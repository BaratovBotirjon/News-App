package uz.botir.newsapp.ui.main.save

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.botir.newsapp.R
import uz.botir.newsapp.core.adapter.SaveAdapter
import uz.botir.newsapp.core.db.NewsEntity
import uz.botir.newsapp.databinding.PageSaveBinding
import uz.botir.newsapp.ui.main.MainScreenDirections

class SavePage : Fragment(R.layout.page_save), SaveNewsMVP.View {
    private val binding by viewBinding(PageSaveBinding::bind)
    private val adapter by lazy { SaveAdapter() }
    private var presenter: SaveNewsMVP.Presenter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter = SaveNewsPresenter(this)
        presenter?.getAllSaves()

        loadAdapter()
    }

    private fun loadAdapter() {
        binding.saveRecyclerView.adapter = adapter
        adapter.onClickNewsAll = { news -> navigateToDetail(news) }
    }

    private fun navigateToDetail(news: NewsEntity) {
        val action = MainScreenDirections.actionMainScreenToDetailScreenSave(news)
        findNavController().navigate(action)
    }

    override fun setAllSaves(data: List<NewsEntity>) {
        adapter.setData(data)
    }

    override fun setError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}

