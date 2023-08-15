package co.uk.practice.compose_practice

import android.app.Application
import co.uk.practice.compose_practice.module.AppModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.*
class ComposeApp : Application(){
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ComposeApp)
            modules(AppModule().module)
        }
    }
}