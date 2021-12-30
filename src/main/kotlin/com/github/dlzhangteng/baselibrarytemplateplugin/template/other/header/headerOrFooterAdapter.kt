package com.github.dlzhangteng.baselibrarytemplateplugin.template.other.header


fun headerOrFooterAdapter(
    mRootPackageName: String?,
    mBeanClass: String,
    mAdapterClass: String,
) = """
package ${mRootPackageName}.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.zhangteng.base.adapter.HeaderOrFooterAdapter
import com.zhangteng.base.base.BaseAdapter
import ${mRootPackageName}.bean.${mBeanClass}

class ${mAdapterClass}(mInnerAdapter: BaseAdapter<${mBeanClass}, DefaultViewHolder>) :
    HeaderOrFooterAdapter<${mBeanClass}>(mInnerAdapter) {
    override fun createHeaderOrFooterViewHolder(
        parent: ViewGroup?,
        viewInt: Int?
    ): DefaultViewHolder {
        return DefaultViewHolder(
            LayoutInflater.from(parent?.context).inflate(viewInt!!, parent, false)
        )
    }

    override fun onBindHeaderOrFooterViewHolder(
        holder: DefaultViewHolder,
        item: ${mBeanClass}?,
        viewType: Int
    ) {

    }
}
"""
