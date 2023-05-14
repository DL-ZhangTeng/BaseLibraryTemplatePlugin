package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvvmdbList

import com.github.dlzhangteng.baselibrarytemplateplugin.template.DependencyInjectionEnum

fun mvvmDbListFragment(
    mRootPackageName: String?,
    mFragmentPackageName: String,
    mPageName: String,
    mFragmentLayoutName: String,
    mBeanClass: String,
    mAdapterClass: String,
    mDependencyInjectionEnum: DependencyInjectionEnum
) = """
package ${mRootPackageName}${mFragmentPackageName.ifEmpty { "" }}

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.zhangteng.mvvm.adapter.BindingAdapter
import com.zhangteng.mvvm.mvvm.db.BaseListMvvmFragment
import ${mRootPackageName}.R
import ${mRootPackageName}.databinding.Fragment${mPageName}Binding
import ${mRootPackageName}.mvvm.vm.${mPageName}FragmentViewModel
import ${mRootPackageName}.bean.${mBeanClass}
import ${mRootPackageName}.adapter.${mAdapterClass}
${
    if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT)
        """import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint""" else
        """
    
"""
}
class ${mPageName}Fragment : BaseListMvvmFragment<Fragment${mPageName}Binding, ${mPageName}FragmentViewModel, ${mBeanClass}, BindingAdapter.BindingViewHolder<${mBeanClass}>, ${mAdapterClass}>(){

    companion object {
        fun newInstance() = ${mPageName}Fragment()
    }
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.${mFragmentLayoutName}, container, false)
    }
    
    override fun initView(view: View, savedInstanceState: Bundle?) {
        super.initView(view, savedInstanceState)
    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
    }

    override fun createAdapter(): $mAdapterClass {
        return ${mAdapterClass}(mList)
    }

    override fun getRecyclerView(): RecyclerView? {
        return view?.findViewById(R.id.recyclerView)
    }

    override fun getSmartRefreshLayout(): SmartRefreshLayout? {
        return view?.findViewById(R.id.smartRefreshLayout)
    }

    override fun loadData(i: Int) {}
    
    override fun setLayoutManager() {
        setLinearLayoutManager(LinearLayoutManager.VERTICAL)
    }
}
"""