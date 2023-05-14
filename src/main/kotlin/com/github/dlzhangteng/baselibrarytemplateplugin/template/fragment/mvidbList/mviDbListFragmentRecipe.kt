package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvidbList

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.github.dlzhangteng.baselibrarytemplateplugin.template.DependencyInjectionEnum
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvi.mviAppModule
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvi.mviFragmentRepository
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvi.mviFragmentViewModel
import com.github.dlzhangteng.baselibrarytemplateplugin.template.getLayoutName
import com.github.dlzhangteng.baselibrarytemplateplugin.template.layout.baseDbXml
import com.github.dlzhangteng.baselibrarytemplateplugin.template.layout.mviDbListFragmentXml
import com.github.dlzhangteng.baselibrarytemplateplugin.template.other.bindingadapter.bindingAdapter
import com.github.dlzhangteng.baselibrarytemplateplugin.template.other.bindingadapter.bindingBean
import java.io.File


fun RecipeExecutor.mviDbListFragmentRecipe(
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
    val mviDbFragment = mviDbListFragment(
        rootPath,
        packageNameStr,
        mPageName,
        mFragmentLayoutName,
        mBeanClass,
        mAdapterClass,
        mDependencyInjectionEnum
    )
    val mviDbFragmentViewModel = mviFragmentViewModel(
        rootPath,
        mPageName,
        mDependencyInjectionEnum
    )
    val mviDbFragmentRepository = mviFragmentRepository(
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
        mviDbFragment,
        moduleTemplateData.srcDir.resolve("${mPageName}Fragment.kt")
    )
    if (mIsGenerateActivityLayout) {
        // 保存xml
        save(
            mviDbListFragmentXml(rootPath, packageNameStr, mPageName),
            moduleTemplateData.resDir.resolve("layout/${mFragmentLayoutName}.xml")
        )
    }
    save(
        mviDbFragmentViewModel,
        File(
            moduleTemplateData.rootDir.absolutePath
                    + "/src/main/java/"
                    + rootPath.replace(".", "/")
                    + "/mvi/vm/"
        ).apply { mkdirs() }
            .resolve("${mPageName}FragmentViewModel.kt")
    )
    save(
        mviDbFragmentRepository,
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
        baseDbXml(rootPath, mBeanClass),
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