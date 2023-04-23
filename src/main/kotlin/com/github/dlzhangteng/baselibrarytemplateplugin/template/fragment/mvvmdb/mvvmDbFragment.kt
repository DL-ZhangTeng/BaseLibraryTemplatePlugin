package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvvmdb

fun mvvmDbFragment(
    mRootPackageName: String?,
    mFragmentPackageName: String,
    mPageName: String,
    mFragmentLayoutName: String
) = """
package ${mRootPackageName}${mFragmentPackageName.ifEmpty { "" }}

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhangteng.mvvm.mvvm.db.BaseMvvmFragment
import ${mRootPackageName}.R
import ${mRootPackageName}.databinding.Fragment${mPageName}Binding
import ${mRootPackageName}.mvvm.vm.${mPageName}FragmentViewModel

class ${mPageName}Fragment : BaseMvvmFragment<Fragment${mPageName}Binding, ${mPageName}FragmentViewModel>() {

    companion object {
        fun newInstance() = ${mPageName}Fragment()
    }
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.${mFragmentLayoutName}, container, false)
    }
    
    override fun initView(view: View, savedInstanceState: Bundle?) {
       
    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
       
    }
}
"""