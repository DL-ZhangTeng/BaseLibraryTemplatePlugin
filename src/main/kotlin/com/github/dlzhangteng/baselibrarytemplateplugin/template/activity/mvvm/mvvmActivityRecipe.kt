package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvm

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.generateManifest
import com.github.dlzhangteng.baselibrarytemplateplugin.template.layout.baseXml
import java.io.File


fun RecipeExecutor.mvvmActivityRecipe(
    moduleTemplateData: ModuleTemplateData,
    mPageName: String,
    mActivityLayoutName: String,
    mIsGenerateActivityLayout: Boolean,
    mActivityPackageName: String,
) {
    generateManifest(
        moduleData = moduleTemplateData,
        activityClass = "${mPageName}Activity",
        packageName = mActivityPackageName,
        isLauncher = false,
        hasNoActionBar = false,
        generateActivityTitle = false
    )
    val packageNameStr =
        if (moduleTemplateData.projectTemplateData.applicationPackage == null) ""
        else mActivityPackageName
            .replace(moduleTemplateData.projectTemplateData.applicationPackage.toString(), "")
            .replace(".", "")
    val rootPath =
        if (!packageNameStr.isNullOrEmpty()) mActivityPackageName.replace(".$packageNameStr", "")
        else mActivityPackageName
    val mvvmActivity = mvvmActivity(
        rootPath,
        packageNameStr,
        mPageName
    )
    val mvvmActivityViewModel = mvvmActivityViewModel(
        rootPath,
        mPageName
    )
    // 保存Activity
    save(
        mvvmActivity,
        moduleTemplateData.srcDir.resolve("${mPageName}Activity.kt")
    )
    if (mIsGenerateActivityLayout) {
        // 保存xml
        save(baseXml(), moduleTemplateData.resDir.resolve("layout/${mActivityLayoutName}.xml"))
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
}