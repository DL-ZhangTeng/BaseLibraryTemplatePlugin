package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.generateManifest
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.src.app_package.ui.mvvmActivityKt
import com.github.dlzhangteng.baselibrarytemplateplugin.template.common.res.layout.mvvmXml


fun RecipeExecutor.mvvmActivityRecipe(
    moduleTemplateData: ModuleTemplateData,
    mRootPackageName: String,
    mPageName: String,
    mIsActivity: Boolean,
    mActivityLayoutName: String,
    mIsGenerateActivityLayout: Boolean,
    mActivityPackageName: String,

    ) {
    val (projectData, srcOut, resOut) = moduleTemplateData
    val ktOrJavaExt = projectData.language.extension



    if (mIsActivity) {

        generateManifest(
            moduleData = moduleTemplateData,
            activityClass = "${mPageName}Activity",
            packageName = ".ui.$mActivityPackageName",
            isLauncher = false,
            hasNoActionBar = false,
            generateActivityTitle = false
        )

        val mvvmActivity = mvvmActivityKt(mRootPackageName, mActivityPackageName, mPageName)
        // 保存Activity
        save(
            mvvmActivity,
            srcOut.resolve("${mActivityPackageName}/${mPageName}Activity.${ktOrJavaExt}")
        )
        if (mIsGenerateActivityLayout) {
            // 保存xml
            save(mvvmXml(), resOut.resolve("layout/${mActivityLayoutName}.xml"))
        }
    }
}