package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvmdbList

import com.github.dlzhangteng.baselibrarytemplateplugin.template.DependencyInjectionEnum
import com.github.dlzhangteng.baselibrarytemplateplugin.template.getPageName

fun mvvmDbListActivity(
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
import com.zhangteng.mvvm.adapter.BindingAdapter
import com.zhangteng.mvvm.mvvm.db.BaseListMvvmActivity
import ${mRootPackageName}.R
import ${mRootPackageName}.adapter.${mPageName}Adapter
import ${mRootPackageName}.bean.${mPageName}Bean
import ${mRootPackageName}.databinding.${getPageName(mActivityLayoutName)}Binding
import ${mRootPackageName}.mvvm.vm.${mPageName}ViewModel
${
    if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT)
        """import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint""" else
        """
    
"""
}
class ${mPageName}Activity : BaseListMvvmActivity<${getPageName(mActivityLayoutName)}Binding, ${mPageName}ViewModel, ${mBeanClass}, BindingAdapter.BindingViewHolder<${mBeanClass}>, ${mAdapterClass}>() {

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
        return mDatabind.recyclerView
    }

    override fun getSmartRefreshLayout(): SmartRefreshLayout {
        return mDatabind.smartRefreshLayout
    }

    override fun loadData(i: Int) {}

    override fun setLayoutManager() {
        setLinearLayoutManager(LinearLayoutManager.VERTICAL)
    }
}
"""