package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvmvb

fun mvvmVbActivityRepository(
    mRootPackageName: String?,
    mPageName: String
) = """
package ${mRootPackageName}.mvvm.repository

import com.zhangteng.mvvm.base.BaseNetRepository

class ${mPageName}Repository : BaseNetRepository() {

    private val mService by lazy {
        //HttpUtils.getInstance().ConfigGlobalHttpUtils().createService(Api::class.java)
    }
}
"""