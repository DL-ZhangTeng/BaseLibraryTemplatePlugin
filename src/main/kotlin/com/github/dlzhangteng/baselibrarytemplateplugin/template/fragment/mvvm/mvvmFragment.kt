package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvvm

import com.github.dlzhangteng.baselibrarytemplateplugin.template.DependencyInjectionEnum

fun mvvmFragment(
    mRootPackageName: String?,
    mFragmentPackageName: String,
    mPageName: String,
    mFragmentLayoutName: String,
    mDependencyInjectionEnum: DependencyInjectionEnum
) = """
package ${mRootPackageName}${mFragmentPackageName.ifEmpty { "" }}

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhangteng.mvvm.mvvm.BaseMvvmFragment
import ${mRootPackageName}.R
import ${mRootPackageName}.mvvm.vm.${mPageName}FragmentViewModel
${
    if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT)
        """import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint""" else
        """
    
"""
}
class ${mPageName}Fragment : BaseMvvmFragment<${mPageName}FragmentViewModel>() {

    companion object {
        fun newInstance() = ${mPageName}Fragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.${mFragmentLayoutName}, container, false)
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
       
    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
       
    }
}
"""