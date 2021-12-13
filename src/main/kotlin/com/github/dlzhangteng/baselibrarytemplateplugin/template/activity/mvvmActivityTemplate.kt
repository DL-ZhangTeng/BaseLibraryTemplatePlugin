package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API

val mvvmActivityTemplate
    get() = template {
//        revision = 1
        name = "BaseActivity Template"
        description = "一键创建 BaseActivity "
        minApi = MIN_API
        minApi = MIN_API
        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.ActivityGallery,
            WizardUiContext.MenuEntry,
            WizardUiContext.NewProject,
            WizardUiContext.NewModule
        )


        val mRootPackageName = stringParameter {
            name = "Root Package Name"
            constraints = listOf(Constraint.PACKAGE)
            default = "com.zhangteng.baselibrary"
            visible = { !isNewModule }
            help = "此 PluginTemplate 是针对 `Android` 项目编写,默认包名为项目的包名,可根据自己需要填写"
        }

        val mPageName = stringParameter {
            name = "Create Page Name"
            constraints = listOf(Constraint.UNIQUE, Constraint.NONEMPTY)
            default = "Main"
            help = "需要生成页面的名字,不需要再写 名字后缀:如Activity、Fragment,会自动生成,以及对应文件名后缀"
        }


        val mIsActivity = booleanParameter {
            name = "Generate Activity"
            default = true
            visible = { true }
            help = "是否需要创建 Activity ? 不勾选则不生成"
        }


        val mActivityLayoutName = stringParameter {
            name = "Activity Layout Name"
            default = "activity_main"
            constraints = listOf(Constraint.LAYOUT, Constraint.NONEMPTY)
            suggest = { activityToLayout(mPageName.value.toLowerCase()) }
            visible = { mIsActivity.value }
        }

        val mIsGenerateActivityLayout = booleanParameter {
            name = "Generate Activity Layout"
            default = true
            visible = { mIsActivity.value }
            help = "默认勾选,如果使用已存在布局 则无需勾选,若不勾选,创建后记得修改Act或 Fragment 绑定的视图文件！！！"
        }

        val mActivityPackageName = stringParameter {
            name = "Activity Package Name"
            constraints = listOf(Constraint.NONEMPTY)
            default = "ui"
            visible = { mIsActivity.value }
            help = "Activity 将被输出到此包下,请认真核实此包名是否是你需要输出的目标包名 (基于 Root Package Name )"
        }

        widgets(
            PackageNameWidget(mRootPackageName),
            TextFieldWidget(mPageName),
            CheckBoxWidget(mIsActivity),
            TextFieldWidget(mActivityLayoutName),
            CheckBoxWidget(mIsGenerateActivityLayout),
            TextFieldWidget(mActivityPackageName),
        )

        recipe = { data: TemplateData ->
            mvvmActivityRecipe(
                data as ModuleTemplateData,
                mRootPackageName.value,
                mPageName.value,
                mIsActivity.value,
                mActivityLayoutName.value,
                mIsGenerateActivityLayout.value,
                mActivityPackageName.value
            )
        }

    }
