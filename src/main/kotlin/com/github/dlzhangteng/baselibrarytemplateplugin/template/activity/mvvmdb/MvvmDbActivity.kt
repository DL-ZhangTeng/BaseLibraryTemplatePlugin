package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvmdb

import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.base.getLayoutName

fun mvvmDbActivity(
    mRootPackageName: String?,
    mActivityPackageName: String,
    mPageName: String
) = """
package ${mRootPackageName}${if (mActivityPackageName.isNullOrEmpty()) "" else ".${mActivityPackageName}"}

import android.os.Bundle
import com.zhangteng.base.base.BaseMvvmDbActivity
import ${mRootPackageName}.R
import ${mRootPackageName}.databinding.${mPageName}DbActivityBinding
import ${mRootPackageName}.mvvm.vm.${mPageName}DbViewModel

class ${mPageName}DbActivity : BaseMvvmDbActivity<${mPageName}DbViewModel, ${mPageName}DbActivityBinding>() {

    override fun initView() {

    }

    override fun initData() {

    }

    override fun layoutId(): Int {
        return R.layout.activity${getLayoutName(mPageName)}_db
    }
}
"""