package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvp.di

import com.github.dlzhangteng.baselibrarytemplateplugin.template.DependencyInjectionEnum

fun mvpModule(
    mRootPackageName: String?,
    mPageName: String,
    mDependencyInjectionEnum: DependencyInjectionEnum
) = """
package ${mRootPackageName}.mvp.di

import ${mRootPackageName}.mvp.model.${mPageName}Model
import ${mRootPackageName}.mvp.model.imodel.I${mPageName}Model
import ${mRootPackageName}.mvp.presenter.${mPageName}Presenter
import ${mRootPackageName}.mvp.presenter.ipresenter.I${mPageName}Presenter
${
    if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT) 
"""import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)""" else """
    
"""
}
abstract class ${mPageName}Module {

    @Binds
    abstract fun bind${mPageName}Presenter(${mPageName}Presenter: ${mPageName}Presenter): I${mPageName}Presenter

    @Binds
    abstract fun bindM${mPageName}Model(${mPageName}Model: ${mPageName}Model): I${mPageName}Model

}
"""