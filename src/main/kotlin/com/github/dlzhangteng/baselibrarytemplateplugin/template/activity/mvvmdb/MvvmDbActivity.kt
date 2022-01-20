package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvmdb

import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.base.getLayoutName

fun mvvmDbActivity(
    mRootPackageName: String?,
    mActivityPackageName: String,
    mPageName: String
) = """
package ${mRootPackageName}${if (mActivityPackageName.isNullOrEmpty()) "" else ".${mActivityPackageName}"}

import com.zhangteng.mvvm.mvvm.BaseMvvmDbActivity
import ${mRootPackageName}.R
import ${mRootPackageName}.databinding.Activity${mPageName}DbBinding
import ${mRootPackageName}.mvvm.vm.${mPageName}DbViewModel

class ${mPageName}DbActivity : BaseMvvmDbActivity<${mPageName}DbViewModel, Activity${mPageName}DbBinding>() {

    override fun initView() {

    }

    override fun initData() {

    }

    override fun layoutId(): Int {
        return R.layout.activity${getLayoutName(mPageName)}_db
    }
}
"""