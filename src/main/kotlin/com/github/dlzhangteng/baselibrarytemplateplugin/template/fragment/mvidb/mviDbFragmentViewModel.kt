package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvidb

fun mviDbFragmentViewModel(
    mRootPackageName: String?,
    mPageName: String
) = """
package ${mRootPackageName}.mvi.vm

import com.zhangteng.mvvm.base.BaseViewModel
import ${mRootPackageName}.mvi.repository.${mPageName}FragmentRepository

class ${mPageName}FragmentViewModel : BaseViewModel() {
    private val mRepository by lazy { ${mPageName}FragmentRepository() }
}
"""