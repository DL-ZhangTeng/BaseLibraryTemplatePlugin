package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvp

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import com.github.dlzhangteng.baselibrarytemplateplugin.template.getLayoutName
import java.io.File
import java.util.*

val mvpActivityTemplate
    get() = template {
//        revision = 1
        name = "ZTBaseMvpActivity"
        description = "一键创建 BaseMvpActivity "
        minApi = MIN_API
        category = Category.Activity
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.ActivityGallery,
            WizardUiContext.MenuEntry,
            WizardUiContext.NewProject,
            WizardUiContext.NewModule
        )

        val mPageName = stringParameter {
            name = "Create Page Name"
            constraints = listOf(Constraint.UNIQUE, Constraint.NONEMPTY)
            default = "Main"
            help = "需要生成页面的名字,不需要再写 名字后缀:如Activity、Fragment,会自动生成,以及对应文件名后缀"
        }

        val mIsGenerateActivityLayout = booleanParameter {
            name = "Generate Activity Layout"
            default = true
            help = "默认勾选,如果使用已存在布局 则无需勾选,若不勾选,创建后记得修改Act或 Fragment 绑定的视图文件！！！"
        }

        val mActivityLayoutName = stringParameter {
            name = "Activity Layout Name"
            default = "activity_main"
            visible = { mIsGenerateActivityLayout.value }
            constraints = listOf(Constraint.LAYOUT, Constraint.NONEMPTY)
            suggest = { "activity${getLayoutName(mPageName.value)}" }
        }

        val mActivityPackageName = stringParameter {
            name = "Root Package Name"
            constraints = listOf(Constraint.PACKAGE, Constraint.NONEMPTY)
            default = "com.zhangteng.baselibrary"
            visible = { !isNewModule }
            help = "Activity 包名"
        }

        thumb { File("template_empty_activity.png") }

        widgets(
            TextFieldWidget(mPageName),
            TextFieldWidget(mActivityLayoutName),
            CheckBoxWidget(mIsGenerateActivityLayout),
            PackageNameWidget(mActivityPackageName),
        )

        recipe = { data: TemplateData ->
            mvpActivityRecipe(
                data as ModuleTemplateData,
                mPageName.value,
                mActivityLayoutName.value,
                mIsGenerateActivityLayout.value,
                mActivityPackageName.value,
            )
        }

    }