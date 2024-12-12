package uz.botir.newsapp.ui.allnews

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import uz.botir.newsapp.core.model.tophedline.TopHeadLineResponse
import uz.botir.newsapp.core.network.ApiClient
import uz.botir.newsapp.core.util.API_KEY

class AllNewsPresenter(val view: AllNewsMVP.View) : AllNewsMVP.Presenter {

    private val service = ApiClient.createNewsService()

    override fun getTopHeadLine(category: String) {
        service.getTopheadlineWithCategory(API_KEY , 50 ,category)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Response<TopHeadLineResponse>> {
                override fun onSubscribe(d: Disposable) {}

                override fun onError(e: Throwable) {
                    view.setError(e.message.toString())
                }

                override fun onSuccess(t: Response<TopHeadLineResponse>) {
                    if (t.isSuccessful) {
                        t.body()?.let { body ->
                            view.setTopHeadLine(body)
                        }
                    }
                }
            })
    }
}