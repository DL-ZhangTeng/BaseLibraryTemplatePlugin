package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvp.presenter

import com.github.dlzhangteng.baselibrarytemplateplugin.template.DependencyInjectionEnum

fun mvpPresenter(
    mRootPackageName: String?,
    mPageName: String,
    mDependencyInjectionEnum: DependencyInjectionEnum,
) = """
package ${mRootPackageName}.mvp.presenter

import com.zhangteng.mvp.base.BasePresenter
${
    if (mDependencyInjectionEnum != DependencyInjectionEnum.HILT) """import ${mRootPackageName}.mvp.model.${mPageName}Model""" else ""
}
import ${mRootPackageName}.mvp.model.imodel.I${mPageName}Model
import ${mRootPackageName}.mvp.presenter.ipresenter.I${mPageName}Presenter
import ${mRootPackageName}.mvp.view.I${mPageName}View
${
    if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT) 
"""import javax.inject.Inject
    
""" else """
    
"""
}
class ${mPageName}Presenter ${if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT) "@Inject constructor() " else ""}: BasePresenter<I${mPageName}View, I${mPageName}Model>(), I${mPageName}Presenter {

${
    if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT) """
    @Inject
    override lateinit var mModel: I${mPageName}Model
"""
    else """
    override var mModel: I${mPageName}Model = ${mPageName}Model()
"""
}
 
}
"""