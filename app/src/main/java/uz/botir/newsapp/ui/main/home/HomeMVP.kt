package uz.botir.newsapp.ui.main.home

import uz.botir.newsapp.core.model.everything.EverythingResponse
import uz.botir.newsapp.core.model.tophedline.TopHeadLineResponse

interface HomeMVP {
    interface View {
        fun setEverything(data: EverythingResponse)
        fun setTopHeadLine(data: TopHeadLineResponse)
        fun setError(message: String)
    }

    interface Presenter {
        fun getEverything(theme: String)
        fun getTopHeadLine(country: String)
    }

}