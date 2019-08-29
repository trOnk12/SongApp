package com.example.tooploxsongapp

import com.example.tooploxsongapp.data.entities.LocalSong
import com.example.tooploxsongapp.data.entities.SongItemViewModel
import com.example.tooploxsongapp.data.repository.SongRepositoryImpl
import com.example.tooploxsongapp.domain.usecases.GetLocalSongsUseCase
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Flowable
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

class GetLocalSongsUseCaseTest {
//
//val songRepository = mock<SongRepositoryImpl>()
//
//    val localSongsUseCase by lazy { GetLocalSongsUseCase(songRepository) }
//
//    @Test
//    fun testGetSongListCompleted(){
//        whenever(songRepository.getLocalSongs(anyString(),anyString()))
//            .thenReturn(Flowable.just(emptyList()))
//
//
//        localSongsUseCase.getLocalSongs("test","test")
//            .test()
//            .assertComplete()
//    }
//
//    @Test
//    fun testGetSongListResponse(){
//        val response = arrayListOf(LocalSong("TEST","TEST",2019,"TEST",1,1,1,1))
//
//        whenever(songRepository.getLocalSongs(anyString(),anyString()))
//            .thenReturn(Flowable.just(response))
//
//        val expected = arrayListOf<SongItemViewModel>()
//
//        for(song in response ){
//        expected.add(SongItemViewModel.convertFromLocal(song))
//        }
//
//        localSongsUseCase.getLocalSongs("TEST",null)
//            .test()
//            .assertValue(expected)
//    }

}