package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvvmdb

fun mvvmDbFragmentRepository(
    mRootPackageName: String?,
    mPageName: String
) = """
package ${mRootPackageName}.mvvm.repository

import com.zhangteng.base.mvvm.base.BaseNetRepository

class ${mPageName}DbFragmentRepository : BaseNetRepository() {

    private val mService by lazy {
        //HttpUtils.getInstance().ConfigGlobalHttpUtils().createService(Api::class.java)
    }
}
"""