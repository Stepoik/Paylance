package stepan.gorokhov.paylance

import android.app.Application
import android.content.Context
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.initialize
import org.koin.dsl.module
import stepan.gorokhov.paylance.di.KoinSDK

class PaylanceApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
        Firebase.initialize(this)
    }

    private fun initKoin() {
        KoinSDK.init(module {
            single<Context> { this@PaylanceApplication }
        })
    }
}