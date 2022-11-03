package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvmdb

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.github.dlzhangteng.baselibrarytemplateplugin.template.addActivityToManifest
import com.github.dlzhangteng.baselibrarytemplateplugin.template.layout.mvvmDbXml
import java.io.File


fun RecipeExecutor.mvvmDbActivityRecipe(
    moduleTemplateData: ModuleTemplateData,
    mPageName: String,
    mActivityLayoutName: String,
    mIsGenerateActivityLayout: Boolean,
    mActivityPackageName: String,
) {
    val packageNameStr =
        if (moduleTemplateData.projectTemplateData.applicationPackage == null) ""
        else mActivityPackageName
            .replace(moduleTemplateData.projectTemplateData.applicationPackage.toString(), "")
    val rootPath =
        if (packageNameStr.isNotEmpty()) moduleTemplateData.projectTemplateData.applicationPackage.toString()
        else mActivityPackageName
    val mvvmDbActivity = mvvmDbActivity(
        rootPath,
        packageNameStr,
        mPageName,
        mActivityLayoutName
    )
    val mvvmDbActivityViewModel = mvvmDbActivityViewModel(
        rootPath,
        mPageName
    )
    val mvvmActivityRepository = mvvmDbActivityRepository(
        rootPath,
        mPageName
    )
    // 保存Activity
    save(
        mvvmDbActivity,
        moduleTemplateData.srcDir.resolve("${mPageName}DbActivity.kt")
    )
    if (mIsGenerateActivityLayout) {
        // 保存xml
        save(
            mvvmDbXml(rootPath, packageNameStr, mPageName),
            moduleTemplateData.resDir.resolve("layout/${mActivityLayoutName}.xml")
        )
    }
    save(
        mvvmDbActivityViewModel,
        File(
            moduleTemplateData.rootDir.absolutePath
                    + "/src/main/java/"
                    + rootPath.replace(".", "/")
                    + "/mvvm/vm/"
        ).apply { mkdirs() }
            .resolve("${mPageName}DbViewModel.kt")
    )
    save(
        mvvmActivityRepository,
        File(
            moduleTemplateData.rootDir.absolutePath
                    + "/src/main/java/"
                    + rootPath.replace(".", "/")
                    + "/mvvm/repository/"
        ).apply { mkdirs() }
            .resolve("${mPageName}DbRepository.kt")
    )

    addActivityToManifest(
        this,
        moduleTemplateData,
        "${packageNameStr}.${mPageName}DbActivity".substring(1)
    )
}