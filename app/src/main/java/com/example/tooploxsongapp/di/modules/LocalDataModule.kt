package com.example.tooploxsongapp.di.modules

import android.content.Context
import com.example.tooploxsongapp.data.local.FileManager
import com.example.tooploxsongapp.data.local.LocalSongsReader
import dagger.Module
import dagger.Provides

@Module
class LocalDataModule {

    @Provides
    fun provideAssetsHelper(context: Context): FileManager {
        return FileManager(context)
    }

    @Provides
    fun provideLocalDataSongs(fileManager: FileManager): LocalSongsReader {
        return LocalSongsReader(fileManager)
    }
}