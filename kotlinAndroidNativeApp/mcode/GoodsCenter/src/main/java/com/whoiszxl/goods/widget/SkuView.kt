package com.whoiszxl.goods.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.eightbitlab.rxbus.Bus
import com.whoiszxl.goods.R
import com.whoiszxl.goods.common.GoodsConstant
import com.whoiszxl.goods.data.protocol.GoodsSku
import com.whoiszxl.goods.event.SkuChangedEvent
import com.zhy.view.flowlayout.FlowLayout
import com.zhy.view.flowlayout.TagAdapter
import kotlinx.android.synthetic.main.layout_sku_view.view.*

/**
 * 单个SKU
 */
class SkuView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyle: Int = 0) : FrameLayout(context, attrs, defStyle) {

    private lateinit var mGoodsSku: GoodsSku

    init {
        View.inflate(context,R.layout.layout_sku_view, this)
    }

    /*
        TODO 这个先写死来，暂时没有SKU功能
        动态设置SKU数据
     */
    fun setSkuData(goodsSku: GoodsSku) {
        mGoodsSku = goodsSku
        //mSkuTitleTv.text = goodsSku.skuTitle
        mSkuTitleTv.text = "一件"

        //FlowLayout设置数据
        mSkuContentView.adapter = object :TagAdapter<String>(arrayListOf("一件")){
            override fun getView(parent: FlowLayout?, position: Int, t: String?): View {
                val view = LayoutInflater.from(context)
                        .inflate(R.layout.layout_sku_item,parent,false) as TextView
                view.text = t
                return view
            }
        }

        mSkuContentView.adapter.setSelectedList(0)

        mSkuContentView.setOnTagClickListener { _, _, _ ->
            Bus.send(SkuChangedEvent())
            true
        }
    }

    /*
        获取选中的SKU
     */
    fun getSkuInfo(): String {
        return mSkuTitleTv.text.toString() + GoodsConstant.SKU_SEPARATOR +
                mGoodsSku.skuContent[mSkuContentView.selectedList.first()]
    }
}