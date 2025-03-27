package com.example.dexdogs.data.di

import com.example.dexdogs.ApplicationController
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {
    fun inject(application: ApplicationController)
}