package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvvmvb

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.github.dlzhangteng.baselibrarytemplateplugin.template.DependencyInjectionEnum
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvm.mvvmAppModule
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvvm.mvvmFragmentRepository
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvvm.mvvmFragmentViewModel
import com.github.dlzhangteng.baselibrarytemplateplugin.template.layout.basePageXml
import java.io.File


fun RecipeExecutor.mvvmVbFragmentRecipe(
    moduleTemplateData: ModuleTemplateData,
    mPageName: String,
    mFragmentLayoutName: String,
    mIsGenerateActivityLayout: Boolean,
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
    val mvvmFragment = mvvmVbFragment(
        rootPath,
        packageNameStr,
        mPageName,
        mFragmentLayoutName,
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
    // 保存Activity
    save(
        mvvmFragment,
        moduleTemplateData.srcDir.resolve("${mPageName}Fragment.kt")
    )
    if (mIsGenerateActivityLayout) {
        // 保存xml
        save(
            basePageXml("${packageNameStr}.${mPageName}Fragment"),
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