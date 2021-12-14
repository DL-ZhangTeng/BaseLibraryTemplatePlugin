package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.list

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.list.listAdapter
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.list.listBean
import com.github.dlzhangteng.baselibrarytemplateplugin.template.layout.baseListXml
import com.github.dlzhangteng.baselibrarytemplateplugin.template.layout.baseXml
import java.io.File


fun RecipeExecutor.listFragmentRecipe(
    moduleTemplateData: ModuleTemplateData,
    mPageName: String,
    mActivityLayoutName: String,
    mIsGenerateActivityLayout: Boolean,
    mBeanClass: String,
    mAdapterClass: String,
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

    val listFragment =
        listFragment(rootPath, packageNameStr, mPageName, mBeanClass, mAdapterClass)
    val listBean =
        listBean(rootPath, mBeanClass)
    val listAdapter =
        listAdapter(rootPath, mPageName, mBeanClass, mAdapterClass)
    // 保存Activity
    save(
        listFragment,
        moduleTemplateData.srcDir.resolve("${mPageName}Fragment.kt")
    )
    if (mIsGenerateActivityLayout) {
        // 保存xml
        save(baseXml(), moduleTemplateData.resDir.resolve("layout/${mActivityLayoutName}.xml"))
    }

    save(
        listBean,
        File(
            moduleTemplateData.rootDir.absolutePath
                    + "/src/main/java/"
                    + rootPath.replace(".", "/")
                    + "/bean/"
        ).apply { mkdirs() }
            .resolve("${mBeanClass}.kt")
    )
    save(
        listAdapter,
        File(
            moduleTemplateData.rootDir.absolutePath
                    + "/src/main/java/"
                    + rootPath.replace(".", "/")
                    + "/adapter/"
        ).apply { mkdirs() }
            .resolve("${mAdapterClass}.kt")
    )

    save(
        baseListXml(),
        moduleTemplateData.resDir.resolve(
            "layout/item${
                getLayoutName(
                    mPageName
                )
            }.xml"
        )
    )
}