package com.example.tooploxsongapp.presentation.songslist

import com.example.tooploxsongapp.domain.repository.SongRepository
import com.example.tooploxsongapp.domain.usecases.GetCombinedSongsUseCase
import com.example.tooploxsongapp.domain.usecases.GetLocalSongsUseCase
import com.example.tooploxsongapp.domain.usecases.GetRemoteSongsUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SongsListActivityModule {

    @Provides
    fun provideGetSongsUseCase(songRepository: SongRepository): GetCombinedSongsUseCase {
        return GetCombinedSongsUseCase(songRepository)
    }

    @Provides
    fun provideGetLocalSongsUseCase(songRepository: SongRepository): GetLocalSongsUseCase {
        return GetLocalSongsUseCase(songRepository)
    }

    @Provides
    fun provideGetRemoteSongsUseCase(songRepository: SongRepository): GetRemoteSongsUseCase {
        return GetRemoteSongsUseCase(songRepository)
    }

}