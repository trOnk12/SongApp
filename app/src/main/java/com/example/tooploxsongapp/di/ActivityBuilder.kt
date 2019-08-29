package com.example.tooploxsongapp.di

import com.example.tooploxsongapp.presentation.songslist.SongsListActivityModule
import dagger.android.ContributesAndroidInjector
import com.example.tooploxsongapp.presentation.songslist.SongsListActivity
import dagger.Module


@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [SongsListActivityModule::class])
    internal abstract fun bindSongsListActivity(): SongsListActivity

}