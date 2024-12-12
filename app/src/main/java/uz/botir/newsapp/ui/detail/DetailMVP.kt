package uz.botir.newsapp.ui.detail

import uz.botir.newsapp.core.model.tophedline.Article

interface DetailMVP {
    interface View {
        fun isSuccessSave()
        fun setError(message: String)
    }

    interface Presenter {
        fun savePost(article: Article)
    }
}