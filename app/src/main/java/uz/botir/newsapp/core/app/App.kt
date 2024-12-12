package uz.botir.newsapp.core.app

import android.app.Application
import uz.botir.newsapp.core.db.NewsDataBase

class App : Application() {

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        NewsDataBase.init(this)
    }

}