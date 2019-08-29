package com.example.tooploxsongapp

import com.example.tooploxsongapp.domain.usecases.GetCombinedSongsUseCase
import com.example.tooploxsongapp.domain.usecases.GetLocalSongsUseCase
import com.example.tooploxsongapp.domain.usecases.GetRemoteSongsUseCase
import com.nhaarman.mockitokotlin2.mock

class SongsListViewModelTest {

    private val getSongsCombinedSongsUseCase : GetCombinedSongsUseCase = mock()
    private val getLocalSongsUseCase : GetLocalSongsUseCase = mock()
    private val getRemoteSongsUseCase : GetRemoteSongsUseCase = mock()

    fun testDetermineFetchSource(){

    }


}