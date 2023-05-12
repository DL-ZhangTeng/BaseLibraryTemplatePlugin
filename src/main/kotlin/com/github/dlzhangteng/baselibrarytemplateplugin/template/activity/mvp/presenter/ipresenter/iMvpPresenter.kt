package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvp.presenter.ipresenter

fun mvpIPresenter(
    mRootPackageName: String?,
    mPageName: String
) = """
package ${mRootPackageName}.mvp.presenter.ipresenter

import com.zhangteng.mvp.base.IPresenter
import ${mRootPackageName}.mvp.model.imodel.I${mPageName}Model
import ${mRootPackageName}.mvp.view.I${mPageName}View

interface I${mPageName}Presenter : IPresenter<I${mPageName}View, I${mPageName}Model>
"""