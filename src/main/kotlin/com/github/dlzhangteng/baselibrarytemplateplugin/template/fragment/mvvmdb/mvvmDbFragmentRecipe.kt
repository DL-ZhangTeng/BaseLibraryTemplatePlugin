package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvvmdb

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.generateManifest
import com.github.dlzhangteng.baselibrarytemplateplugin.template.layout.mvvmDbFragmentXml
import java.io.File


fun RecipeExecutor.mvvmDbFragmentRecipe(
    moduleTemplateData: ModuleTemplateData,
    mPageName: String,
    mActivityLayoutName: String,
    mIsGenerateActivityLayout: Boolean,
    mActivityPackageName: String,
) {
    val packageNameStr =
        if (moduleTemplateData.projectTemplateData.applicationPackage == null) ""
        else mActivityPackageName
            .replace(moduleTemplateData.projectTemplateData.applicationPackage.toString(), "")
            .replace(".", "")
    val rootPath =
        if (!packageNameStr.isNullOrEmpty()) mActivityPackageName.replace(".$packageNameStr", "")
        else mActivityPackageName
    val mvvmDbFragment = mvvmDbFragment(
        rootPath,
        packageNameStr,
        mPageName
    )
    val mvvmDbFragmentViewModel = mvvmDbFragmentViewModel(
        rootPath,
        mPageName
    )
    // 保存Activity
    save(
        mvvmDbFragment,
        moduleTemplateData.srcDir.resolve("${mPageName}DbFragment.kt")
    )
    if (mIsGenerateActivityLayout) {
        // 保存xml
        save(
            mvvmDbFragmentXml(rootPath, packageNameStr, mPageName),
            moduleTemplateData.resDir.resolve("layout/${mActivityLayoutName}.xml")
        )
    }
    save(
        mvvmDbFragmentViewModel,
        File(
            moduleTemplateData.rootDir.absolutePath
                    + "/src/main/java/"
                    + rootPath.replace(".", "/")
                    + "/mvvm/vm/"
        ).apply { mkdirs() }
            .resolve("${mPageName}DbFragmentViewModel.kt")
    )
}