package com.lock.bengalsharenews

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class Sharenewsadapter(private val lister:Newsitemclicked) : RecyclerView.Adapter<NewsViewholder>() {
    private  val items:ArrayList<News> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewholder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        val viewholder=NewsViewholder(view)
        view.setOnClickListener{
            lister.newsclick(items[viewholder.adapterPosition])
        }
        return viewholder
    }

    override fun onBindViewHolder(holder: NewsViewholder, position: Int) {
        val currentItem=items[position]
        holder.titleview.text=currentItem.title
        Glide.with(holder.itemView.context).load(currentItem.image).into(holder.imageview).view
        holder.dateview.text=currentItem.author
    }

    override fun getItemCount(): Int {
        return items.size
    }
    fun updatenews(updatednews:ArrayList<News>){
        items.clear()
        items.addAll(updatednews)

        notifyDataSetChanged()
    }

}
class NewsViewholder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val titleview:TextView=itemView.findViewById(R.id.title)
    val imageview:ImageView=itemView.findViewById(R.id.image)
    val dateview:TextView=itemView.findViewById(R.id.date)
}
interface Newsitemclicked{
    fun newsclick(items:News)
}