package com.github.dlzhangteng.baselibrarytemplateplugin.template.other.adapter


fun baseAdapter(
    mRootPackageName: String?,
    mAdapterLayoutName: String,
    mBeanClass: String,
    mAdapterClass: String,
) = """
package ${mRootPackageName}.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.zhangteng.base.base.BaseAdapter
import ${mRootPackageName}.R

import ${mRootPackageName}.bean.${mBeanClass}

class ${mAdapterClass} : BaseAdapter<${mBeanClass}, BaseAdapter.DefaultViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultViewHolder {
        return DefaultViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.${mAdapterLayoutName}, parent, false)
        )
    }
    
    override fun onBindViewHolder(holder: DefaultViewHolder, item: ${mBeanClass}?, position: Int) {
        
    }
}
"""
