package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvmdb

fun mvvmDbActivityViewModel(
    mRootPackageName: String?,
    mPageName: String
) = """
package ${mRootPackageName}.mvvm.vm

import com.zhangteng.base.mvvm.base.BaseViewModel
import ${mRootPackageName}.mvvm.repository.${mPageName}DbRepository

class ${mPageName}DbViewModel : BaseViewModel() {
    private val mRepository by lazy { ${mPageName}DbRepository() }
}
"""