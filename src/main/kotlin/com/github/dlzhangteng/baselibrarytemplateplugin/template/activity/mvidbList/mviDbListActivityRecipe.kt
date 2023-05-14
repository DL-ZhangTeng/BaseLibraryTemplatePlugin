package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvidbList

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.github.dlzhangteng.baselibrarytemplateplugin.template.DependencyInjectionEnum
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvi.mviActivityRepository
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvi.mviActivityViewModel
import com.github.dlzhangteng.baselibrarytemplateplugin.template.addActivityToManifest
import com.github.dlzhangteng.baselibrarytemplateplugin.template.getLayoutName
import com.github.dlzhangteng.baselibrarytemplateplugin.template.layout.baseDbXml
import com.github.dlzhangteng.baselibrarytemplateplugin.template.layout.mviDbListXml
import com.github.dlzhangteng.baselibrarytemplateplugin.template.other.bindingadapter.bindingAdapter
import com.github.dlzhangteng.baselibrarytemplateplugin.template.other.bindingadapter.bindingBean
import java.io.File


fun RecipeExecutor.mviDbListActivityRecipe(
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
    val mviDbActivity = mviDbListActivity(
        rootPath,
        packageNameStr,
        mPageName,
        mActivityLayoutName,
        mBeanClass,
        mAdapterClass,
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
    val listBean = bindingBean(rootPath, mBeanClass)
    val listAdapter =
        bindingAdapter(
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
        mviDbActivity,
        moduleTemplateData.srcDir.resolve("${mPageName}Activity.kt")
    )
    if (mIsGenerateActivityLayout) {
        // 保存xml
        save(
            mviDbListXml(rootPath, packageNameStr, mPageName),
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
        baseDbXml(rootPath, mBeanClass),
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
}