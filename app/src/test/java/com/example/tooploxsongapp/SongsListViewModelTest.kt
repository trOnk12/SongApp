package com.example.tooploxsongapp

import androidx.lifecycle.Observer
import com.example.tooploxsongapp.data.entities.SongItemViewModel
import com.example.tooploxsongapp.domain.usecases.GetCombinedSongsUseCase
import com.example.tooploxsongapp.domain.usecases.GetLocalSongsUseCase
import com.example.tooploxsongapp.domain.usecases.GetRemoteSongsUseCase
import com.example.tooploxsongapp.presentation.songslist.SongsListViewModel
import io.reactivex.Flowable
import io.reactivex.Observable
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.Scheduler.Worker
import io.reactivex.disposables.Disposable
import io.reactivex.Scheduler
import org.junit.BeforeClass
import java.util.concurrent.Executor
import java.util.concurrent.TimeUnit
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.tooploxsongapp.data.repository.SongRepositoryImpl
import com.nhaarman.mockitokotlin2.*
import junit.framework.Assert.assertEquals
import org.junit.rules.TestRule
import org.junit.Rule


class SongsListViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val songsRepository: SongRepositoryImpl = mock()

    private val getSongsCombinedSongsUseCase: GetCombinedSongsUseCase = mock()
    private val getLocalSongsUseCase: GetLocalSongsUseCase = mock()
    private val getRemoteSongsUseCase: GetRemoteSongsUseCase = mock()

    private val uiStateObserver: Observer<SongsListViewModel.UIState> = mock()
    private val songListObserver : Observer<List<SongItemViewModel>> = mock()

    private val songListViewModel by lazy {
        SongsListViewModel(
            getSongsCombinedSongsUseCase,
            getLocalSongsUseCase,
            getRemoteSongsUseCase
        )
    }

    @Test
    fun testFetchLocalDataNoArtistName() {
        songListViewModel.uiState.observeForever(uiStateObserver)

        songListViewModel.fetchSongList(fetchLocal = true, fetchRemote = false)


        verify(uiStateObserver).onChanged(SongsListViewModel.UIState.SHOW_INFO_SCREEN)
        verify(uiStateObserver).onChanged(SongsListViewModel.UIState.NO_ARTIST_NAME)
    }

    @Test
    fun testShowResulstEmptyList() {
        songListViewModel.uiState.observeForever(uiStateObserver)

        val list = mutableListOf<SongItemViewModel>()
        songListViewModel.showResults(list)

        verify(uiStateObserver).onChanged(SongsListViewModel.UIState.NO_LOADING)
        verify(uiStateObserver).onChanged(SongsListViewModel.UIState.NO_SEARCH_RESULTS)
    }

    @Test
    fun testShowDataValidData() {
        songListViewModel.uiState.observeForever(uiStateObserver)

        val list = mutableListOf(SongItemViewModel("TEST","TEST","TEST"))
        songListViewModel.showResults(list)


        verify(uiStateObserver).onChanged(SongsListViewModel.UIState.NO_LOADING )
        verify(uiStateObserver).onChanged(SongsListViewModel.UIState.HIDE_INFO_SCREEN)
    }


}