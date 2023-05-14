package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvi

import com.github.dlzhangteng.baselibrarytemplateplugin.template.DependencyInjectionEnum

fun mviActivityViewModel(
    mRootPackageName: String?,
    mPageName: String,
    mDependencyInjectionEnum: DependencyInjectionEnum
) = """
package ${mRootPackageName}.mvi.vm

import com.zhangteng.mvvm.base.BaseViewModel
import ${mRootPackageName}.mvi.repository.${mPageName}Repository
${
    if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT)
        """import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel""" else """
    
"""
}
class ${mPageName}ViewModel ${if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT) "@Inject constructor() " else ""}: BaseViewModel() {

${
    if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT) """
    @Inject
    lateinit var mRepository: ${mPageName}Repository
"""
    else """
    val mRepository by lazy {
        ${mPageName}Repository()
    }
"""
}

}
"""