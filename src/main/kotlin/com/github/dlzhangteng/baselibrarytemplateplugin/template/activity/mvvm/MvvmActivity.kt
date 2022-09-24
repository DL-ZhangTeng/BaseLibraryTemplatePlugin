package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvm

import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.base.getLayoutName

fun mvvmActivity(
    mRootPackageName: String?,
    mActivityPackageName: String,
    mPageName: String
) = """
package ${mRootPackageName}${if (mActivityPackageName.isNullOrEmpty()) "" else ".${mActivityPackageName}"}

import android.os.Bundle
import com.zhangteng.mvvm.mvvm.BaseMvvmActivity
import ${mRootPackageName}.R
import ${mRootPackageName}.mvvm.vm.${mPageName}ViewModel


class ${mPageName}Activity : BaseMvvmActivity<${mPageName}ViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity${getLayoutName(mPageName)})
    }

    override fun initView() {

    }

    override fun initData() {

    }
}
"""