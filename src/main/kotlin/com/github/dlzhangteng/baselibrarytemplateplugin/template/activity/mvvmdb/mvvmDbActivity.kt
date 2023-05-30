package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvmdb

import com.github.dlzhangteng.baselibrarytemplateplugin.template.DependencyInjectionEnum
import com.github.dlzhangteng.baselibrarytemplateplugin.template.getPageName

fun mvvmDbActivity(
    mRootPackageName: String?,
    mActivityPackageName: String,
    mPageName: String,
    mActivityLayoutName: String,
    mDependencyInjectionEnum: DependencyInjectionEnum,
) = """
package ${mRootPackageName}${mActivityPackageName.ifEmpty { "" }}

import android.os.Bundle
import com.zhangteng.mvvm.mvvm.db.BaseMvvmActivity
import ${mRootPackageName}.R
import ${mRootPackageName}.databinding.${getPageName(mActivityLayoutName)}Binding
import ${mRootPackageName}.mvvm.vm.${mPageName}ViewModel
${
    if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT)
        """import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint""" else
        """
    
"""
}
class ${mPageName}Activity : BaseMvvmActivity<${getPageName(mActivityLayoutName)}Binding, ${mPageName}ViewModel>() {

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