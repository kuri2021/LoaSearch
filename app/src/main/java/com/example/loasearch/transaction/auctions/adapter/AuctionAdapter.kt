package com.example.loasearch.transaction.market.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.loasearch.R

class AuctionAdapter(private var context:Context, val items: List<AuctionListItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

//    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.market_item,parent,false)
        return VH(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vh: VH = holder as VH
        val item = items[position]

        Glide.with(context).load(item.icon).into(vh.itemView.findViewById(R.id.m_item_icon))
        vh.itemView.findViewById<TextView>(R.id.m_item_name).text = item.name
        if (item.tradeRemainCount!=null){
            vh.itemView.findViewById<TextView>(R.id.m_item_remain_count).visibility = View.VISIBLE
            vh.itemView.findViewById<TextView>(R.id.m_item_remain_count).text = "구매 시 거래 ${item.tradeRemainCount}회 가능"
        }else{
            vh.itemView.findViewById<TextView>(R.id.m_item_remain_count).visibility = View.INVISIBLE
        }
        vh.itemView.findViewById<TextView>(R.id.m_item_y_day_avg_price).text = "전일 평균 거래가 : ${item.yDayAvgPrice}"
        vh.itemView.findViewById<TextView>(R.id.m_item_recent_price).text = "최근 거래가 : ${item.recentPrice}"
        vh.itemView.findViewById<TextView>(R.id.m_item_current_min_price).text = "최저가 : ${item.currentMinPrice}"
    }

    override fun getItemCount(): Int {
        return items.size
    }

//    fun setOnItemClickListener(listener: OnItemClickListener){
//        this.listener = listener
//    }
//
//    interface OnItemClickListener{
//        fun webMove(position: Int)
//    }

    inner class VH(itemView: View):RecyclerView.ViewHolder(itemView){
        init {
        }
    }
}