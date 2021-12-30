package com.github.dlzhangteng.baselibrarytemplateplugin.template.other.header

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.generateManifest
import com.github.dlzhangteng.baselibrarytemplateplugin.template.layout.baseListXml
import com.github.dlzhangteng.baselibrarytemplateplugin.template.layout.baseXml
import com.github.dlzhangteng.baselibrarytemplateplugin.template.other.adapter.baseBean
import java.io.File


fun RecipeExecutor.headerOrFooterAdapterRecipe(
    moduleTemplateData: ModuleTemplateData,
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
    val headerOrFooterAdapter =
        headerOrFooterAdapter(rootPath, mBeanClass, mAdapterClass)

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
        headerOrFooterAdapter,
        File(
            moduleTemplateData.rootDir.absolutePath
                    + "/src/main/java/"
                    + rootPath.replace(".", "/")
                    + "/adapter/"
        ).apply { mkdirs() }
            .resolve("${mAdapterClass}.kt")
    )
}