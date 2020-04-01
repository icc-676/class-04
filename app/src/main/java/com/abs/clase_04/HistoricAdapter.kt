package com.abs.clase_04

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.historic_cell.view.*

class HistoricAdapter(private val historicList: List<HistoricItem>) :
    RecyclerView.Adapter<HistoricAdapter.HistoricViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoricViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.historic_cell, parent, false)
        return HistoricViewHolder(item)
    }

    override fun getItemCount(): Int {
        return historicList.count()
    }

    override fun onBindViewHolder(holder: HistoricViewHolder, position: Int) {
        val currentItem = historicList[position]
        holder.bindHistoric(currentItem)
    }

    class HistoricViewHolder(v: View) : RecyclerView.ViewHolder(v){
        private var view: View = v
        private var item: HistoricItem? = null

        init {}

        fun bindHistoric(item: HistoricItem) {
            this.item = item
            view.operationTextView.text = item.operation
            view.resultTextView.text = item.result
        }
    }
}


data class HistoricItem(val operation: String, val result: String) {}