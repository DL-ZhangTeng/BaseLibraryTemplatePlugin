package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvivbList

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.github.dlzhangteng.baselibrarytemplateplugin.template.DependencyInjectionEnum
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvi.mviAppModule
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvi.mviFragmentRepository
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvi.mviFragmentViewModel
import com.github.dlzhangteng.baselibrarytemplateplugin.template.getLayoutName
import com.github.dlzhangteng.baselibrarytemplateplugin.template.layout.baseListXml
import com.github.dlzhangteng.baselibrarytemplateplugin.template.layout.baseXml
import com.github.dlzhangteng.baselibrarytemplateplugin.template.other.adapter.baseAdapter
import com.github.dlzhangteng.baselibrarytemplateplugin.template.other.adapter.baseBean
import java.io.File


fun RecipeExecutor.mviVbListFragmentRecipe(
    moduleTemplateData: ModuleTemplateData,
    mPageName: String,
    mFragmentLayoutName: String,
    mIsGenerateActivityLayout: Boolean,
    mBeanClass: String,
    mAdapterClass: String,
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
    val mviFragment = mviVbListFragment(
        rootPath,
        packageNameStr,
        mPageName,
        mFragmentLayoutName,
        mBeanClass,
        mAdapterClass,
        mDependencyInjectionEnum
    )
    val mviFragmentViewModel = mviFragmentViewModel(
        rootPath,
        mPageName,
        mDependencyInjectionEnum
    )
    val mviFragmentRepository = mviFragmentRepository(
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
        mviFragment,
        moduleTemplateData.srcDir.resolve("${mPageName}Fragment.kt")
    )
    if (mIsGenerateActivityLayout) {
        // 保存xml
        save(
            baseListXml("${packageNameStr}.${mPageName}Fragment"),
            moduleTemplateData.resDir.resolve("layout/${mFragmentLayoutName}.xml")
        )
    }
    save(
        mviFragmentViewModel,
        File(
            moduleTemplateData.rootDir.absolutePath
                    + "/src/main/java/"
                    + rootPath.replace(".", "/")
                    + "/mvi/vm/"
        ).apply { mkdirs() }
            .resolve("${mPageName}FragmentViewModel.kt")
    )
    save(
        mviFragmentRepository,
        File(
            moduleTemplateData.rootDir.absolutePath
                    + "/src/main/java/"
                    + rootPath.replace(".", "/")
                    + "/mvi/repository/"
        ).apply { mkdirs() }
            .resolve("${mPageName}FragmentRepository.kt")
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