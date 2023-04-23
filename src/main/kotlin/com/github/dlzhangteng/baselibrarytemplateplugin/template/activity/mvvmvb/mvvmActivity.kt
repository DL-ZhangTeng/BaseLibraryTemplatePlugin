package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvmvb

fun mvvmVbActivity(
    mRootPackageName: String?,
    mActivityPackageName: String,
    mPageName: String,
    mActivityLayoutName: String
) = """
package ${mRootPackageName}${mActivityPackageName.ifEmpty { "" }}

import android.os.Bundle
import com.zhangteng.mvvm.mvvm.vb.BaseMvvmActivity
import ${mRootPackageName}.R
import ${mRootPackageName}.databinding.Activity${mPageName}Binding
import ${mRootPackageName}.mvvm.vm.${mPageName}ViewModel


class ${mPageName}Activity : BaseMvvmActivity<Activity${mPageName}Binding, ${mPageName}ViewModel>() {

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