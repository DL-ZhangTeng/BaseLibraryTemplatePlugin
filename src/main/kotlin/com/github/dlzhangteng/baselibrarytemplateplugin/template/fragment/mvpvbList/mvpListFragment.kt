package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvpvbList

import com.github.dlzhangteng.baselibrarytemplateplugin.template.DependencyInjectionEnum

fun mvpVbListFragment(
    mRootPackageName: String?,
    mFragmentPackageName: String,
    mPageName: String,
    mFragmentLayoutName: String,
    mBeanClass: String,
    mAdapterClass: String,
    mDependencyInjectionEnum: DependencyInjectionEnum,
) = """
package ${mRootPackageName}${mFragmentPackageName.ifEmpty { "" }}

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.zhangteng.base.base.BaseAdapter
import com.zhangteng.mvp.mvp.vb.BaseListMvpFragment
import ${mRootPackageName}.R
import ${mRootPackageName}.databinding.Fragment${mPageName}Binding
import ${mRootPackageName}.mvp.model.imodel.I${mPageName}FragmentModel
import ${mRootPackageName}.mvp.presenter.${mPageName}FragmentPresenter
import ${mRootPackageName}.mvp.presenter.ipresenter.I${mPageName}FragmentPresenter
import ${mRootPackageName}.mvp.view.I${mPageName}FragmentView
import ${mRootPackageName}.bean.${mBeanClass}
import ${mRootPackageName}.adapter.${mAdapterClass}
${
    if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT) """
    import dagger.hilt.android.AndroidEntryPoint
    import javax.inject.Inject

    @AndroidEntryPoint
""" else """
    
"""
}
class ${mPageName}Fragment : BaseListMvpFragment<Fragment${mPageName}Binding, I${mPageName}FragmentView, I${mPageName}FragmentModel, I${mPageName}FragmentPresenter, ${mBeanClass}, BaseAdapter.DefaultViewHolder, ${mAdapterClass}>() , I${mPageName}FragmentView {
    
${
    if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT) """
    @Inject
    override lateinit var mPresenter: I${mPageName}FragmentPresenter
"""
    else """
    override var mPresenter: I${mPageName}FragmentPresenter = ${mPageName}FragmentPresenter()
"""
}
    
    companion object {
        fun newInstance() = ${mPageName}Fragment()
    }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(context).inflate(R.layout.${mFragmentLayoutName}, container, false)
    }

	override fun initView(view: View, savedInstanceState: Bundle?) {
	    super.initView(view, savedInstanceState)

	}

    override fun initData(savedInstanceState: Bundle?) {
        
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