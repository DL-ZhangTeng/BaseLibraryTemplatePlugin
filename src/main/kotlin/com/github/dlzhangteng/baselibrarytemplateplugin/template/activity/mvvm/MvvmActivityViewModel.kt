package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvm

fun mvvmActivityViewModel(
    mRootPackageName: String?,
    mPageName: String
) = """
package ${mRootPackageName}.mvvm.vm

import com.zhangteng.base.mvvm.base.BaseViewModel

class ${mPageName}ViewModel : BaseViewModel()
"""