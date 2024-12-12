package uz.botir.newsapp.core.model.tophedline

import com.google.gson.annotations.SerializedName
import uz.botir.newsapp.core.model.BaseModel

data class TopHeadLineResponse(
    @SerializedName("articles")
    var articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
) : BaseModel() {
    override fun getType(): Int {
        return NEWS_ITEM
    }
}