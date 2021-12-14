package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.titlebar


fun titlebarAcitivity(
    mRootPackageName: String?,
    mActivityPackageName: String,
    mPageName: String
) = """
package ${mRootPackageName}${if (mActivityPackageName.isNullOrEmpty()) "" else ".${mActivityPackageName}"}

import android.os.Bundle
import com.zhangteng.base.base.TitlebarActivity
import ${mRootPackageName}.R

class ${mPageName}Activity : TitlebarActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity${getLayoutName(mPageName)})
    }

    override fun initView() {
        super.initView()
    }

    override fun initData() {

    }
}
"""
