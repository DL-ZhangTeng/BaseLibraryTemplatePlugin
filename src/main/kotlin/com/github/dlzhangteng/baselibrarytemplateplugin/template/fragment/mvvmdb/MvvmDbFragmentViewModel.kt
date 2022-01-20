package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvvmdb

fun mvvmDbFragmentViewModel(
    mRootPackageName: String?,
    mPageName: String
) = """
package ${mRootPackageName}.mvvm.vm

import com.zhangteng.mvvm.base.BaseViewModel
import ${mRootPackageName}.mvvm.repository.${mPageName}DbFragmentRepository

class ${mPageName}DbFragmentViewModel : BaseViewModel() {
    private val mRepository by lazy { ${mPageName}DbFragmentRepository() }
}
"""