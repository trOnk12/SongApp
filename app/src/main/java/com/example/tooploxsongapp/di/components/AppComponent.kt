package com.example.tooploxsongapp.di.components

import com.example.tooploxsongapp.AndroidApp
import com.example.tooploxsongapp.di.ActivityBuilder
import com.example.tooploxsongapp.di.modules.AppModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@Component(modules = [AndroidSupportInjectionModule::class,
        AppModule::class, ActivityBuilder::class])
interface AppComponent : AndroidInjector<AndroidApp> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<AndroidApp>()

}