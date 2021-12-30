package com.github.dlzhangteng.baselibrarytemplateplugin.template.other.tree


fun treeAdapter(
    mRootPackageName: String?,
    mAdapterLayoutName: String,
    mBeanClass: String,
    mAdapterClass: String,
) = """
package ${mRootPackageName}.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.zhangteng.base.adapter.TreeRecyclerViewAdapter
import com.zhangteng.base.tree.Node
import ${mRootPackageName}.R
import ${mRootPackageName}.bean.${mBeanClass}

class ${mAdapterClass}(
    data: MutableList<${mBeanClass}?>?,
    defaultExpandLevel: Int
) : TreeRecyclerViewAdapter<${mBeanClass}>(data, defaultExpandLevel) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DefaultViewHolder {
        return DefaultViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.${mAdapterLayoutName}, parent, false)
        )
    }
    
    override fun onBindViewHolder(
        holder: DefaultViewHolder?,
        item: ${mBeanClass}?,
        position: Int,
        node: Node?
    ) {

    }
}
"""
