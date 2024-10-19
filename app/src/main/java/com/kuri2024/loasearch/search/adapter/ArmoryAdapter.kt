package com.kuri2024.loasearch.search.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kuri2024.loasearch.R
import com.kuri2024.loasearch.search.data.ArmoryData

class ArmoryAdapter(private var context:Context, val items: ArrayList<ArmoryData>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.armory_item,parent,false)
        return VH(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vh: VH = holder as VH
        val item = items[position]

        Glide.with(context).load(item.icon).into(vh.itemView.findViewById(R.id.armory_iv))
        vh.itemView.findViewById<TextView>(R.id.armory_name).text = item.name
        vh.itemView.findViewById<TextView>(R.id.armory_transcendence).text = item.transcendence
        val iconBack = vh.itemView.findViewById<CardView>(R.id.armory_iv_back)
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
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun setOnItemClickListener(listener: OnItemClickListener){
        this.listener = listener
    }

    interface OnItemClickListener{
        fun webMove(position: Int)
    }

    inner class VH(itemView: View):RecyclerView.ViewHolder(itemView){
        init {
        }
    }
}