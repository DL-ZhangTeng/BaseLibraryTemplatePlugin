package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvvm

import com.github.dlzhangteng.baselibrarytemplateplugin.template.getLayoutName

fun mvvmFragment(
    mRootPackageName: String?,
    mActivityPackageName: String,
    mPageName: String
) = """
package ${mRootPackageName}${mActivityPackageName.ifEmpty { "" }}

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhangteng.mvvm.mvvm.BaseMvvmFragment
import ${mRootPackageName}.R
import ${mRootPackageName}.mvvm.vm.${mPageName}FragmentViewModel

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