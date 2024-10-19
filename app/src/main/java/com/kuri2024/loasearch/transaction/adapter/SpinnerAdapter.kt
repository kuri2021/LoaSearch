package com.kuri2024.loasearch.transaction.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.kuri2024.loasearch.R

class SpinnerAdapter(context:Context, design:Int, list: List<String>):
    ArrayAdapter<String>(context, design, list) {
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: layoutInflater.inflate(R.layout.spinner_item, parent, false)
        val textView = view.findViewById<TextView>(R.id.spinner_tv)
        textView.text = getItem(position)
        return view
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: layoutInflater.inflate(R.layout.spinner_item, parent, false)
        val textView = view.findViewById<TextView>(R.id.spinner_tv)
        textView.text = getItem(position)
        return view
    }
}