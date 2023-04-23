package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvidb

fun mviDbActivity(
    mRootPackageName: String?,
    mActivityPackageName: String,
    mPageName: String,
    mActivityLayoutName: String
) = """
package ${mRootPackageName}${mActivityPackageName.ifEmpty { "" }}

import android.os.Bundle
import com.zhangteng.mvvm.mvi.db.BaseMviActivity
import ${mRootPackageName}.R
import ${mRootPackageName}.databinding.Activity${mPageName}DbBinding
import ${mRootPackageName}.mvi.vm.${mPageName}DbViewModel

class ${mPageName}Activity : BaseMviActivity<${mPageName}ViewModel, Activity${mPageName}Binding>() {

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