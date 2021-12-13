package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.list

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.generateManifest
import com.github.dlzhangteng.baselibrarytemplateplugin.template.layout.baseXml


fun RecipeExecutor.listActivityRecipe(
    moduleTemplateData: ModuleTemplateData,
    mRootPackageName: String,
    mPageName: String,
    mActivityLayoutName: String,
    mIsGenerateActivityLayout: Boolean,
    mBeanClass: String,
    mIsBean: Boolean,
    mAdapterClass: String,
    mIsAdapter: Boolean,
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

    val listActivity =
        listActivityKt(mRootPackageName, mActivityPackageName, mPageName, mBeanClass, mAdapterClass)
    val listBean =
        listBean(mRootPackageName, mBeanClass)
    val listAdapter =
        listAdapter(mRootPackageName, mPageName, mBeanClass, mAdapterClass)
    // 保存Activity
    save(
        listActivity,
        srcOut.resolve("${mActivityPackageName}/${mPageName}Activity.kt")
    )
    if (mIsGenerateActivityLayout) {
        // 保存xml
        save(baseXml(), resOut.resolve("layout/${mActivityLayoutName}.xml"))
    }

    if (mIsBean) {
        save(
            listBean,
            srcOut.resolve("bean/${mBeanClass}.kt")
        )
    }
    if (mIsAdapter) {
        save(
            listAdapter,
            srcOut.resolve("adapter/${mAdapterClass}.kt")
        )

        save(
            baseXml(),
            resOut.resolve("layout/item_${getLayoutName(mPageName)}.xml")
        )
    }
}