package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvp

import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.base.getLayoutName

fun mvpFragment(
    mRootPackageName: String?,
    mActivityPackageName: String,
    mPageName: String
) = """
package ${mRootPackageName}${if (mActivityPackageName.isNullOrEmpty()) "" else ".${mActivityPackageName}"}

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.zhangteng.mvp.mvp.BaseMvpFragment
import ${mRootPackageName}.mvp.model.imodel.I${mPageName}FragmentModel
import ${mRootPackageName}.mvp.presenter.${mPageName}FragmentPresenter
import ${mRootPackageName}.mvp.presenter.ipresenter.I${mPageName}FragmentPresenter
import ${mRootPackageName}.mvp.view.I${mPageName}FragmentView
import ${mRootPackageName}.R

class ${mPageName}Fragment : BaseMvpFragment<I${mPageName}FragmentView, I${mPageName}FragmentModel, I${mPageName}FragmentPresenter>() , I${mPageName}FragmentView {
    
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
}
"""