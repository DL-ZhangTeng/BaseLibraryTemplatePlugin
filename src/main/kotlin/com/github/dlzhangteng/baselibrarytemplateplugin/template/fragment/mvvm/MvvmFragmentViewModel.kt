package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvvm

fun mvvmFragmentViewModel(
    mRootPackageName: String?,
    mPageName: String
) = """
package ${mRootPackageName}.mvvm.vm

import com.zhangteng.base.mvvm.base.BaseViewModel

class ${mPageName}FragmentViewModel : BaseViewModel() {

}
"""