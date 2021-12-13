package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.list

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.generateManifest
import com.github.dlzhangteng.baselibrarytemplateplugin.template.layout.baseXml
import java.io.File


fun RecipeExecutor.listActivityRecipe(
    moduleTemplateData: ModuleTemplateData,
    mPageName: String,
    mActivityLayoutName: String,
    mIsGenerateActivityLayout: Boolean,
    mBeanClass: String,
    mAdapterClass: String,
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

    val listActivity =
        listActivityKt(rootPath, packageNameStr, mPageName, mBeanClass, mAdapterClass)
    val listBean =
        listBean(rootPath, mBeanClass)
    val listAdapter =
        listAdapter(rootPath, mPageName, mBeanClass, mAdapterClass)
    // 保存Activity
    save(
        listActivity,
        moduleTemplateData.srcDir.resolve("${mPageName}Activity.kt")
    )
    if (mIsGenerateActivityLayout) {
        // 保存xml
        save(baseXml(), moduleTemplateData.resDir.resolve("layout/${mActivityLayoutName}.xml"))
    }

    save(
        listBean,
        if (moduleTemplateData.projectTemplateData.applicationPackage == null)
            moduleTemplateData.srcDir
        else
            File(
                moduleTemplateData.rootDir.absolutePath
                        + "/src/main/java/"
                        + rootPath.replace(".", "/")
            )
                .resolve("bean/${mBeanClass}.kt")
    )
    save(
        listAdapter,
        if (moduleTemplateData.projectTemplateData.applicationPackage == null)
            moduleTemplateData.srcDir
        else
            File(
                moduleTemplateData.rootDir.absolutePath
                        + "/src/main/java/"
                        + rootPath.replace(".", "/")
            )
                .resolve("adapter/${mAdapterClass}.kt")
    )

    save(
        baseXml(),
        moduleTemplateData.resDir.resolve("layout/item${getLayoutName(mPageName)}.xml")
    )
}