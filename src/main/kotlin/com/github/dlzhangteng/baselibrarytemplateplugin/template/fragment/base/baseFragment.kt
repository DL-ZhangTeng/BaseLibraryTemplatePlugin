package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.base


fun baseFragment(
    mRootPackageName: String?,
    mActivityPackageName: String,
    mPageName: String
) = """
package ${mRootPackageName}${if (mActivityPackageName.isNullOrEmpty()) "" else ".${mActivityPackageName}"}

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zhangteng.base.base.BaseFragment
import ${mRootPackageName}.R

class ${mPageName}Fragment : BaseFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment${getLayoutName(mPageName)}, container, false)
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {

    }

    companion object {
        fun newInstance(): ${mPageName}Fragment {
            return BlankFragment().apply {
                arguments = Bundle()
            }
        }
    }
}
"""
