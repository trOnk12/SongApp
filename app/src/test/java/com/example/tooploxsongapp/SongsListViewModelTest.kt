package com.example.tooploxsongapp

import androidx.lifecycle.Observer
import com.example.tooploxsongapp.data.entities.SongItemViewModel
import com.example.tooploxsongapp.domain.usecases.GetCombinedSongsUseCase
import com.example.tooploxsongapp.domain.usecases.GetLocalSongsUseCase
import com.example.tooploxsongapp.domain.usecases.GetRemoteSongsUseCase
import com.example.tooploxsongapp.presentation.songslist.SongsListViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Flowable
import io.reactivex.Observable
import org.mockito.ArgumentMatchers.anyString

class SongsListViewModelTest {

//    private val getSongsCombinedSongsUseCase : GetCombinedSongsUseCase = mock()
//    private val getLocalSongsUseCase : GetLocalSongsUseCase = mock()
//    private val getRemoteSongsUseCase : GetRemoteSongsUseCase = mock()
//
//    private val uiStateObserver : Observer<SongsListViewModel.UIState> = mock()
//    val songListViewModel by lazy { SongsListViewModel(getLocalSongsUseCase,getRemoteSongsUseCase,getSongsCombinedSongsUseCase) }
//
//    fun testFetchLocalDataSuccess(){
//        val list = mutableListOf<SongItemViewModel>()
//
//        whenever(getLocalSongsUseCase.getLocalSongs(anyString(),anyString()))
//            .thenReturn(Observable.just(list))
//
//        songListViewModel.fetchSongList(fetchLocal = true, fetchRemote = false)
//
//        verify(uiStateObserver).onChanged(SongsListViewModel.UIState.SHOW_INFO_SCREEN)
//        verify(uiStateObserver).onChanged(SongsListViewModel.UIState.LOADING)
//        verify(uiStateObserver).onChanged(SongsListViewModel.UIState.NO_LOADING)
//    }
//
//
//    fun testFetchRemoteDataSuccess(){
//
//    }


}