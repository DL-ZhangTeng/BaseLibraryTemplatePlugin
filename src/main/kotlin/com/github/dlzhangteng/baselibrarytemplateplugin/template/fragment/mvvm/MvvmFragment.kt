package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvvm

import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.base.getLayoutName

fun mvvmFragment(
    mRootPackageName: String?,
    mActivityPackageName: String,
    mPageName: String
) = """
package ${mRootPackageName}${if (mActivityPackageName.isNullOrEmpty()) "" else ".${mActivityPackageName}"}

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhangteng.base.base.BaseAdapter
import com.zhangteng.base.base.BaseMvvmFragment
import ${mRootPackageName}.R

class ${mPageName}Fragment : BaseMvvmFragment<${mPageName}FragmentViewModel>() {

    companion object {
        fun newInstance() = ${mPageName}Fragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment${getLayoutName(mPageName)}, container, false)
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
       
    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
       
    }
}
"""