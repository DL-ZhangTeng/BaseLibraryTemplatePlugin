package com.github.dlzhangteng.baselibrarytemplateplugin.template.other.adapter

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.github.dlzhangteng.baselibrarytemplateplugin.template.layout.baseXml
import java.io.File


fun RecipeExecutor.baseAdapterRecipe(
    moduleTemplateData: ModuleTemplateData,
    mAdapterLayoutName: String,
    mIsGenerateAdapterLayout: Boolean,
    mBeanClass: String,
    mAdapterClass: String,
    mAdapterPackageName: String,
) {
    val packageNameStr =
        if (moduleTemplateData.projectTemplateData.applicationPackage == null) ""
        else mAdapterPackageName
            .replace(moduleTemplateData.projectTemplateData.applicationPackage.toString(), "")
            .replace(".", "")
    val rootPath =
        if (!packageNameStr.isNullOrEmpty()) mAdapterPackageName.replace(".$packageNameStr", "")
        else mAdapterPackageName

    val baseBean =
        baseBean(rootPath, mBeanClass)
    val baseAdapter =
        baseAdapter(rootPath, mAdapterLayoutName, mBeanClass, mAdapterClass)
    if (mIsGenerateAdapterLayout) {
        // 保存xml
        save(baseXml(), moduleTemplateData.resDir.resolve("layout/${mAdapterLayoutName}.xml"))
    }

    save(
        baseBean,
        File(
            moduleTemplateData.rootDir.absolutePath
                    + "/src/main/java/"
                    + rootPath.replace(".", "/")
                    + "/bean/"
        ).apply { mkdirs() }
            .resolve("${mBeanClass}.kt")
    )
    save(
        baseAdapter,
        File(
            moduleTemplateData.rootDir.absolutePath
                    + "/src/main/java/"
                    + rootPath.replace(".", "/")
                    + "/adapter/"
        ).apply { mkdirs() }
            .resolve("${mAdapterClass}.kt")
    )
}