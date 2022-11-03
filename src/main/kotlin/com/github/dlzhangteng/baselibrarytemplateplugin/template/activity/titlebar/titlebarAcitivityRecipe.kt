package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.titlebar

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.github.dlzhangteng.baselibrarytemplateplugin.template.addActivityToManifest
import com.github.dlzhangteng.baselibrarytemplateplugin.template.layout.titleXml


fun RecipeExecutor.titlebarAcitivityRecipe(
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
    val rootPath =
        if (packageNameStr.isNotEmpty()) moduleTemplateData.projectTemplateData.applicationPackage.toString()
        else mActivityPackageName
    val baseActivity = titlebarAcitivity(
        rootPath,
        packageNameStr,
        mPageName,
        mActivityLayoutName
    )
    // 保存Activity

    save(
        baseActivity,
        moduleTemplateData.srcDir.resolve("${mPageName}Activity.kt")
    )
    if (mIsGenerateActivityLayout) {
        // 保存xml
        save(titleXml(), moduleTemplateData.resDir.resolve("layout/${mActivityLayoutName}.xml"))
    }

    addActivityToManifest(
        this,
        moduleTemplateData,
        "${packageNameStr}.${mPageName}Activity".substring(1)
    )
}