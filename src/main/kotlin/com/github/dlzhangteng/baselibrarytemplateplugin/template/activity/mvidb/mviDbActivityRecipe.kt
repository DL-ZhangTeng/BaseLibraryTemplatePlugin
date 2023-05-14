package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvidb

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.github.dlzhangteng.baselibrarytemplateplugin.template.DependencyInjectionEnum
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvi.mviActivityRepository
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvi.mviActivityViewModel
import com.github.dlzhangteng.baselibrarytemplateplugin.template.addActivityToManifest
import com.github.dlzhangteng.baselibrarytemplateplugin.template.layout.mviDbXml
import java.io.File


fun RecipeExecutor.mviDbActivityRecipe(
    moduleTemplateData: ModuleTemplateData,
    mPageName: String,
    mActivityLayoutName: String,
    mIsGenerateActivityLayout: Boolean,
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
    val mviDbActivity = mviDbActivity(
        rootPath,
        packageNameStr,
        mPageName,
        mActivityLayoutName,
        mDependencyInjectionEnum
    )
    val mviDbActivityViewModel = mviActivityViewModel(
        rootPath,
        mPageName,
        mDependencyInjectionEnum
    )
    val mviActivityRepository = mviActivityRepository(
        rootPath,
        mPageName,
        mDependencyInjectionEnum
    )
    // 保存Activity
    save(
        mviDbActivity,
        moduleTemplateData.srcDir.resolve("${mPageName}Activity.kt")
    )
    if (mIsGenerateActivityLayout) {
        // 保存xml
        save(
            mviDbXml(rootPath, packageNameStr, mPageName),
            moduleTemplateData.resDir.resolve("layout/${mActivityLayoutName}.xml")
        )
    }
    save(
        mviDbActivityViewModel,
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

    addActivityToManifest(
        this,
        moduleTemplateData,
        "${packageNameStr}.${mPageName}Activity".substring(1)
    )
}