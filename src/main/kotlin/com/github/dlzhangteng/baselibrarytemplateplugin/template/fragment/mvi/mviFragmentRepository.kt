package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvi

fun mviFragmentRepository(
    mRootPackageName: String?,
    mPageName: String
) = """
package ${mRootPackageName}.mvi.repository

import com.zhangteng.mvvm.base.BaseNetRepository

class ${mPageName}FragmentRepository : BaseNetRepository() {

    private val mService by lazy {
        //HttpUtils.getInstance().ConfigGlobalHttpUtils().createService(Api::class.java)
    }
}
"""