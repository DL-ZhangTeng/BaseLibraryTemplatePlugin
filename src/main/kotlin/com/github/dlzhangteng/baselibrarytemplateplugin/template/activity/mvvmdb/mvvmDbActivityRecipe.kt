package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvmdb

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.github.dlzhangteng.baselibrarytemplateplugin.template.DependencyInjectionEnum
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvm.mvvmActivityRepository
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvm.mvvmActivityViewModel
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvm.mvvmAppModule
import com.github.dlzhangteng.baselibrarytemplateplugin.template.addActivityToManifest
import com.github.dlzhangteng.baselibrarytemplateplugin.template.layout.mvvmDbXml
import java.io.File


fun RecipeExecutor.mvvmDbActivityRecipe(
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
    val mvvmDbActivity = mvvmDbActivity(
        rootPath,
        packageNameStr,
        mPageName,
        mActivityLayoutName,
        mDependencyInjectionEnum
    )
    val mvvmDbActivityViewModel = mvvmActivityViewModel(
        rootPath,
        mPageName,
        mDependencyInjectionEnum
    )
    val mvvmActivityRepository = mvvmActivityRepository(
        rootPath,
        mPageName,
        mDependencyInjectionEnum
    )
    // 保存Activity
    save(
        mvvmDbActivity,
        moduleTemplateData.srcDir.resolve("${mPageName}Activity.kt")
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
            .resolve("${mPageName}ViewModel.kt")
    )
    save(
        mvvmActivityRepository,
        File(
            moduleTemplateData.rootDir.absolutePath
                    + "/src/main/java/"
                    + rootPath.replace(".", "/")
                    + "/mvvm/repository/"
        ).apply { mkdirs() }
            .resolve("${mPageName}Repository.kt")
    )

    addActivityToManifest(
        this,
        moduleTemplateData,
        "${packageNameStr}.${mPageName}Activity".substring(1)
    )

    if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT) {
        val mvvmAppModule = mvvmAppModule(rootPath)
        val path = moduleTemplateData.rootDir.absolutePath + "/src/main/java/" +
                rootPath.replace(".", "/") +
                "/mvvm/di/"
        if (!File(path + "AppModule.kt").exists()) {
            save(
                mvvmAppModule,
                File(path)
                    .apply { mkdirs() }
                    .resolve("AppModule.kt")
            )
        }
    }
}