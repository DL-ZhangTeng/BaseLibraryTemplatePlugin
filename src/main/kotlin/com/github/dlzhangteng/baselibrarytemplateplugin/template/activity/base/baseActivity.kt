package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.base


fun baseActivity(
    mRootPackageName: String?,
    mActivityPackageName: String,
    mPageName: String
) = """
package ${mRootPackageName}${if (mActivityPackageName.isNullOrEmpty()) "" else ".${mActivityPackageName}"}

import android.os.Bundle

import com.zhangteng.base.base.BaseActivity
import ${mRootPackageName}.R

class ${mPageName}Activity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity${
    com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.base.getLayoutName(
        mPageName
    )
})
    }

    override fun initView() {
     
    }

    override fun initData() {

    }
}
"""
