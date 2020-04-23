package co.health.test.corona

import android.app.Application
import co.health.test.corona.screen.utils.FontsOverride
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        FontsOverride.setYekan(this)
        startKoin {
            androidContext(this@App)
            androidLogger()
            modules(co.health.test.corona.di.modules)
        }
    }
}