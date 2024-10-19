package com.kuri2024.loasearch.transaction.auctions.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kuri2024.loasearch.R
import com.kuri2024.loasearch.api.data.post_auctions.Item

class AuctionAdapter(private var context:Context, val items: List<Item>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

//    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.auction_item,parent,false)
        return VH(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vh: VH = holder as VH
        val item = items[position]
        val iconBack = vh.itemView.findViewById<CardView>(R.id.a_item_icon_back)
        when(item.Grade){
            "일반"->iconBack.background.setTint(context.resources.getColor(R.color.normal))
            "고급"->iconBack.background.setTint(context.resources.getColor(R.color.advanced))
            "희귀"->iconBack.background.setTint(context.resources.getColor(R.color.rare))
            "영웅"->iconBack.background.setTint(context.resources.getColor(R.color.hero))
            "전설"->iconBack.background.setTint(context.resources.getColor(R.color.legend))
            "유물"->iconBack.background.setTint(context.resources.getColor(R.color.relics))
            "고대"->iconBack.background.setTint(context.resources.getColor(R.color.ancient))
            else -> iconBack.background.setTint(context.resources.getColor(R.color.hintColor))
        }
        Glide.with(context).load(item.Icon).into(vh.itemView.findViewById(R.id.a_item_icon))
        vh.itemView.findViewById<TextView>(R.id.a_item_name).text = item.Name
        vh.itemView.findViewById<TextView>(R.id.a_item_remain_count).text = "구매 시 거래 ${item.AuctionInfo.TradeAllowCount}회 가능"
        vh.itemView.findViewById<TextView>(R.id.a_item_level).text = "아이템 레벨 : ${item.Level}"
        if (item.GradeQuality!=null){
            vh.itemView.findViewById<TextView>(R.id.a_item_quality).text = "품질 : ${item.GradeQuality}"
        }else{
            vh.itemView.findViewById<TextView>(R.id.a_item_quality).text = "품질 : -"
        }
        val year = item.AuctionInfo.EndDate.substring(0 until 4)
        val month = item.AuctionInfo.EndDate.substring(5 until 7)
        val day = item.AuctionInfo.EndDate.substring(8 until 10)
        val hour = item.AuctionInfo.EndDate.substring(11 until 13)
        val minute = item.AuctionInfo.EndDate.substring(14 until 16)
        vh.itemView.findViewById<TextView>(R.id.a_item_time_remaining).text = "구매 가능 시간 : ${year}년 ${month}월 ${day}일 ${hour}시 ${minute}분"
        vh.itemView.findViewById<TextView>(R.id.a_item_minimum_bid).text = "최소 입찰가 : ${item.AuctionInfo.BidPrice}"
        vh.itemView.findViewById<TextView>(R.id.a_item_buy_now).text = "구매가 : ${item.AuctionInfo.BuyPrice}"
    }

    override fun getItemCount(): Int {
        return items.size
    }


    inner class VH(itemView: View):RecyclerView.ViewHolder(itemView){
        init {
        }
    }
}