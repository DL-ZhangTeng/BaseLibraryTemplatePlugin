package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvmdb

import com.github.dlzhangteng.baselibrarytemplateplugin.template.getLayoutName

fun mvvmDbActivity(
    mRootPackageName: String?,
    mActivityPackageName: String,
    mPageName: String
) = """
package ${mRootPackageName}${mActivityPackageName.ifEmpty { "" }}

import android.os.Bundle
import com.zhangteng.mvvm.mvvm.BaseMvvmDbActivity
import ${mRootPackageName}.R
import ${mRootPackageName}.databinding.Activity${mPageName}DbBinding
import ${mRootPackageName}.mvvm.vm.${mPageName}DbViewModel

class ${mPageName}DbActivity : BaseMvvmDbActivity<${mPageName}DbViewModel, Activity${mPageName}DbBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity${getLayoutName(mPageName)}_db)
    }
    
    override fun initView() {

    }

    override fun initData() {

    }
}
"""