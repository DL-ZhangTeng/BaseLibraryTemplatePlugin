package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvpvb

fun mvpVbActivity(
    mRootPackageName: String?,
    mActivityPackageName: String,
    mPageName: String,
    mActivityLayoutName: String
) = """
package ${mRootPackageName}${mActivityPackageName.ifEmpty { "" }}

import android.os.Bundle
import com.zhangteng.mvp.mvp.vb.BaseMvpActivity
import ${mRootPackageName}.R
import ${mRootPackageName}.databinding.Activity${mPageName}Binding
import ${mRootPackageName}.mvp.model.imodel.I${mPageName}Model
import ${mRootPackageName}.mvp.presenter.${mPageName}Presenter
import ${mRootPackageName}.mvp.presenter.ipresenter.I${mPageName}Presenter
import ${mRootPackageName}.mvp.view.I${mPageName}View

class ${mPageName}Activity : BaseMvpActivity<Activity${mPageName}Binding, I${mPageName}View, I${mPageName}Model, I${mPageName}Presenter>(), I${mPageName}View {

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