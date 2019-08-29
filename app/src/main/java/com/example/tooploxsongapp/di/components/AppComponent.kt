package com.example.tooploxsongapp.di.components

import com.example.tooploxsongapp.AndroidApp
import com.example.tooploxsongapp.di.ActivityBuilder
import com.example.tooploxsongapp.di.modules.AppModule
import com.example.tooploxsongapp.di.modules.LocalDataModule
import com.example.tooploxsongapp.di.modules.NetworkModule
import com.example.tooploxsongapp.di.modules.RepositoryModule
import com.example.tooploxsongapp.presentation.songslist.utils.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import android.app.Application
import dagger.BindsInstance
import dagger.android.AndroidInjectionModule


@Component(modules = [AndroidInjectionModule::class,
        AppModule::class, ViewModelModule::class, ActivityBuilder::class, NetworkModule::class,RepositoryModule::class,LocalDataModule::class])
interface AppComponent{

    @Component.Builder
     interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: AndroidApp)

}