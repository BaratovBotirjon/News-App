package uz.botir.newsapp.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.botir.newsapp.core.model.BaseModel
import uz.botir.newsapp.core.model.everything.EverythingResponse
import uz.botir.newsapp.core.model.tophedline.Article
import uz.botir.newsapp.core.model.tophedline.TopHeadLineResponse
import uz.botir.newsapp.databinding.ItemBannerParentBinding
import uz.botir.newsapp.databinding.ItemNewParentBinding

class MultiAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val data = ArrayList<BaseModel>()
    var onClickNews: ((article: Article) -> Unit)? = null
    var onClicSee: (() -> Unit?)? = null

    fun setData(data: ArrayList<BaseModel>) {
        this.data.clear()
        data.sortBy { it.getType() }
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return data[position].getType()
    }

    inner class BannerViewHolder(private val binding: ItemBannerParentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val adapter by lazy { BannerAdapter() }

        fun bindData(data: EverythingResponse) {
            binding.viewPagerBanner.adapter = adapter
            adapter.setData(data.articles)
        }
    }


    inner class NewsViewHolder(private val binding: ItemNewParentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val adapter by lazy { NewsAdapter() }

        fun bindData(data: TopHeadLineResponse) {
            binding.newsRecyclerView.adapter = adapter
            adapter.setData(data.articles)

            adapter.onClick = {
                onClickNews?.invoke(it)
            }
            binding.seeAll.setOnClickListener{
                onClicSee?.invoke()
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            BaseModel.BANNER_ITEM -> {
                BannerViewHolder(
                    ItemBannerParentBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            else -> {
                NewsViewHolder(
                    ItemNewParentBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val type = getItemViewType(position)
        when (type) {
            BaseModel.BANNER_ITEM -> {
                (holder as BannerViewHolder).bindData(data[position] as EverythingResponse)
            }
            else -> {
                (holder as NewsViewHolder).bindData(data[position] as TopHeadLineResponse)
            }
        }
    }
}