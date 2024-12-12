package uz.botir.newsapp.ui.detail_save

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import uz.botir.newsapp.R
import uz.botir.newsapp.core.db.NewsDataBase
import uz.botir.newsapp.core.util.gone
import uz.botir.newsapp.core.util.visible
import uz.botir.newsapp.databinding.ScreenDetailSaveBinding

class DetailScreenSave: Fragment(R.layout.screen_detail_save)  {

    private val binding by viewBinding(ScreenDetailSaveBinding::bind)
    private val args by navArgs<DetailScreenSaveArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageView.load(args.article.image)
        binding.title.text = args.article.title
        binding.description.text = args.article.content
        binding.backIcon.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.deleteBtn.setOnClickListener {
            binding.deleteBtn.gone()
            binding.savedIcon.visible()

            NewsDataBase.getDb().getDao().deleteNews(args.article)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.d(TAG, "Article O'chirildi")
                }, { error ->
                    Log.e(TAG, "eror", error)
                })
        }
    }

}
