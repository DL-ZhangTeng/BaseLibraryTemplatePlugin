package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.generateManifest
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.src.app_package.ui.baseActivityKt
import com.github.dlzhangteng.baselibrarytemplateplugin.template.common.res.layout.baseXml


fun RecipeExecutor.baseActivityRecipe(
    moduleTemplateData: ModuleTemplateData,
    mRootPackageName: String,
    mPageName: String,
    mActivityLayoutName: String,
    mIsGenerateActivityLayout: Boolean,
    mActivityPackageName: String,

    ) {
    val (_, srcOut, resOut) = moduleTemplateData

    generateManifest(
        moduleData = moduleTemplateData,
        activityClass = "${mPageName}Activity",
        packageName = ".ui.$mActivityPackageName",
        isLauncher = false,
        hasNoActionBar = false,
        generateActivityTitle = false
    )

    val baseActivity = baseActivityKt(mRootPackageName, mActivityPackageName, mPageName)
    // 保存Activity
    save(
        baseActivity,
        srcOut.resolve("${mActivityPackageName}/${mPageName}Activity.kt")
    )
    if (mIsGenerateActivityLayout) {
        // 保存xml
        save(baseXml(), resOut.resolve("layout/${mActivityLayoutName}.xml"))
    }
}