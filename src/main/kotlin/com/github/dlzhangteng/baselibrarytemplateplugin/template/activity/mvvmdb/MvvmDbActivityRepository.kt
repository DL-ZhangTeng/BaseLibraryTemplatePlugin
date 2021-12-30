package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvmdb

fun mvvmActivityRepository(
    mRootPackageName: String?,
    mPageName: String
) = """
package ${mRootPackageName}.mvvm.repository

import com.zhangteng.base.mvvm.base.BaseNetRepository

class ${mPageName}DbRepository : BaseNetRepository() {

    private val mService by lazy {
        //HttpUtils.getInstance().ConfigGlobalHttpUtils().createService(Api::class.java)
    }
}
"""