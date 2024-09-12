package com.usetsai.widgetsize

class DataItemHolder(item: DataItemInfo) : ItemAdapter.ItemHolder<DataItemInfo>(item, item.id) {
    override fun getItemViewType(): Int {
        return DataItemViewHolder.VIEW_TYPE
    }
}