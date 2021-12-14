package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvpList

import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.base.getLayoutName

fun mvpListFragment(
    mRootPackageName: String?,
    mActivityPackageName: String,
    mPageName: String
) = """
package ${mRootPackageName}${if (mActivityPackageName.isNullOrEmpty()) "" else ".${mActivityPackageName}"}

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.zhangteng.base.base.BaseListMvpFragment
import ${mRootPackageName}.adapter.${mPageName}Adapter
import ${mRootPackageName}.bean.${mPageName}Bean
import ${mRootPackageName}.mvp.model.imodel.I${mPageName}FragmentModel
import ${mRootPackageName}.mvp.presenter.${mPageName}FragmentPresenter
import ${mRootPackageName}.mvp.presenter.ipresenter.I${mPageName}FragmentPresenter
import ${mRootPackageName}.mvp.view.I${mPageName}FragmentView
import ${mRootPackageName}.R

class ${mPageName}Fragment : BaseListMvpFragment<I${mPageName}FragmentView, I${mPageName}FragmentModel, I${mPageName}FragmentPresenter, ${mPageName}Bean, ${mPageName}Adapter>() , I${mPageName}FragmentView {
    
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
    
    override fun createAdapter(): ${mPageName}Adapter {
        return ${mPageName}Adapter()
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