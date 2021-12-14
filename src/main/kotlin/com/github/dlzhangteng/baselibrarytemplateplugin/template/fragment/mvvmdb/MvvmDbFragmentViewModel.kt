package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvvmdb

fun mvvmDbFragmentViewModel(
    mRootPackageName: String?,
    mPageName: String
) = """
package ${mRootPackageName}.mvvm.vm

import com.zhangteng.base.mvvm.base.BaseViewModel

class ${mPageName}DbFragmentViewModel : BaseViewModel() {
   
}
"""