package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvvmdb

fun mvvmDbFragment(
    mRootPackageName: String?,
    mFragmentPackageName: String,
    mPageName: String,
    mFragmentLayoutName: String
) = """
package ${mRootPackageName}${mFragmentPackageName.ifEmpty { "" }}

import android.os.Bundle
import android.view.View
import com.zhangteng.mvvm.mvvm.BaseMvvmDbFragment
import ${mRootPackageName}.R
import ${mRootPackageName}.databinding.Fragment${mPageName}DbBinding
import ${mRootPackageName}.mvvm.vm.${mPageName}DbFragmentViewModel

class ${mPageName}DbFragment : BaseMvvmDbFragment<${mPageName}DbFragmentViewModel, Fragment${mPageName}DbBinding>() {

    companion object {
        fun newInstance() = ${mPageName}DbFragment()
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