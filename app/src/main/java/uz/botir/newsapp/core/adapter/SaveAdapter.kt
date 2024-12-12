package uz.botir.newsapp.core.adapter

import android.net.ParseException
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.botir.newsapp.core.db.NewsEntity
import uz.botir.newsapp.databinding.ItemNewChildBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone


class SaveAdapter : RecyclerView.Adapter<SaveAdapter.NewsViewHolder>() {
    var onClickNewsAll: ((news: NewsEntity) -> Unit)? = null

    private val newsList = ArrayList<NewsEntity>()

    fun setData(data: List<NewsEntity>) {
        this.newsList.clear()
        this.newsList.addAll(data)
        notifyDataSetChanged()
    }

    inner class NewsViewHolder(private val binding: ItemNewChildBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: NewsEntity) {
            binding.authorNews.text = data.author
            binding.titleNews.text = data.title
            binding.imageNews.load(data.image)


            val isoFormat: SimpleDateFormat =
                SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
            isoFormat.timeZone = TimeZone.getTimeZone("UTC")

            val desiredFormat: SimpleDateFormat =
                SimpleDateFormat("dd MMM, HH:mm", Locale.getDefault())

            try {
                val date: Date = isoFormat.parse(data.publishAt)!!
                val formattedDate: String = desiredFormat.format(date)
                binding.dateNews.text = formattedDate
            } catch (e: ParseException) {
                binding.dateNews.text = data.publishAt
            }



            itemView.setOnClickListener {

                onClickNewsAll?.invoke(data)
            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ItemNewChildBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bindData(newsList[position])
    }

    override fun getItemCount(): Int = newsList.size
}


