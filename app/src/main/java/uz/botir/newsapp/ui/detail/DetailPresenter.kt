package uz.botir.newsapp.ui.detail

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.CompletableObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import uz.botir.newsapp.core.db.NewsDataBase
import uz.botir.newsapp.core.db.NewsEntity
import uz.botir.newsapp.core.model.tophedline.Article

class DetailPresenter(private val view: DetailMVP.View) : DetailMVP.Presenter {

    private val dataBase = NewsDataBase.getDb().getDao()

    override fun savePost(article: Article) {
        val news = mapToEntity(article)
        dataBase.addNews(news)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onComplete() {
                    view.isSuccessSave()
                }

                override fun onError(e: Throwable) {
                    view.setError(e.message.toString())
                }
            })
    }

    private fun mapToEntity(data: Article): NewsEntity {
        return NewsEntity(
            data.title ?: "",
            data.content ?: "",
            data.description ?: "",
            data.publishedAt ?: "",
            data.urlToImage ?: "",
            data.author ?: ""
        )
    }


}