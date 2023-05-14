package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvi

import com.github.dlzhangteng.baselibrarytemplateplugin.template.DependencyInjectionEnum

fun mviFragmentViewModel(
    mRootPackageName: String?,
    mPageName: String,
    mDependencyInjectionEnum: DependencyInjectionEnum
) = """
package ${mRootPackageName}.mvi.vm

import com.zhangteng.mvvm.base.BaseViewModel
import ${mRootPackageName}.mvi.repository.${mPageName}FragmentRepository
${
    if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT)
        """import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
""" else """
    
"""
}
class ${mPageName}FragmentViewModel ${if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT) "@Inject constructor() " else ""}: BaseViewModel() {

${
    if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT) """
    @Inject
    override lateinit var mRepository: ${mPageName}FragmentRepository
"""
    else """
    override var mRepository: ${mPageName}Repository = ${mPageName}FragmentRepository()
"""
}

}
"""