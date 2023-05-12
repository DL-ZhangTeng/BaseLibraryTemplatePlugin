package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvpvb

import com.github.dlzhangteng.baselibrarytemplateplugin.template.DependencyInjectionEnum

fun mvpVbActivity(
    mRootPackageName: String?,
    mActivityPackageName: String,
    mPageName: String,
    mActivityLayoutName: String,
    mDependencyInjectionEnum: DependencyInjectionEnum,
) = """
package ${mRootPackageName}${mActivityPackageName.ifEmpty { "" }}

import android.os.Bundle
import com.zhangteng.mvp.mvp.vb.BaseMvpActivity
import ${mRootPackageName}.R
import ${mRootPackageName}.databinding.Activity${mPageName}Binding
import ${mRootPackageName}.mvp.model.imodel.I${mPageName}Model
${
    if (mDependencyInjectionEnum != DependencyInjectionEnum.HILT) """import ${mRootPackageName}.mvp.presenter.${mPageName}Presenter""" else ""
}
import ${mRootPackageName}.mvp.presenter.ipresenter.I${mPageName}Presenter
import ${mRootPackageName}.mvp.view.I${mPageName}View
${
    if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT)
        """import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint""" else
        """
    
"""
}
class ${mPageName}Activity : BaseMvpActivity<Activity${mPageName}Binding, I${mPageName}View, I${mPageName}Model, I${mPageName}Presenter>(), I${mPageName}View {

${
    if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT) """
    @Inject
    override lateinit var mPresenter: I${mPageName}Presenter
"""
    else """
    override var mPresenter: I${mPageName}Presenter = ${mPageName}Presenter()
"""
}
    
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