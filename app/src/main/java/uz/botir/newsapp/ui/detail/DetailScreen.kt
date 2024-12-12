package uz.botir.newsapp.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import uz.botir.newsapp.R
import uz.botir.newsapp.core.util.gone
import uz.botir.newsapp.core.util.visible
import uz.botir.newsapp.databinding.ScreenDetailBinding

class DetailScreen : Fragment(R.layout.screen_detail), DetailMVP.View {

    private val binding by viewBinding(ScreenDetailBinding::bind)
    private val args by navArgs<DetailScreenArgs>()
    private var presenter: DetailMVP.Presenter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = DetailPresenter(this)

        loadDataToView()
        loadActions()
    }

    private fun loadActions() {
        binding.backIcon.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.saveBtn.setOnClickListener {
            presenter?.savePost(args.article)
        }
    }

    private fun loadDataToView() {
        binding.imageView.load(args.article.urlToImage)
        binding.title.text = args.article.title
        binding.description.text = args.article.content
    }

    override fun isSuccessSave() {
        binding.saveBtn.gone()
        binding.savedIcon.visible()
    }

    override fun setError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}