package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvivbList

import com.github.dlzhangteng.baselibrarytemplateplugin.template.DependencyInjectionEnum

fun mviVbListActivity(
    mRootPackageName: String?,
    mActivityPackageName: String,
    mPageName: String,
    mActivityLayoutName: String,
    mBeanClass: String,
    mAdapterClass: String,
    mDependencyInjectionEnum: DependencyInjectionEnum,
) = """
package ${mRootPackageName}${mActivityPackageName.ifEmpty { "" }}

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.zhangteng.base.base.BaseAdapter
import com.zhangteng.mvvm.mvi.vb.BaseListMviActivity
import ${mRootPackageName}.R
import ${mRootPackageName}.databinding.Activity${mPageName}Binding
import ${mRootPackageName}.adapter.${mPageName}Adapter
import ${mRootPackageName}.bean.${mPageName}Bean
import ${mRootPackageName}.mvi.vm.${mPageName}ViewModel
${
    if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT)
        """import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint""" else
        """
    
"""
}
class ${mPageName}Activity : BaseListMviActivity<Activity${mPageName}Binding, ${mPageName}ViewModel, ${mBeanClass}, BaseAdapter.DefaultViewHolder, ${mAdapterClass}>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.${mActivityLayoutName})
    }

    override fun initView() {
        super.initView()
       
    }

    override fun initData() {}

    override fun createAdapter(): $mAdapterClass {
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