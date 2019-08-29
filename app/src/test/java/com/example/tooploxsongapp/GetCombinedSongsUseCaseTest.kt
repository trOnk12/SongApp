package com.example.tooploxsongapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.tooploxsongapp.data.entities.SongItemViewModel
import com.example.tooploxsongapp.data.repository.SongRepositoryImpl
import com.example.tooploxsongapp.domain.model.RemoteSong
import com.example.tooploxsongapp.domain.usecases.GetRemoteSongsUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Flowable
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers

class GetCombinedSongsUseCaseTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    val songRepository = mock<SongRepositoryImpl>()

    val remoteSongsUseCase by lazy { GetRemoteSongsUseCase(songRepository) }

    @Test
    fun testGetSongListCompleted() {
        whenever(songRepository.getRemoteSongs(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
            .thenReturn(Flowable.just(emptyList()))

        remoteSongsUseCase.getRemoteSongs("test", "test")
            .test()
            .assertComplete()
    }

    @Test
    fun testGetSongListArtistNameAndReleaseYearResponse() {
        val remoteSong = RemoteSong(
            "TEST",
            "TEST",
            2019,
            1,
            "TEST",
            "TEST",
            "TEST",
            "TEST",
            "TEST",
            "TEST",
            "TEST",
            "TEST",
            "TEST",
            2.00,
            2.00,
            "TEST",
            "TEST",
            "TEST",
            2000,
            "TEST",
            "TEST",
            "TEST",
            "TEST"
        )

        val response = arrayListOf(remoteSong,remoteSong,remoteSong)

        whenever(songRepository.getRemoteSongs(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
            .thenReturn(Flowable.just(response))

        val expected = mutableListOf<SongItemViewModel>()

        for (song in response) {
            expected.add(SongItemViewModel.convertFromRemote(song))
        }

        remoteSongsUseCase.getRemoteSongs("TEST", "1994")
            .test()
            .assertValue(expected)
    }

    @Test
    fun testGetSongListNoReleaseYearResponse() {
        val remoteSong = RemoteSong(
            "TEST",
            "TEST",
            2019,
            1,
            "TEST",
            "TEST",
            "TEST",
            "TEST",
            "TEST",
            "TEST",
            "TEST",
            "TEST",
            "TEST",
            2.00,
            2.00,
            "TEST",
            "TEST",
            "TEST",
            2000,
            "TEST",
            "TEST",
            "TEST",
            "TEST"
        )

        val response = arrayListOf(remoteSong,remoteSong,remoteSong)


        whenever(songRepository.getRemoteSongs(ArgumentMatchers.anyString()))
            .thenReturn(Flowable.just(response))

        val expected = mutableListOf<SongItemViewModel>()

        for (song in response) {
            expected.add(SongItemViewModel.convertFromRemote(song))
        }

        remoteSongsUseCase.getRemoteSongs("TEST", null)
            .test()
            .assertValue(expected)
    }


}