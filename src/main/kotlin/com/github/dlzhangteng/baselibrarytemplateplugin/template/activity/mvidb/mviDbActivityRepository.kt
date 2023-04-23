package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvidb

fun mviDbActivityRepository(
    mRootPackageName: String?,
    mPageName: String
) = """
package ${mRootPackageName}.mvi.repository

import com.zhangteng.mvvm.base.BaseNetRepository

class ${mPageName}Repository : BaseNetRepository() {

    private val mService by lazy {
        //HttpUtils.getInstance().ConfigGlobalHttpUtils().createService(Api::class.java)
    }
}
"""