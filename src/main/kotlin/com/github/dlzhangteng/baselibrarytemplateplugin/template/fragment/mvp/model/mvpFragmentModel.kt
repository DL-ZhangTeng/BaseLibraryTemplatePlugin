package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvp.model

import com.github.dlzhangteng.baselibrarytemplateplugin.template.DependencyInjectionEnum

fun mvpFragmentModel(
    mRootPackageName: String?,
    mPageName: String,
    mDependencyInjectionEnum: DependencyInjectionEnum
) = """
package ${mRootPackageName}.mvp.model

import com.zhangteng.mvp.base.BaseModel
import ${mRootPackageName}.mvp.model.imodel.I${mPageName}FragmentModel
${
    if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT)
"""import javax.inject.Inject
    
""" else """
    
"""
}
class ${mPageName}FragmentModel ${if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT) "@Inject constructor() " else ""}: BaseModel(), I${mPageName}FragmentModel
"""