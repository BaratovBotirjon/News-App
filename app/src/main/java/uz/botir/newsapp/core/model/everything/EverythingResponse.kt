package uz.botir.newsapp.core.model.everything


import com.google.gson.annotations.SerializedName
import uz.botir.newsapp.core.model.BaseModel

data class EverythingResponse(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
) : BaseModel() {
    override fun getType(): Int {
        return BANNER_ITEM
    }

}