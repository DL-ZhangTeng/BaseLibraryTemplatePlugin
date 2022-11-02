package com.github.dlzhangteng.baselibrarytemplateplugin.template.widget.dialog

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.github.dlzhangteng.baselibrarytemplateplugin.template.layout.baseXml
import java.io.File


fun RecipeExecutor.baseDialogRecipe(
    moduleTemplateData: ModuleTemplateData,
    mDialogLayoutName: String,
    mIsGenerateDialogLayout: Boolean,
    mDialogClass: String,
    mDialogPackageName: String,
) {
    val packageNameStr =
        if (moduleTemplateData.projectTemplateData.applicationPackage == null) ""
        else mDialogPackageName
            .replace(moduleTemplateData.projectTemplateData.applicationPackage.toString(), "")
    val rootPath =
        if (packageNameStr.isNotEmpty()) moduleTemplateData.projectTemplateData.applicationPackage.toString()
        else mDialogPackageName

    val baseDialog =
        baseDialog(rootPath, mDialogLayoutName, mDialogClass)
    if (mIsGenerateDialogLayout) {
        // 保存xml
        save(baseXml(), moduleTemplateData.resDir.resolve("layout/${mDialogLayoutName}.xml"))
    }

    save(
        baseDialog,
        File(
            moduleTemplateData.rootDir.absolutePath
                    + "/src/main/java/"
                    + rootPath.replace(".", "/")
                    + "/widget/"
        ).apply { mkdirs() }
            .resolve("${mDialogClass}.kt")
    )
}