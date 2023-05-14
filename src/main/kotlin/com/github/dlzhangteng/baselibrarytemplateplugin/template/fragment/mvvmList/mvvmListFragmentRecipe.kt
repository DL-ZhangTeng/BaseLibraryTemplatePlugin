package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvvmList

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.github.dlzhangteng.baselibrarytemplateplugin.template.DependencyInjectionEnum
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvm.mvvmAppModule
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvvm.mvvmFragmentRepository
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvvm.mvvmFragmentViewModel
import com.github.dlzhangteng.baselibrarytemplateplugin.template.getLayoutName
import com.github.dlzhangteng.baselibrarytemplateplugin.template.layout.baseListXml
import com.github.dlzhangteng.baselibrarytemplateplugin.template.layout.baseXml
import com.github.dlzhangteng.baselibrarytemplateplugin.template.other.adapter.baseAdapter
import com.github.dlzhangteng.baselibrarytemplateplugin.template.other.adapter.baseBean
import java.io.File


fun RecipeExecutor.mvvmListFragmentRecipe(
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
    val mvvmFragment = mvvmListFragment(
        rootPath,
        packageNameStr,
        mPageName,
        mFragmentLayoutName,
        mBeanClass,
        mAdapterClass,
        mDependencyInjectionEnum
    )
    val mvvmFragmentViewModel = mvvmFragmentViewModel(
        rootPath,
        mPageName,
        mDependencyInjectionEnum
    )
    val mvvmFragmentRepository = mvvmFragmentRepository(
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
        mvvmFragment,
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
        mvvmFragmentViewModel,
        File(
            moduleTemplateData.rootDir.absolutePath
                    + "/src/main/java/"
                    + rootPath.replace(".", "/")
                    + "/mvvm/vm/"
        ).apply { mkdirs() }
            .resolve("${mPageName}FragmentViewModel.kt")
    )
    save(
        mvvmFragmentRepository,
        File(
            moduleTemplateData.rootDir.absolutePath
                    + "/src/main/java/"
                    + rootPath.replace(".", "/")
                    + "/mvvm/repository/"
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