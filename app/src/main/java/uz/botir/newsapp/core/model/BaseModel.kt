package uz.botir.newsapp.core.model

abstract class BaseModel {

    companion object {
        val BANNER_ITEM = 0
        val NEWS_ITEM = 1
    }

    abstract fun getType(): Int
}