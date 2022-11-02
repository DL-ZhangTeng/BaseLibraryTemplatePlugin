package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.list

import com.github.dlzhangteng.baselibrarytemplateplugin.template.getLayoutName


fun listActivity(
    mRootPackageName: String?,
    mActivityPackageName: String,
    mPageName: String,
    mBeanClass: String,
    mAdapterClass: String,
) = """
package ${mRootPackageName}${mActivityPackageName.ifEmpty { "" }}

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ${mRootPackageName}.R
import com.zhangteng.base.base.BaseListActivity
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import ${mRootPackageName}.bean.${mBeanClass}
import ${mRootPackageName}.adapter.${mAdapterClass}

class ${mPageName}Activity : BaseListActivity<${mBeanClass}, ${mAdapterClass}>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setContentView(R.layout.activity${getLayoutName(mPageName)})
    }

    override fun initView() {
        super.initView()

    }

    override fun initData() {}
    
    override fun createAdapter(): ${mAdapterClass} {
        return ${mAdapterClass}(mList)
    }

    override fun getRecyclerView(): RecyclerView {
        return findViewById(R.id.recyclerView)
    }

    override fun getSmartRefreshLayout(): SmartRefreshLayout {
        return findViewById(R.id.smartRefreshLayout)
    }

    override fun loadData(i: Int) {}
    
    override fun setLayoutManager() {
        setLinearLayoutManager(LinearLayoutManager.VERTICAL)
    }
}
"""
