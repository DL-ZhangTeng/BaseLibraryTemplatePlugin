package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvvm

fun mvvmFragmentViewModel(
    mRootPackageName: String?,
    mPageName: String
) = """
package ${mRootPackageName}.mvvm.vm

import com.zhangteng.mvvm.base.BaseViewModel
import ${mRootPackageName}.mvvm.repository.${mPageName}FragmentRepository

class ${mPageName}FragmentViewModel : BaseViewModel() {
    private val mRepository by lazy { ${mPageName}FragmentRepository() }
}
"""