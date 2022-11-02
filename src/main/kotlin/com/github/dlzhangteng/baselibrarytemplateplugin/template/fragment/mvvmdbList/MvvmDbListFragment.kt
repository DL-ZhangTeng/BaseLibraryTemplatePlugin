package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvvmdbList

import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.base.getLayoutName

fun mvvmDbListFragment(
    mRootPackageName: String?,
    mActivityPackageName: String,
    mPageName: String,
    mBeanClass: String,
    mAdapterClass: String,
) = """
package ${mRootPackageName}${mActivityPackageName.ifEmpty { "" }}

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.zhangteng.mvvm.mvvm.BaseListMvvmDbFragment
import ${mRootPackageName}.R
import ${mRootPackageName}.databinding.Fragment${mPageName}DbBinding
import ${mRootPackageName}.mvvm.vm.${mPageName}DbFragmentViewModel
import ${mRootPackageName}.bean.${mBeanClass}
import ${mRootPackageName}.adapter.${mAdapterClass}

class ${mPageName}DbFragment : BaseListMvvmDbFragment<${mPageName}DbFragmentViewModel, Fragment${mPageName}DbBinding, ${mBeanClass}, ${mAdapterClass}>(){

    companion object {
        fun newInstance() = ${mPageName}DbFragment()
    }
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment${getLayoutName(mPageName)}_db, container, false)
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