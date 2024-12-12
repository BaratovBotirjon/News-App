package uz.botir.newsapp.ui.main.save

import uz.botir.newsapp.core.db.NewsEntity

interface SaveNewsMVP {
    interface View {
        fun setAllSaves(data: List<NewsEntity>)
        fun setError(message: String)
    }

    interface Presenter {
        fun getAllSaves()
    }

}