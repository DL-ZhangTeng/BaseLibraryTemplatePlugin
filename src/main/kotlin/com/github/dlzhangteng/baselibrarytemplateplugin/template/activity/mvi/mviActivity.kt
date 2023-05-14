package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvi

import com.github.dlzhangteng.baselibrarytemplateplugin.template.DependencyInjectionEnum

fun mviActivity(
    mRootPackageName: String?,
    mActivityPackageName: String,
    mPageName: String,
    mActivityLayoutName: String,
    mDependencyInjectionEnum: DependencyInjectionEnum,
) = """
package ${mRootPackageName}${mActivityPackageName.ifEmpty { "" }}

import android.os.Bundle
import com.zhangteng.mvvm.mvi.BaseMviActivity
import ${mRootPackageName}.R
import ${mRootPackageName}.mvi.vm.${mPageName}ViewModel
${
    if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT)
        """import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint""" else
        """
    
"""
}
class ${mPageName}Activity : BaseMviActivity<${mPageName}ViewModel>() {

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