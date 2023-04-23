package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvmdb

fun mvvmDbActivity(
    mRootPackageName: String?,
    mActivityPackageName: String,
    mPageName: String,
    mActivityLayoutName: String
) = """
package ${mRootPackageName}${mActivityPackageName.ifEmpty { "" }}

import android.os.Bundle
import com.zhangteng.mvvm.mvvm.db.BaseMvvmActivity
import ${mRootPackageName}.R
import ${mRootPackageName}.databinding.Activity${mPageName}DbBinding
import ${mRootPackageName}.mvvm.vm.${mPageName}DbViewModel

class ${mPageName}Activity : BaseMvvmActivity<${mPageName}ViewModel, Activity${mPageName}Binding>() {

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