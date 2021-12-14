package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvp.view

fun mvpFragmentView(
    mRootPackageName: String?,
    mPageName: String
) = """
package ${mRootPackageName}.mvp.view

import com.zhangteng.base.mvp.base.IView

interface I${mPageName}FragmentView : IView
"""