package com.kuri2024.loasearch.main.information.adapter.guardian

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kuri2024.loasearch.R
import com.kuri2024.loasearch.api.data.challenge_guardian.Raid

class GuardianAdapter(private var context:Context, val items: List<Raid>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var listener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val itemView = inflater.inflate(R.layout.weekly_item,parent,false)
        return VH(itemView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val vh: VH = holder as VH
        val item = items[position]

        Glide.with(context).load(item.Image).into(vh.itemView.findViewById(R.id.weeklyIv))
        vh.itemView.findViewById<TextView>(R.id.weeklyNameTv).text = item.Name
        val start = item.StartTime.substring(0 until 10)
        val end = item.EndTime.substring(0 until 10)
        vh.itemView.findViewById<TextView>(R.id.weeklyDate).text = "$start ~ $end"
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
            itemView.setOnClickListener{
                listener?.webMove(position)
            }
        }
    }
}