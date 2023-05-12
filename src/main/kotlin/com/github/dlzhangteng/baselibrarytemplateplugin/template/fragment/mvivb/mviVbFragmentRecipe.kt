package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvivb

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.github.dlzhangteng.baselibrarytemplateplugin.template.DependencyInjectionEnum
import com.github.dlzhangteng.baselibrarytemplateplugin.template.layout.basePageXml
import java.io.File


fun RecipeExecutor.mviVbFragmentRecipe(
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
    val mviFragment = mviVbFragment(
        rootPath,
        packageNameStr,
        mPageName,
        mFragmentLayoutName
    )
    val mviFragmentViewModel = mviVbFragmentViewModel(
        rootPath,
        mPageName
    )
    val mviFragmentRepository = mviVbFragmentRepository(
        rootPath,
        mPageName
    )
    // 保存Activity
    save(
        mviFragment,
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
}