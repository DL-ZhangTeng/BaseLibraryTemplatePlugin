package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvp

import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.base.getLayoutName

fun mvpActivity(
    mRootPackageName: String?,
    mActivityPackageName: String,
    mPageName: String
) = """
package ${mRootPackageName}${if (mActivityPackageName.isNullOrEmpty()) "" else ".${mActivityPackageName}"}

import android.os.Bundle
import com.zhangteng.base.base.BaseMvpActivity
import com.zhangteng.base.mvp.utils.LoadingPresenterHandler
import com.zhangteng.base.utils.LoadViewHelper
import com.zhangteng.base.utils.jumpToActivity
import ${mRootPackageName}.mvp.model.imodel.I${mPageName}Model
import ${mRootPackageName}.mvp.presenter.${mPageName}Presenter
import ${mRootPackageName}.mvp.presenter.ipresenter.I${mPageName}Presenter
import ${mRootPackageName}.mvp.view.I${mPageName}View
import ${mRootPackageName}.R

class ${mPageName}Activity : BaseMvpActivity<I${mPageName}View, I${mPageName}Model, I${mPageName}Presenter>(), I${mPageName}View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity${getLayoutName(mPageName)})
    }

    override fun createPresenter(): I${mPageName}Presenter? {
        return ${mPageName}Presenter()
//        return Proxy.newProxyInstance(
//            ${mPageName}Presenter::class.java.classLoader,
//            arrayOf(I${mPageName}Presenter::class.java),
//            LoadingPresenterHandler(${mPageName}Presenter())
//        ) as I${mPageName}Presenter
    }

    override fun initView() {
        super.initView()

    }

    override fun initData() {
     
    }
}
"""