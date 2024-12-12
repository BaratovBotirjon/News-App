package uz.botir.newsapp.core.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface NewsDAO {

    @Query("SELECT * FROM NewsEntity")
    fun getAllNews(): Single<List<NewsEntity>>

    @Query("SELECT * FROM NewsEntity WHERE id = :id")
    fun getOneNews(id: Int): Single<NewsEntity>

    @Insert
    fun addNews(news: NewsEntity): Completable

    @Delete
    fun deleteNews(news: NewsEntity): Completable
}