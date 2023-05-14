package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvvm

import com.github.dlzhangteng.baselibrarytemplateplugin.template.DependencyInjectionEnum

fun mvvmFragmentViewModel(
    mRootPackageName: String?,
    mPageName: String,
    mDependencyInjectionEnum: DependencyInjectionEnum
) = """
package ${mRootPackageName}.mvvm.vm

import com.zhangteng.mvvm.base.BaseViewModel
import ${mRootPackageName}.mvvm.repository.${mPageName}FragmentRepository
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