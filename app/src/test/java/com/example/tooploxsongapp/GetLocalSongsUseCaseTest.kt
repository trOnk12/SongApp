package com.example.tooploxsongapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.tooploxsongapp.data.entities.LocalSong
import com.example.tooploxsongapp.data.entities.SongItemViewModel
import com.example.tooploxsongapp.data.repository.SongRepositoryImpl
import com.example.tooploxsongapp.domain.usecases.GetLocalSongsUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Flowable
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

class GetLocalSongsUseCaseTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    val songRepository = mock<SongRepositoryImpl>()

    val localSongsUseCase by lazy { GetLocalSongsUseCase(songRepository) }

    @Test
    fun testGetSongListCompleted() {
        whenever(songRepository.getLocalSongs(anyString(), anyString()))
            .thenReturn(Flowable.just(emptyList()))

        localSongsUseCase.getLocalSongs("test", "test")
            .test()
            .assertComplete()
    }

    @Test
    fun testGetSongListArtistNameAndReleaseYearResponse() {
        val response = arrayListOf(
            LocalSong("TEST", "TEST", 2019, "TEST", 1, 1, 1, 1),
            LocalSong("TEST", "TEST", 2019, "TEST", 1, 1, 1, 1),
            LocalSong("TEST", "TEST", 2019, "TEST", 1, 1, 1, 1)
        )

        whenever(songRepository.getLocalSongs(anyString(), anyString()))
            .thenReturn(Flowable.just(response))

        val expected = mutableListOf<SongItemViewModel>()

        for (song in response) {
            expected.add(SongItemViewModel.convertFromLocal(song))
        }

        localSongsUseCase.getLocalSongs("TEST", "1994")
            .test()
            .assertValue(expected)
    }

    @Test
    fun testGetSongListNoReleaseYearResponse() {
        val response = mutableListOf(
            LocalSong()
        )

        whenever(songRepository.getLocalSongs(anyString()))
            .thenReturn(Flowable.just(response))

        val expected = mutableListOf(SongItemViewModel.convertFromLocal(LocalSong()) )

        localSongsUseCase.getLocalSongs("TEST", null)
            .test()
            .assertValue(expected)
    }

}