package com.example.tooploxsongapp.di.modules

import android.content.Context
import com.example.tooploxsongapp.data.local.AssetsHelper
import com.example.tooploxsongapp.data.local.LocalDataSongs
import dagger.Module
import dagger.Provides

@Module
class LocalDataModule {

    @Provides
    fun provideAssetsHelper(context: Context): AssetsHelper {
        return AssetsHelper(context)
    }

    @Provides
    fun provideLocalDataSongs(assetsHelper: AssetsHelper): LocalDataSongs {
        return LocalDataSongs(assetsHelper)
    }
}