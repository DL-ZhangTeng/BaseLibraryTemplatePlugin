package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvmdbList

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvmdb.mvvmDbActivityRepository
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvmdb.mvvmDbActivityViewModel
import com.github.dlzhangteng.baselibrarytemplateplugin.template.addActivityToManifest
import com.github.dlzhangteng.baselibrarytemplateplugin.template.getLayoutName
import com.github.dlzhangteng.baselibrarytemplateplugin.template.layout.baseXml
import com.github.dlzhangteng.baselibrarytemplateplugin.template.layout.mvvmDbListXml
import com.github.dlzhangteng.baselibrarytemplateplugin.template.other.adapter.baseAdapter
import com.github.dlzhangteng.baselibrarytemplateplugin.template.other.adapter.baseBean
import java.io.File


fun RecipeExecutor.mvvmDbListActivityRecipe(
    moduleTemplateData: ModuleTemplateData,
    mPageName: String,
    mActivityLayoutName: String,
    mIsGenerateActivityLayout: Boolean,
    mBeanClass: String,
    mAdapterClass: String,
    mActivityPackageName: String,
) {
    addActivityToManifest(
        moduleTemplateData,
        "${mPageName}DbActivity",
        mActivityPackageName,
    )
    val packageNameStr =
        if (moduleTemplateData.projectTemplateData.applicationPackage == null) ""
        else mActivityPackageName
            .replace(moduleTemplateData.projectTemplateData.applicationPackage.toString(), "")
    val rootPath =
        if (packageNameStr.isNotEmpty()) moduleTemplateData.projectTemplateData.applicationPackage.toString()
        else mActivityPackageName
    val mvvmDbActivity = mvvmDbListActivity(
        rootPath,
        packageNameStr,
        mPageName,
        mBeanClass,
        mAdapterClass
    )
    val mvvmDbActivityViewModel = mvvmDbActivityViewModel(
        rootPath,
        mPageName
    )
    val mvvmActivityRepository = mvvmDbActivityRepository(
        rootPath,
        mPageName
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
        mvvmDbActivity,
        moduleTemplateData.srcDir.resolve("${mPageName}DbActivity.kt")
    )
    if (mIsGenerateActivityLayout) {
        // 保存xml
        save(
            mvvmDbListXml(rootPath, packageNameStr, mPageName),
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
}