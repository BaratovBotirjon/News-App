package uz.botir.newsapp.ui.allnews

import uz.botir.newsapp.core.model.tophedline.TopHeadLineResponse

interface AllNewsMVP {
    interface View {
        fun setTopHeadLine(data: TopHeadLineResponse)
        fun setError(message: String)
    }

    interface Presenter {
        fun getTopHeadLine(category: String)
    }

}