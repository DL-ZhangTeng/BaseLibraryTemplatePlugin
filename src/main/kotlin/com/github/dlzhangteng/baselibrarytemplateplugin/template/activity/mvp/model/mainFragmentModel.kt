package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvp.model

import com.github.dlzhangteng.baselibrarytemplateplugin.template.DependencyInjectionEnum

fun mvpModel(
    mRootPackageName: String?,
    mPageName: String,
    mDependencyInjectionEnum: DependencyInjectionEnum
) = """
package ${mRootPackageName}.mvp.model

import com.zhangteng.mvp.base.BaseModel
import ${mRootPackageName}.mvp.model.imodel.I${mPageName}Model
${
    if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT) """
    import javax.inject.Inject
    
""" else """
    
"""
}
class ${mPageName}Model ${if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT) "@Inject constructor() " else ""}: BaseModel(), I${mPageName}Model
"""