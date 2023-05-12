package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvp.view

fun mvpView(
    mRootPackageName: String?,
    mPageName: String
) = """
package ${mRootPackageName}.mvp.view

import com.zhangteng.mvp.base.IView

interface I${mPageName}View : IView
"""