package com.usetsai.widgetsize

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import android.widget.TextView

class DataItemViewHolder(itemView: View) : ItemAdapter.ItemViewHolder<DataItemHolder>(itemView) {

    private val text: TextView = itemView.findViewById(R.id.text) as TextView
    private val check: Switch = itemView.findViewById(R.id.check) as Switch

    init {
        text.setOnClickListener { _ ->
            Log.i(TAG,"text click id: ${itemHolder?.item?.id}, text: ${itemHolder?.item?.text}")
            notifyItemClicked(CLICK_NORMAL)
        }
        check.setOnCheckedChangeListener { _, isChecked ->
            itemHolder?.item?.check = isChecked
        }
    }

    override fun onBindItemView(itemHolder: DataItemHolder) {
        super.onBindItemView(itemHolder)
        val item = itemHolder.item
        text.text = item.text
        check.isChecked = item.check
    }

    class Factory(private val layoutInflater: LayoutInflater) : ItemAdapter.ItemViewHolder.Factory {
        override fun createViewHolder(parent: ViewGroup, viewType: Int): ItemAdapter.ItemViewHolder<*> {
            return DataItemViewHolder(layoutInflater.inflate(
                viewType, parent, false /* attachToRoot */))
        }
    }

    companion object {
        @JvmField
        val VIEW_TYPE: Int = R.layout.item_layout
        const val CLICK_NORMAL = 0
        const val TAG = "DataItemViewHolder"
    }
}