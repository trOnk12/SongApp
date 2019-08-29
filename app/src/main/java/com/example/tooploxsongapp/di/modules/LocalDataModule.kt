package com.example.tooploxsongapp.di.modules

import android.content.Context
import com.example.tooploxsongapp.data.local.FileManager
import com.example.tooploxsongapp.data.local.LocalSongsReader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {

    @Provides
    fun provideFileManager(context: Context): FileManager {
        return FileManager(context)
    }

    @Provides
    fun provideLocalSongsReader(fileManager: FileManager): LocalSongsReader {
        return LocalSongsReader(fileManager)
    }
}