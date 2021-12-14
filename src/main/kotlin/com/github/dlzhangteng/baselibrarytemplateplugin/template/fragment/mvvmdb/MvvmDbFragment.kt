package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvvmdb

import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.base.getLayoutName

fun mvvmDbFragment(
    mRootPackageName: String?,
    mActivityPackageName: String,
    mPageName: String
) = """
package ${mRootPackageName}${if (mActivityPackageName.isNullOrEmpty()) "" else ".${mActivityPackageName}"}

import android.os.Bundle
import android.view.View
import com.zhangteng.base.base.BaseAdapter
import com.zhangteng.base.base.BaseMvvmDbFragment
import ${mRootPackageName}.R
import ${mRootPackageName}.databinding.${mPageName}DbFragmentBinding
import ${mRootPackageName}.mvvm.vm.${mPageName}DbFragmentViewModel

class ${mPageName}DbFragment : BaseMvvmDbFragment<${mPageName}DbFragmentViewModel, ${mPageName}DbFragmentBinding>() {

    companion object {
        fun newInstance() = ${mPageName}DbFragment()
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
       
    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
       
    }

    override fun layoutId(): Int {
        return R.layout.fragment_db${getLayoutName(mPageName)}
    }
}
"""