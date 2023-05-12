package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvp.presenter

fun mvpFragmentPresenter(
    mRootPackageName: String?,
    mPageName: String
) = """
package ${mRootPackageName}.mvp.presenter

import com.zhangteng.mvp.base.BasePresenter
import ${mRootPackageName}.mvp.model.${mPageName}FragmentModel
import ${mRootPackageName}.mvp.model.imodel.I${mPageName}FragmentModel
import ${mRootPackageName}.mvp.presenter.ipresenter.I${mPageName}FragmentPresenter
import ${mRootPackageName}.mvp.view.I${mPageName}FragmentView

class ${mPageName}FragmentPresenter : BasePresenter<I${mPageName}FragmentView, I${mPageName}FragmentModel>(), I${mPageName}FragmentPresenter {

    override var mModel: I${mPageName}Model = ${mPageName}Model()
    
}
"""