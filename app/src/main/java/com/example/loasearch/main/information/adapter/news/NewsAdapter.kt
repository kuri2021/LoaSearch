package com.example.loasearch.main.information.adapter.news

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.loasearch.R
import com.example.loasearch.api.data.news.GetNewsData

class NewsAdapter(private var context:Context, val items: GetNewsData) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.news_item,parent,false)
        return VH(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vh: VH = holder as VH
        val item = items[position]

        vh.itemView.findViewById<TextView>(R.id.newsTitle).text = item.Title
        vh.itemView.findViewById<TextView>(R.id.newsDate).text = item.Date.substring(0 until 10)
        if (position==4){
            vh.itemView.findViewById<View>(R.id.newsLine).visibility = View.INVISIBLE
        }else{
            vh.itemView.findViewById<View>(R.id.newsLine).visibility = View.VISIBLE
        }
    }

    override fun getItemCount(): Int {
        return 5
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }

    interface OnItemClickListener{
        fun webMove(position: Int)
    }

    inner class VH constructor(itemView: View):RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener{
                listener?.webMove(position)
            }
        }
    }
}