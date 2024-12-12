package uz.botir.newsapp.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.botir.newsapp.R
import uz.botir.newsapp.core.model.tophedline.Article
import uz.botir.newsapp.databinding.ItemNewChildBinding

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private val articles = ArrayList<Article>()
    var onClick: ((article: Article) -> Unit)? = null

    fun setData(data: List<Article>) {
        this.articles.clear()
        data.forEach { item ->
            if (item.title != "[Removed]") {
                articles.add(item)
            }
        }
        notifyDataSetChanged()
    }

    fun clearAdapter(){
        this.articles.clear()
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemNewChildBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(data: Article) {
            binding.imageNews.load(data.urlToImage) {
                placeholder(R.drawable.place_holder)
                error(R.drawable.place_holder)
            }
            binding.titleNews.text = data.title
            binding.dateNews.text = data.publishedAt
            binding.authorNews.text = data.author ?: "No author"

            itemView.setOnClickListener {
                onClick?.invoke(data)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemNewChildBinding.inflate(
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
