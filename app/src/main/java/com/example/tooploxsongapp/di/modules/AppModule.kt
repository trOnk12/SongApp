package com.example.tooploxsongapp.di.modules

import android.app.Application
import android.content.Context
import com.example.tooploxsongapp.AndroidApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun provideContext(application: Application): Context {
        return application
    }

}