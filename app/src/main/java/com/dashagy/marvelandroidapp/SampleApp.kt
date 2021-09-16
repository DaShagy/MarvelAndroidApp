package com.dashagy.marvelandroidapp

import android.app.Application
import com.dashagy.di.repositoriesModule
import com.dashagy.di.useCasesModule
import com.dashagy.marvelandroidapp.di.viewModelsModule
import io.realm.Realm
import org.koin.core.context.startKoin

class SampleApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        startKoin {
            modules(listOf(repositoriesModule, viewModelsModule, useCasesModule))
        }
    }
}