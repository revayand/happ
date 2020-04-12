package co.health.test.corona

import android.app.Application
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            // modules
            modules(co.health.test.corona.di.modules)

        }
    }
}