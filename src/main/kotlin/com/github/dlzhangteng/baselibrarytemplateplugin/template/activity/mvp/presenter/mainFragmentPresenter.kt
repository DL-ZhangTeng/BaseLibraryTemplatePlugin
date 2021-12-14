package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvp.presenter

fun mvpPresenter(
    mRootPackageName: String?,
    mPageName: String
) = """
package ${mRootPackageName}.mvp.presenter

import com.zhangteng.base.mvp.base.BasePresenter
import ${mRootPackageName}.mvp.model.${mPageName}Model
import ${mRootPackageName}.mvp.model.imodel.I${mPageName}Model
import ${mRootPackageName}.mvp.presenter.ipresenter.I${mPageName}Presenter
import ${mRootPackageName}.mvp.view.I${mPageName}View

class ${mPageName}Presenter : BasePresenter<I${mPageName}View, I${mPageName}Model>(), I${mPageName}Presenter {
    init {
        this.mModel = ${mPageName}Model()
    }
}
"""