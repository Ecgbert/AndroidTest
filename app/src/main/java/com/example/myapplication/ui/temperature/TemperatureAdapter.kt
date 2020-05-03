package com.example.myapplication.ui.temperature

import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseDelegateMultiAdapter
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.example.myapplication.R
import com.example.myapplication.vo.Image
import com.example.myapplication.vo.TempModel
import com.example.myapplication.vo.temperature.Time

class TemperatureAdapter : BaseDelegateMultiAdapter<TempModel, BaseViewHolder>() {


    init {
        setMultiTypeDelegate(object : BaseMultiTypeDelegate<TempModel>() {
            override fun getItemType(data: List<TempModel>, position: Int): Int {
                return when (position % 2) {
                    0 -> TempModel.typeA
                    1 -> TempModel.typeB
                    else -> 0
                }
            }
        })
        getMultiTypeDelegate()?.apply {
            addItemType(TempModel.typeA, R.layout.temperature_item).addItemType(
                TempModel.typeB,
                R.layout.image_item
            )
        }
    }

    override fun convert(holder: BaseViewHolder, item: TempModel) {

        when (holder.itemViewType) {
            TempModel.typeA -> {
                val time = item.data
                if (time is Time) {
                    holder.setText(
                        R.id.tv_temperature,
                        "${time.startTime} \n${time.endTime} \n${time.parameter.parameterName}${time.parameter.parameterUnit}"
                    )
                }
            }
            TempModel.typeB -> {
                val image = item.data
                if (image is Image) {
                    Glide.with(context).load(image.imageResourceId)
                        .into(holder.getView(R.id.iv_rvItem))
                }
            }
        }
    }
}
