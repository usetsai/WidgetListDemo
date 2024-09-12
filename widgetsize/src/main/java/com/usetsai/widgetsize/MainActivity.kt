package com.usetsai.widgetsize

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        val layoutInflater = layoutInflater
        val adapter: ItemAdapter<DataItemHolder> = ItemAdapter()
        adapter.setHasStableIds()
        adapter.withViewTypes(
            DataItemViewHolder.Factory(layoutInflater),
            ItemAdapter.OnItemClickedListener { viewHolder: ItemAdapter.ItemViewHolder<*>, id: Int ->
                if (id == DataItemViewHolder.CLICK_NORMAL) {
                    val info = viewHolder.itemHolder!!.item as DataItemInfo
                    if (info.clazz == null) {
                        PinShortcutUtils.requestPinShortcut(this@MainActivity, "MainActivity_Shortcut")
                    } else {
                        PinWidgetUtils.requestPinAppWidget(this@MainActivity, info.clazz, info.check)
                    }
                }
            },
            DataItemViewHolder.VIEW_TYPE
        )
        val dataItemInfoList = buildData()
        val itemHolders: MutableList<DataItemHolder> = ArrayList(dataItemInfoList.size)
        for (dataItemInfo in dataItemInfoList) {
            itemHolders.add(DataItemHolder(dataItemInfo))
        }
        adapter.setItems(itemHolders)
        recyclerView.adapter = adapter
    }

    private fun buildData(): List<DataItemInfo> {
        val dataItemInfoList: MutableList<DataItemInfo> = ArrayList()
        dataItemInfoList.add(DataItemInfo(0, "add shortcut", false, null))
        dataItemInfoList.add(DataItemInfo(1, "add Widget 1x1", false, WidgetProvider1x1::class.java))
        dataItemInfoList.add(DataItemInfo(2, "add Widget 1x2", false, WidgetProvider1x2::class.java))
        dataItemInfoList.add(DataItemInfo(3, "add Widget 1x3", false, WidgetProvider1x3::class.java))
        dataItemInfoList.add(DataItemInfo(4, "add Widget 2x1", false, WidgetProvider2x1::class.java))
        dataItemInfoList.add(DataItemInfo(5, "add Widget 2x2", false, WidgetProvider2x2::class.java))
        dataItemInfoList.add(DataItemInfo(6, "add Widget 2x3", false, WidgetProvider2x3::class.java))
        dataItemInfoList.add(DataItemInfo(7, "add Widget 3x1", false, WidgetProvider3x1::class.java))
        dataItemInfoList.add(DataItemInfo(8, "add Widget 3x2", false, WidgetProvider3x2::class.java))
        dataItemInfoList.add(DataItemInfo(9, "add Widget 3x3", false, WidgetProvider3x3::class.java))
        dataItemInfoList.add(DataItemInfo(10, "add Widget 4x1", false, WidgetProvider4x1::class.java))
        dataItemInfoList.add(DataItemInfo(12, "add Widget 4x2", false, WidgetProvider4x2::class.java))
        dataItemInfoList.add(DataItemInfo(13, "add Widget 4x3", false, WidgetProvider4x3::class.java))
        dataItemInfoList.add(DataItemInfo(14, "add Widget 4x4", false, WidgetProvider4x4::class.java))
        dataItemInfoList.add(DataItemInfo(15, "add Widget 5x2", false, WidgetProvider5x2::class.java))
        return dataItemInfoList
    }
}