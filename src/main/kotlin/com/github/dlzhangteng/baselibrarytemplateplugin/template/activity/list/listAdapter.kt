package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.list


fun listAdapter(
    mRootPackageName: String?,
    mPageName: String,
    mBeanClass: String,
    mAdapterClass: String,
) = """
package ${mRootPackageName}.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.zhangteng.base.base.BaseAdapter
import ${mRootPackageName}.R

import ${mRootPackageName}.bean

class ${mAdapterClass} : BaseAdapter<${mBeanClass}, BaseAdapter.DefaultViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultViewHolder {
        return DefaultViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_${getLayoutName(mPageName)}, parent, false)
        )
    }
}
"""
