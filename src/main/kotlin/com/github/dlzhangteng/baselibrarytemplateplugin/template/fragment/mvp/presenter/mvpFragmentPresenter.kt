package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvp.presenter

import com.github.dlzhangteng.baselibrarytemplateplugin.template.DependencyInjectionEnum

fun mvpFragmentPresenter(
    mRootPackageName: String?,
    mPageName: String,
    mDependencyInjectionEnum: DependencyInjectionEnum
) = """
package ${mRootPackageName}.mvp.presenter

import com.zhangteng.mvp.base.BasePresenter
${
    if (mDependencyInjectionEnum != DependencyInjectionEnum.HILT) """import ${mRootPackageName}.mvp.model.${mPageName}FragmentModel""" else ""
}
import ${mRootPackageName}.mvp.model.imodel.I${mPageName}FragmentModel
import ${mRootPackageName}.mvp.presenter.ipresenter.I${mPageName}FragmentPresenter
import ${mRootPackageName}.mvp.view.I${mPageName}FragmentView
${
    if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT)
        """import javax.inject.Inject
    
""" else """
    
"""
}
class ${mPageName}FragmentPresenter ${if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT) "@Inject constructor() " else ""}: BasePresenter<I${mPageName}FragmentView, I${mPageName}FragmentModel>(), I${mPageName}FragmentPresenter {

    override var mModel: I${mPageName}FragmentModel = ${mPageName}FragmentModel()
    
}
"""