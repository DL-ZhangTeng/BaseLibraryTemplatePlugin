package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvm

fun mvvmActivityViewModel(
    mRootPackageName: String?,
    mPageName: String
) = """
package ${mRootPackageName}.mvvm.vm

import com.zhangteng.base.mvvm.base.BaseViewModel
import ${mRootPackageName}.mvvm.repository.${mPageName}Repository

class ${mPageName}ViewModel : BaseViewModel() {
    private val mRepository by lazy { ${mPageName}Repository() }
}
"""