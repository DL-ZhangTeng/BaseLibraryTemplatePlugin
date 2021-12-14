package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvmdb

fun mvvmDbActivityViewModel(
    mRootPackageName: String?,
    mPageName: String
) = """
package ${mRootPackageName}.mvvm.vm

import com.zhangteng.base.mvvm.base.BaseViewModel

class ${mPageName}DbViewModel : BaseViewModel()
"""