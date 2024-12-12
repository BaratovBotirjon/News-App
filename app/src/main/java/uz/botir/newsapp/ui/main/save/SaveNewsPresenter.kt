package uz.botir.newsapp.ui.main.save

import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import uz.botir.newsapp.core.db.NewsDataBase
import uz.botir.newsapp.core.db.NewsEntity

class SaveNewsPresenter(val view: SaveNewsMVP.View) : SaveNewsMVP.Presenter {

    private val dataBase = NewsDataBase.getDb().getDao()

    override fun getAllSaves() {

        dataBase.getAllNews()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<List<NewsEntity>> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    view.setError(e.message.toString())
                }

                override fun onSuccess(list: List<NewsEntity>) {
                    view.setAllSaves(list)
                    Log.d("TAGaaaaaa", "onSuccess: ${list[0].publishAt}")
                }

            })

    }


}