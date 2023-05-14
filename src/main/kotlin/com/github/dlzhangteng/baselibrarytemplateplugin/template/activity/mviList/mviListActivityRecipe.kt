package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mviList

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.github.dlzhangteng.baselibrarytemplateplugin.template.DependencyInjectionEnum
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvi.mviActivityRepository
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvi.mviActivityViewModel
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvi.mviAppModule
import com.github.dlzhangteng.baselibrarytemplateplugin.template.addActivityToManifest
import com.github.dlzhangteng.baselibrarytemplateplugin.template.getLayoutName
import com.github.dlzhangteng.baselibrarytemplateplugin.template.layout.baseListXml
import com.github.dlzhangteng.baselibrarytemplateplugin.template.layout.baseXml
import com.github.dlzhangteng.baselibrarytemplateplugin.template.other.adapter.baseAdapter
import com.github.dlzhangteng.baselibrarytemplateplugin.template.other.adapter.baseBean
import java.io.File


fun RecipeExecutor.mviListActivityRecipe(
    moduleTemplateData: ModuleTemplateData,
    mPageName: String,
    mActivityLayoutName: String,
    mIsGenerateActivityLayout: Boolean,
    mBeanClass: String,
    mAdapterClass: String,
    mActivityPackageName: String,
    mDependencyInjectionEnum: DependencyInjectionEnum,
) {
    val packageNameStr =
        if (moduleTemplateData.projectTemplateData.applicationPackage == null) ""
        else mActivityPackageName
            .replace(moduleTemplateData.projectTemplateData.applicationPackage.toString(), "")
    val rootPath =
        if (packageNameStr.isNotEmpty()) moduleTemplateData.projectTemplateData.applicationPackage.toString()
        else mActivityPackageName
    val mviActivity = mviListActivity(
        rootPath,
        packageNameStr,
        mPageName,
        mActivityLayoutName,
        mBeanClass,
        mAdapterClass,
        mDependencyInjectionEnum
    )
    val mviActivityViewModel = mviActivityViewModel(
        rootPath,
        mPageName,
        mDependencyInjectionEnum
    )
    val mviActivityRepository = mviActivityRepository(
        rootPath,
        mPageName,
        mDependencyInjectionEnum
    )
    val listBean = baseBean(rootPath, mBeanClass)
    val listAdapter =
        baseAdapter(
            rootPath,
            "item${
                getLayoutName(
                    mPageName
                )
            }",
            mBeanClass,
            mAdapterClass
        )
    // 保存Activity
    save(
        mviActivity,
        moduleTemplateData.srcDir.resolve("${mPageName}Activity.kt")
    )
    if (mIsGenerateActivityLayout) {
        // 保存xml
        save(
            baseListXml("${packageNameStr}.${mPageName}Activity"),
            moduleTemplateData.resDir.resolve("layout/${mActivityLayoutName}.xml")
        )
    }
    save(
        mviActivityViewModel,
        File(
            moduleTemplateData.rootDir.absolutePath
                    + "/src/main/java/"
                    + rootPath.replace(".", "/")
                    + "/mvi/vm/"
        ).apply { mkdirs() }
            .resolve("${mPageName}ViewModel.kt")
    )
    save(
        mviActivityRepository,
        File(
            moduleTemplateData.rootDir.absolutePath
                    + "/src/main/java/"
                    + rootPath.replace(".", "/")
                    + "/mvi/repository/"
        ).apply { mkdirs() }
            .resolve("${mPageName}Repository.kt")
    )

    save(
        listBean,
        File(
            moduleTemplateData.rootDir.absolutePath
                    + "/src/main/java/"
                    + rootPath.replace(".", "/")
                    + "/bean/"
        ).apply { mkdirs() }
            .resolve("${mBeanClass}.kt")
    )
    save(
        listAdapter,
        File(
            moduleTemplateData.rootDir.absolutePath
                    + "/src/main/java/"
                    + rootPath.replace(".", "/")
                    + "/adapter/"
        ).apply { mkdirs() }
            .resolve("${mAdapterClass}.kt")
    )

    save(
        baseXml(),
        moduleTemplateData.resDir.resolve(
            "layout/item${
                getLayoutName(
                    mPageName
                )
            }.xml"
        )
    )

    addActivityToManifest(
        this,
        moduleTemplateData,
        "${packageNameStr}.${mPageName}Activity".substring(1)
    )

    if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT) {
        val mviAppModule = mviAppModule(rootPath)
        val path = moduleTemplateData.rootDir.absolutePath + "/src/main/java/" +
                rootPath.replace(".", "/") +
                "/mvi/di/"
        if (!File(path + "AppModule.kt").exists()) {
            save(
                mviAppModule,
                File(path)
                    .apply { mkdirs() }
                    .resolve("AppModule.kt")
            )
        }
    }
}