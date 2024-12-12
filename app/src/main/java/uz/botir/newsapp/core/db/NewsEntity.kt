package uz.botir.newsapp.core.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class NewsEntity(
    val title: String,
    val content: String,
    val description: String,
    val publishAt: String,
    val image: String,
    val author: String
) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}