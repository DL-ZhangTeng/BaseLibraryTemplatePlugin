package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvpList

import com.github.dlzhangteng.baselibrarytemplateplugin.template.getLayoutName

fun mvpListFragment(
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
import ${mRootPackageName}.R
import com.zhangteng.mvp.mvp.BaseListMvpFragment
import ${mRootPackageName}.mvp.model.imodel.I${mPageName}FragmentModel
import ${mRootPackageName}.mvp.presenter.${mPageName}FragmentPresenter
import ${mRootPackageName}.mvp.presenter.ipresenter.I${mPageName}FragmentPresenter
import ${mRootPackageName}.mvp.view.I${mPageName}FragmentView
import ${mRootPackageName}.bean.${mBeanClass}
import ${mRootPackageName}.adapter.${mAdapterClass}

class ${mPageName}Fragment : BaseListMvpFragment<I${mPageName}FragmentView, I${mPageName}FragmentModel, I${mPageName}FragmentPresenter, ${mBeanClass}, ${mAdapterClass}>() , I${mPageName}FragmentView {
    
    companion object {
        fun newInstance() = ${mPageName}Fragment()
    }
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return LayoutInflater.from(context).inflate(R.layout.fragment${getLayoutName(mPageName)}, container, false)
    }

    /**
    *return Proxy.newProxyInstance(${mPageName}FragmentPresenter::class.java.classLoader, arrayOf(I${mPageName}FragmentPresenter::class.java), LoadingPresenterHandler(${mPageName}FragmentPresenter())) as I${mPageName}FragmentPresenter
    */
	override fun createPresenter():I${mPageName}FragmentPresenter? {
        return ${mPageName}FragmentPresenter()
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