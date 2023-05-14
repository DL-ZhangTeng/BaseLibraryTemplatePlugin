package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvm

import com.github.dlzhangteng.baselibrarytemplateplugin.template.DependencyInjectionEnum

fun mvvmActivityViewModel(
    mRootPackageName: String?,
    mPageName: String,
    mDependencyInjectionEnum: DependencyInjectionEnum
) = """
package ${mRootPackageName}.mvvm.vm

import com.zhangteng.mvvm.base.BaseViewModel
import ${mRootPackageName}.mvvm.repository.${mPageName}Repository
${
    if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT)
        """import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
""" else """
    
"""
}
class ${mPageName}ViewModel ${if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT) "@Inject constructor() " else ""}: BaseViewModel() {

${
    if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT) """
    @Inject
    override lateinit var mRepository: ${mPageName}Repository
"""
    else """
    override var mRepository: ${mPageName}Repository = ${mPageName}Repository()
"""
}

}
"""