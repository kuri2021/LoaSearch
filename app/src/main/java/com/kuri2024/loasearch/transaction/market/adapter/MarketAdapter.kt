package com.kuri2024.loasearch.transaction.market.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kuri2024.loasearch.R

class MarketAdapter(private var context:Context, val items: List<MarketListItem>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
        val iconBack = vh.itemView.findViewById<CardView>(R.id.m_item_icon_back)
        when(item.grade){
            "일반"->iconBack.background.setTint(context.resources.getColor(R.color.normal))
            "고급"->iconBack.background.setTint(context.resources.getColor(R.color.advanced))
            "희귀"->iconBack.background.setTint(context.resources.getColor(R.color.rare))
            "영웅"->iconBack.background.setTint(context.resources.getColor(R.color.hero))
            "전설"->iconBack.background.setTint(context.resources.getColor(R.color.legend))
            "유물"->iconBack.background.setTint(context.resources.getColor(R.color.relics))
            "고대"->iconBack.background.setTint(context.resources.getColor(R.color.ancient))
            else -> iconBack.background.setTint(context.resources.getColor(R.color.hintColor))
        }
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