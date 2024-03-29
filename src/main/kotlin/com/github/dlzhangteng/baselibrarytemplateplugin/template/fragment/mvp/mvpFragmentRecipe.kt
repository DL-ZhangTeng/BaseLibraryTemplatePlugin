package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvp

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.github.dlzhangteng.baselibrarytemplateplugin.template.DependencyInjectionEnum
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvp.di.mvpFragmentModule
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvp.model.imodel.mvpIFragmentModel
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvp.model.mvpFragmentModel
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvp.presenter.ipresenter.mvpIFragmentPresenter
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvp.presenter.mvpFragmentPresenter
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvp.view.mvpFragmentView
import com.github.dlzhangteng.baselibrarytemplateplugin.template.layout.basePageXml
import java.io.File


fun RecipeExecutor.mvpFragmentRecipe(
    moduleTemplateData: ModuleTemplateData,
    mPageName: String,
    mFragmentLayoutName: String,
    mIsGenerateActivityLayout: Boolean,
    mFragmentPackageName: String,
    mDependencyInjectionEnum: DependencyInjectionEnum,
) {
    val packageNameStr =
        if (moduleTemplateData.projectTemplateData.applicationPackage == null) ""
        else mFragmentPackageName
            .replace(moduleTemplateData.projectTemplateData.applicationPackage.toString(), "")
    val rootPath =
        if (packageNameStr.isNotEmpty()) moduleTemplateData.projectTemplateData.applicationPackage.toString()
        else mFragmentPackageName

    val listActivity = mvpFragment(
        rootPath,
        packageNameStr,
        mPageName,
        mFragmentLayoutName,
        mDependencyInjectionEnum
    )
    val mvpIView = mvpFragmentView(rootPath, mPageName)
    val mvpIModel = mvpIFragmentModel(rootPath, mPageName)
    val mvpModel = mvpFragmentModel(rootPath, mPageName, mDependencyInjectionEnum)
    val mvpIPresenter = mvpIFragmentPresenter(rootPath, mPageName)
    val mvpPresenter = mvpFragmentPresenter(rootPath, mPageName, mDependencyInjectionEnum)
    // 保存Activity
    save(
        listActivity,
        moduleTemplateData.srcDir.resolve("${mPageName}Fragment.kt")
    )
    if (mIsGenerateActivityLayout) {
        // 保存xml
        save(
            basePageXml("${packageNameStr}.${mPageName}Fragment"),
            moduleTemplateData.resDir.resolve("layout/${mFragmentLayoutName}.xml")
        )
    }

    save(
        mvpIView,
        File(
            moduleTemplateData.rootDir.absolutePath
                    + "/src/main/java/"
                    + rootPath.replace(".", "/")
                    + "/mvp/view/"
        ).apply { mkdirs() }
            .resolve("I${mPageName}FragmentView.kt")
    )

    save(
        mvpIModel,
        File(
            moduleTemplateData.rootDir.absolutePath
                    + "/src/main/java/"
                    + rootPath.replace(".", "/")
                    + "/mvp/model/imodel/"
        ).apply { mkdirs() }
            .resolve("I${mPageName}FragmentModel.kt")
    )

    save(
        mvpModel,
        File(
            moduleTemplateData.rootDir.absolutePath
                    + "/src/main/java/"
                    + rootPath.replace(".", "/")
                    + "/mvp/model/"
        ).apply { mkdirs() }
            .resolve("${mPageName}FragmentModel.kt")
    )

    save(
        mvpIPresenter,
        File(
            moduleTemplateData.rootDir.absolutePath
                    + "/src/main/java/"
                    + rootPath.replace(".", "/")
                    + "/mvp/presenter/ipresenter/"
        ).apply { mkdirs() }
            .resolve("I${mPageName}FragmentPresenter.kt")
    )

    save(
        mvpPresenter,
        File(
            moduleTemplateData.rootDir.absolutePath
                    + "/src/main/java/"
                    + rootPath.replace(".", "/")
                    + "/mvp/presenter/"
        ).apply { mkdirs() }
            .resolve("${mPageName}FragmentPresenter.kt")
    )

    if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT) {
        val mvpModule = mvpFragmentModule(rootPath, mPageName, mDependencyInjectionEnum)

        save(
            mvpModule,
            File(
                moduleTemplateData.rootDir.absolutePath
                        + "/src/main/java/"
                        + rootPath.replace(".", "/")
                        + "/mvp/di/"
            ).apply { mkdirs() }
                .resolve("${mPageName}FragmentModule.kt")
        )
    }
}