package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.base


fun baseActivity(
    mRootPackageName: String?,
    mActivityPackageName: String,
    mPageName: String,
    mActivityLayoutName: String
) = """
package ${mRootPackageName}${mActivityPackageName.ifEmpty { "" }}

import android.os.Bundle

import com.zhangteng.base.base.BaseActivity
import ${mRootPackageName}.R

class ${mPageName}Activity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.${mActivityLayoutName})
    }

    override fun initView() {
     
    }

    override fun initData() {

    }
}
"""
