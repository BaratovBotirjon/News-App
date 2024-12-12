package uz.botir.newsapp.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.botir.newsapp.R
import uz.botir.newsapp.core.model.everything.Article
import uz.botir.newsapp.databinding.ItemBannerChildBinding

class BannerAdapter : RecyclerView.Adapter<BannerAdapter.ViewHolder>() {

    private val articles = ArrayList<Article>()

    fun setData(data: List<Article>) {
        this.articles.clear()
        this.articles.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemBannerChildBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: Article) {
            binding.title.text = data.title
            binding.imageBanner.load(data.urlToImage) {
                placeholder(R.drawable.place_holder)
                error(R.drawable.place_holder)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBannerChildBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(articles[position])
    }

    override fun getItemCount(): Int = articles.size
}
