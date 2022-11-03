package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.titlebar


fun titleBarActivity(
    mRootPackageName: String?,
    mActivityPackageName: String,
    mPageName: String,
    mActivityLayoutName: String
) = """
package ${mRootPackageName}${mActivityPackageName.ifEmpty { "" }}

import android.os.Bundle
import com.zhangteng.base.base.TitleBarActivity
import ${mRootPackageName}.R

class ${mPageName}Activity : TitleBarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.${mActivityLayoutName})
    }

    override fun initView() {
        super.initView()
    }

    override fun initData() {

    }
}
"""
