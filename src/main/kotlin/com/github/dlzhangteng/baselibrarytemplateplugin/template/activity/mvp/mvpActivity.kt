package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvp

fun mvpActivity(
    mRootPackageName: String?,
    mActivityPackageName: String,
    mPageName: String,
    mActivityLayoutName: String
) = """
package ${mRootPackageName}${mActivityPackageName.ifEmpty { "" }}

import android.os.Bundle
import com.zhangteng.mvp.mvp.BaseMvpActivity
import ${mRootPackageName}.mvp.model.imodel.I${mPageName}Model
import ${mRootPackageName}.mvp.presenter.${mPageName}Presenter
import ${mRootPackageName}.mvp.presenter.ipresenter.I${mPageName}Presenter
import ${mRootPackageName}.mvp.view.I${mPageName}View
import ${mRootPackageName}.R

class ${mPageName}Activity : BaseMvpActivity<I${mPageName}View, I${mPageName}Model, I${mPageName}Presenter>(), I${mPageName}View {

    override var mPresenter: I${mPageName}Presenter = ${mPageName}Presenter()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.${mActivityLayoutName})
    }

    override fun initView() {
        super.initView()

    }

    override fun initData() {
     
    }
}
"""