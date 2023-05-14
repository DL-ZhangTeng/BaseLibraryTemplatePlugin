package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvmList

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.github.dlzhangteng.baselibrarytemplateplugin.template.DependencyInjectionEnum
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvm.mvvmActivityRepository
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvm.mvvmActivityViewModel
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvm.mvvmAppModule
import com.github.dlzhangteng.baselibrarytemplateplugin.template.addActivityToManifest
import com.github.dlzhangteng.baselibrarytemplateplugin.template.getLayoutName
import com.github.dlzhangteng.baselibrarytemplateplugin.template.layout.baseListXml
import com.github.dlzhangteng.baselibrarytemplateplugin.template.layout.baseXml
import com.github.dlzhangteng.baselibrarytemplateplugin.template.other.adapter.baseAdapter
import com.github.dlzhangteng.baselibrarytemplateplugin.template.other.adapter.baseBean
import java.io.File


fun RecipeExecutor.mvvmListActivityRecipe(
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
    val mvvmActivity = mvvmListActivity(
        rootPath,
        packageNameStr,
        mPageName,
        mActivityLayoutName,
        mBeanClass,
        mAdapterClass,
        mDependencyInjectionEnum
    )
    val mvvmActivityViewModel = mvvmActivityViewModel(
        rootPath,
        mPageName,
        mDependencyInjectionEnum
    )
    val mvvmActivityRepository = mvvmActivityRepository(
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
        mvvmActivity,
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
        mvvmActivityViewModel,
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