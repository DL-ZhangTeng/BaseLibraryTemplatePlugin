package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvp

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.generateManifest
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvp.model.imodel.mvpIModel
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvp.model.mvpModel
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvp.presenter.ipresenter.mvpIPresenter
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvp.presenter.mvpPresenter
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvp.view.mvpView
import com.github.dlzhangteng.baselibrarytemplateplugin.template.layout.baseXml
import java.io.File


fun RecipeExecutor.mvpActivityRecipe(
    moduleTemplateData: ModuleTemplateData,
    mPageName: String,
    mActivityLayoutName: String,
    mIsGenerateActivityLayout: Boolean,
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
    val rootPath =
        if (packageNameStr.isNotEmpty()) moduleTemplateData.projectTemplateData.applicationPackage.toString()
        else mActivityPackageName

    val listActivity = mvpActivity(rootPath, packageNameStr, mPageName)
    val mvpIView = mvpView(rootPath, mPageName)
    val mvpIModel = mvpIModel(rootPath, mPageName)
    val mvpModel = mvpModel(rootPath, mPageName)
    val mvpIPresenter = mvpIPresenter(rootPath, mPageName)
    val mvpPresenter = mvpPresenter(rootPath, mPageName)
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
        mvpIView,
        File(
            moduleTemplateData.rootDir.absolutePath
                    + "/src/main/java/"
                    + rootPath.replace(".", "/")
                    + "/mvp/view/"
        ).apply { mkdirs() }
            .resolve("I${mPageName}View.kt")
    )

    save(
        mvpIModel,
        File(
            moduleTemplateData.rootDir.absolutePath
                    + "/src/main/java/"
                    + rootPath.replace(".", "/")
                    + "/mvp/model/imodel/"
        ).apply { mkdirs() }
            .resolve("I${mPageName}Model.kt")
    )

    save(
        mvpModel,
        File(
            moduleTemplateData.rootDir.absolutePath
                    + "/src/main/java/"
                    + rootPath.replace(".", "/")
                    + "/mvp/model/"
        ).apply { mkdirs() }
            .resolve("${mPageName}Model.kt")
    )

    save(
        mvpIPresenter,
        File(
            moduleTemplateData.rootDir.absolutePath
                    + "/src/main/java/"
                    + rootPath.replace(".", "/")
                    + "/mvp/presenter/ipresenter/"
        ).apply { mkdirs() }
            .resolve("I${mPageName}Presenter.kt")
    )

    save(
        mvpPresenter,
        File(
            moduleTemplateData.rootDir.absolutePath
                    + "/src/main/java/"
                    + rootPath.replace(".", "/")
                    + "/mvp/presenter/"
        ).apply { mkdirs() }
            .resolve("${mPageName}Presenter.kt")
    )
}