package com.github.dlzhangteng.baselibrarytemplateplugin.template.other.bindingadapter

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.github.dlzhangteng.baselibrarytemplateplugin.template.layout.baseDbXml
import java.io.File


fun RecipeExecutor.bindingAdapterRecipe(
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
    val rootPath =
        if (packageNameStr.isNotEmpty()) moduleTemplateData.projectTemplateData.applicationPackage.toString()
        else mAdapterPackageName

    val baseBean =
        bindingBean(rootPath, mBeanClass)
    val bindingAdapter =
        bindingAdapter(
            rootPath,
            mAdapterLayoutName,
            mBeanClass,
            mAdapterClass
        )
    if (mIsGenerateAdapterLayout) {
        // 保存xml
        save(
            baseDbXml(rootPath, mBeanClass),
            moduleTemplateData.resDir.resolve("layout/${mAdapterLayoutName}.xml")
        )
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
        bindingAdapter,
        File(
            moduleTemplateData.rootDir.absolutePath
                    + "/src/main/java/"
                    + rootPath.replace(".", "/")
                    + "/adapter/"
        ).apply { mkdirs() }
            .resolve("${mAdapterClass}.kt")
    )
}