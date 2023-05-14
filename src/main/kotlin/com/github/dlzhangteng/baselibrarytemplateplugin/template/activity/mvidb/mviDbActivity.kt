package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvidb

import com.github.dlzhangteng.baselibrarytemplateplugin.template.DependencyInjectionEnum

fun mviDbActivity(
    mRootPackageName: String?,
    mActivityPackageName: String,
    mPageName: String,
    mActivityLayoutName: String,
    mDependencyInjectionEnum: DependencyInjectionEnum,
) = """
package ${mRootPackageName}${mActivityPackageName.ifEmpty { "" }}

import android.os.Bundle
import com.zhangteng.mvvm.mvi.db.BaseMviActivity
import ${mRootPackageName}.R
import ${mRootPackageName}.databinding.Activity${mPageName}Binding
import ${mRootPackageName}.mvi.vm.${mPageName}ViewModel
${
    if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT)
        """import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint""" else
        """
    
"""
}
class ${mPageName}Activity : BaseMviActivity<Activity${mPageName}Binding, ${mPageName}ViewModel>() {

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