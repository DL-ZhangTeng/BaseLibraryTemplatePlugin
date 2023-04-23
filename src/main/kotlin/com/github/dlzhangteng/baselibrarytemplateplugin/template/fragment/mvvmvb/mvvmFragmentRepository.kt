package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvvmvb

fun mvvmVbFragmentRepository(
    mRootPackageName: String?,
    mPageName: String
) = """
package ${mRootPackageName}.mvvm.repository

import com.zhangteng.mvvm.base.BaseNetRepository

class ${mPageName}FragmentRepository : BaseNetRepository() {

    private val mService by lazy {
        //HttpUtils.getInstance().ConfigGlobalHttpUtils().createService(Api::class.java)
    }
}
"""