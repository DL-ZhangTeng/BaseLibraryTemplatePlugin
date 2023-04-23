package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvi

fun mviActivityViewModel(
    mRootPackageName: String?,
    mPageName: String
) = """
package ${mRootPackageName}.mvi.vm

import com.zhangteng.mvvm.base.BaseViewModel
import ${mRootPackageName}.mvi.repository.${mPageName}Repository

class ${mPageName}ViewModel : BaseViewModel() {
    private val mRepository by lazy { ${mPageName}Repository() }
}
"""