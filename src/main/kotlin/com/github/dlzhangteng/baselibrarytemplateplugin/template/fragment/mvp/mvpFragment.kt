package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvp

import com.github.dlzhangteng.baselibrarytemplateplugin.template.DependencyInjectionEnum

fun mvpFragment(
    mRootPackageName: String?,
    mFragmentPackageName: String,
    mPageName: String,
    mFragmentLayoutName: String,
    mDependencyInjectionEnum: DependencyInjectionEnum,
) = """
package ${mRootPackageName}${mFragmentPackageName.ifEmpty { "" }}

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.zhangteng.mvp.mvp.BaseMvpFragment
import ${mRootPackageName}.mvp.model.imodel.I${mPageName}FragmentModel
${
    if (mDependencyInjectionEnum != DependencyInjectionEnum.HILT) """import ${mRootPackageName}.mvp.presenter.${mPageName}FragmentPresenter""" else ""
}
import ${mRootPackageName}.mvp.presenter.ipresenter.I${mPageName}FragmentPresenter
import ${mRootPackageName}.mvp.view.I${mPageName}FragmentView
import ${mRootPackageName}.R
${
    if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT)
        """import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint""" else
        """
    
"""
}
class ${mPageName}Fragment : BaseMvpFragment<I${mPageName}FragmentView, I${mPageName}FragmentModel, I${mPageName}FragmentPresenter>() , I${mPageName}FragmentView {
    
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
}
"""