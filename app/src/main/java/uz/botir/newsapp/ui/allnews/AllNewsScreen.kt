package uz.botir.newsapp.ui.allnews

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.botir.newsapp.R
import uz.botir.newsapp.core.adapter.NewsAdapter
import uz.botir.newsapp.core.model.tophedline.TopHeadLineResponse
import uz.botir.newsapp.core.util.gone
import uz.botir.newsapp.core.util.visible
import uz.botir.newsapp.databinding.ScreenAllnewsBinding

class AllNewsScreen : Fragment(R.layout.screen_allnews), AllNewsMVP.View {
    private val binding by viewBinding(ScreenAllnewsBinding::bind)
    private var presenter: AllNewsMVP.Presenter? = null
    private val adapter by lazy { NewsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadAdapter()
        sendRequest()
        loadAction()
    }

    private fun loadAction() {

        for (i in 0 until binding.categoryGroup.childCount) {
            val child = binding.categoryGroup.getChildAt(i) as TextView
            child.setOnClickListener {
                val category = child.text.toString().trim()
                presenter?.getTopHeadLine(category)
                adapter.clearAdapter()
                binding.progressBar.visible()
            }
        }
    }
    private fun loadAdapter() {
        binding.allnewsRecyclerView.adapter = adapter
    }

    private fun sendRequest() {
        presenter = AllNewsPresenter(this)
        presenter?.getTopHeadLine("general")
        binding.progressBar.visible()
    }

    override fun setTopHeadLine(data: TopHeadLineResponse) {
        adapter.setData(data.articles)
        binding.progressBar.gone()
    }

    override fun setError(message: String) {
        binding.errorView.visible()
        binding.progressBar.gone()
    }
}