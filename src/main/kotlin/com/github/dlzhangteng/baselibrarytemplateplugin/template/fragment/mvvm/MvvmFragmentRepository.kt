package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvvm

fun mvvmFragmentRepository(
    mRootPackageName: String?,
    mPageName: String
) = """
package ${mRootPackageName}.mvvm.repository

import com.zhangteng.base.mvvm.base.BaseNetRepository

class ${mPageName}FragmentRepository : BaseNetRepository() {

    private val mService by lazy {
        //HttpUtils.getInstance().ConfigGlobalHttpUtils().createService(Api::class.java)
    }
}
"""