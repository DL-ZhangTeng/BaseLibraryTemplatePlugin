package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvidb

fun mviDbFragment(
    mRootPackageName: String?,
    mFragmentPackageName: String,
    mPageName: String,
    mFragmentLayoutName: String
) = """
package ${mRootPackageName}${mFragmentPackageName.ifEmpty { "" }}

import android.os.Bundle
import android.view.View
import com.zhangteng.mvvm.mvi.db.BaseMviFragment
import ${mRootPackageName}.R
import ${mRootPackageName}.databinding.Fragment${mPageName}Binding
import ${mRootPackageName}.mvi.vm.${mPageName}FragmentViewModel

class ${mPageName}Fragment : BaseMviFragment<${mPageName}FragmentViewModel, Fragment${mPageName}Binding>() {

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