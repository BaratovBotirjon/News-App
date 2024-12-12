package uz.botir.newsapp.ui.main.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.botir.newsapp.R
import uz.botir.newsapp.core.adapter.MultiAdapter
import uz.botir.newsapp.core.model.BaseModel
import uz.botir.newsapp.core.model.everything.EverythingResponse
import uz.botir.newsapp.core.model.tophedline.TopHeadLineResponse
import uz.botir.newsapp.core.util.visible
import uz.botir.newsapp.databinding.PageHomeBinding
import uz.botir.newsapp.ui.main.MainScreenDirections

class HomePage : Fragment(R.layout.page_home), HomeMVP.View {

    private val binding by viewBinding(PageHomeBinding::bind)
    private var presenter: HomeMVP.Presenter? = null
    private var baseData = ArrayList<BaseModel>()
    private val adapter by lazy { MultiAdapter() }
    private var first = false
    private var second = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadAdapter()
        sendRequest()
        loadActions()
    }

    private fun loadActions() {
        adapter.onClickNews = {
            findNavController().navigate(MainScreenDirections.actionMainScreenToDetailScreen(it))
        }
        adapter.onClicSee = {
            findNavController().navigate(MainScreenDirections.actionMainScreenToAllNews())
        }
    }

    private fun setDataToAdapter() {
        if (first && second) {
            adapter.setData(baseData)
        }
    }

    private fun loadAdapter() {
        binding.mainRecyclerView.adapter = adapter
    }

    private fun sendRequest() {
        baseData.clear()

        presenter = HomePresenter(this)
        presenter?.getEverything("bitcoin")
        presenter?.getTopHeadLine("us")
    }

    override fun setEverything(data: EverythingResponse) {
        first = true
        baseData.add(data)
        setDataToAdapter()
    }

    override fun setTopHeadLine(data: TopHeadLineResponse) {
        second = true
        baseData.add(data)
        setDataToAdapter()
    }

    override fun setError(message: String) {
        binding.errorView.visible()
    }

}