package com.github.dlzhangteng.baselibrarytemplateplugin.template.other.bindingadapter


fun bindingAdapter(
    mRootPackageName: String?,
    mAdapterLayoutName: String,
    mBeanClass: String,
    mAdapterClass: String,
) = """
package ${mRootPackageName}.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.zhangteng.mvvm.adapter.BindingAdapter
import ${mRootPackageName}.R

import ${mRootPackageName}.bean.${mBeanClass}

class ${mAdapterClass} : BindingAdapter<${mBeanClass}, BindingAdapter.BindingViewHolder<${mBeanClass}>> {

    constructor() : super()

    constructor(data: MutableList<${mBeanClass}?>?) : super(data)
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<${mBeanClass}> {
        return BindingViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.${mAdapterLayoutName}, parent, false)
        )
    }
}
"""
