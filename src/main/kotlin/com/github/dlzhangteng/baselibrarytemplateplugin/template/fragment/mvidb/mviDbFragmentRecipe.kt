package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvidb

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.github.dlzhangteng.baselibrarytemplateplugin.template.layout.mvvmDbFragmentXml
import java.io.File


fun RecipeExecutor.mviDbFragmentRecipe(
    moduleTemplateData: ModuleTemplateData,
    mPageName: String,
    mFragmentLayoutName: String,
    mIsGenerateActivityLayout: Boolean,
    mFragmentPackageName: String,
) {
    val packageNameStr =
        if (moduleTemplateData.projectTemplateData.applicationPackage == null) ""
        else mFragmentPackageName
            .replace(moduleTemplateData.projectTemplateData.applicationPackage.toString(), "")
    val rootPath =
        if (packageNameStr.isNotEmpty()) moduleTemplateData.projectTemplateData.applicationPackage.toString()
        else mFragmentPackageName
    val mviDbFragment = mviDbFragment(
        rootPath,
        packageNameStr,
        mPageName,
        mFragmentLayoutName
    )
    val mviDbFragmentViewModel = mviDbFragmentViewModel(
        rootPath,
        mPageName
    )
    val mviDbFragmentRepository = mviDbFragmentRepository(
        rootPath,
        mPageName
    )
    // 保存Activity
    save(
        mviDbFragment,
        moduleTemplateData.srcDir.resolve("${mPageName}Fragment.kt")
    )
    if (mIsGenerateActivityLayout) {
        // 保存xml
        save(
            mvvmDbFragmentXml(rootPath, packageNameStr, mPageName),
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
}