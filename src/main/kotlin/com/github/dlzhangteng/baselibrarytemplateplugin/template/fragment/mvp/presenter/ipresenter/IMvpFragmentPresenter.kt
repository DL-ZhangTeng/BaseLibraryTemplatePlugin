package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvp.presenter.ipresenter

fun mvpIFragmentPresenter(
    mRootPackageName: String?,
    mPageName: String
) = """
package ${mRootPackageName}.mvp.presenter.ipresenter

import com.zhangteng.mvp.base.IPresenter
import ${mRootPackageName}.mvp.model.imodel.I${mPageName}FragmentModel
import ${mRootPackageName}.mvp.view.I${mPageName}FragmentView

interface I${mPageName}FragmentPresenter : IPresenter<I${mPageName}FragmentView, I${mPageName}FragmentModel>
"""