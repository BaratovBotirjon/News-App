package uz.botir.newsapp.ui.main.home

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.SingleObserver
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Response
import uz.botir.newsapp.core.model.everything.EverythingResponse
import uz.botir.newsapp.core.model.tophedline.TopHeadLineResponse
import uz.botir.newsapp.core.network.ApiClient
import uz.botir.newsapp.core.util.API_KEY

class HomePresenter(val view: HomeMVP.View) : HomeMVP.Presenter {

    private val service = ApiClient.createNewsService()


    override fun getEverything(theme: String) {

        service.getEverything(theme, API_KEY)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : SingleObserver<Response<EverythingResponse>> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    view.setError(e.message.toString())
                }

                override fun onSuccess(t: Response<EverythingResponse>) {
                    if (t.isSuccessful) {
                        t.body()?.let { body ->
                            view.setEverything(body)
                        }
                    }
                }
            })
    }
    override fun getTopHeadLine(country: String) {
        service.getTopheadline(country, API_KEY)
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