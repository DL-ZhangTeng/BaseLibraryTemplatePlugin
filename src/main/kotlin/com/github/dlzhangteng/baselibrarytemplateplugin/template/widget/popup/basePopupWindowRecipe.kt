package com.github.dlzhangteng.baselibrarytemplateplugin.template.widget.popup

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.github.dlzhangteng.baselibrarytemplateplugin.template.layout.baseXml
import java.io.File

fun RecipeExecutor.basePopupWindowRecipe(
    moduleTemplateData: ModuleTemplateData,
    mPopupWindowLayoutName: String,
    mIsGeneratePopupWindowLayout: Boolean,
    mPopupWindowClass: String,
    mPopupWindowPackageName: String,
) {
    val packageNameStr =
        if (moduleTemplateData.projectTemplateData.applicationPackage == null) ""
        else mPopupWindowPackageName
            .replace(moduleTemplateData.projectTemplateData.applicationPackage.toString(), "")
    val rootPath =
        if (packageNameStr.isNotEmpty()) moduleTemplateData.projectTemplateData.applicationPackage.toString()
        else mPopupWindowPackageName

    val basePopupWindow =
        basePopupWindow(rootPath, mPopupWindowLayoutName, mPopupWindowClass)
    if (mIsGeneratePopupWindowLayout) {
        // 保存xml
        save(baseXml(), moduleTemplateData.resDir.resolve("layout/${mPopupWindowLayoutName}.xml"))
    }

    save(
        basePopupWindow,
        File(
            moduleTemplateData.rootDir.absolutePath
                    + "/src/main/java/"
                    + rootPath.replace(".", "/")
                    + "/widget/"
        ).apply { mkdirs() }
            .resolve("${mPopupWindowClass}.kt")
    )
}