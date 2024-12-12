package uz.botir.newsapp.core.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NewsEntity::class], version = 1)
abstract class NewsDataBase : RoomDatabase() {

    abstract fun getDao(): NewsDAO

    companion object {
        private var db: NewsDataBase? = null

        fun init(context: Context) {
            if (db == null) {
                db = Room.databaseBuilder(
                    context,
                    NewsDataBase::class.java,
                    "news_db"
                ).build()
            }
        }

        fun getDb(): NewsDataBase {
            return db!!
        }
    }
}
