package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvp.di

import com.github.dlzhangteng.baselibrarytemplateplugin.template.DependencyInjectionEnum

fun mvpFragmentModule(
    mRootPackageName: String?,
    mPageName: String,
    mDependencyInjectionEnum: DependencyInjectionEnum
) = """
package ${mRootPackageName}.mvp.di

import ${mRootPackageName}.mvp.model.${mPageName}FragmentModel
import ${mRootPackageName}.mvp.model.imodel.I${mPageName}FragmentModel
import ${mRootPackageName}.mvp.presenter.${mPageName}FragmentPresenter
import ${mRootPackageName}.mvp.presenter.ipresenter.I${mPageName}FragmentPresenter
${
    if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT) """
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
""" else """
    
"""
}
abstract class ${mPageName}FragmentModule {

    @Binds
    abstract fun bind${mPageName}FragmentPresenter(${mPageName}FragmentPresenter: ${mPageName}FragmentPresenter): I${mPageName}FragmentPresenter

    @Binds
    abstract fun bindM${mPageName}FragmentModel(${mPageName}FragmentModel: ${mPageName}FragmentModel): I${mPageName}FragmentModel

}
"""