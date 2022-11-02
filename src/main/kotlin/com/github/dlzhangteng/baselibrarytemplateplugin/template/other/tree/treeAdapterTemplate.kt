package com.github.dlzhangteng.baselibrarytemplateplugin.template.other.tree

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import com.github.dlzhangteng.baselibrarytemplateplugin.template.getLayoutName
import java.io.File
import java.util.*

val treeAdapterTemplate
    get() = template {
//        revision = 1
        name = "ZTTreeAdapter"
        description = "一键创建 TreeAdapter "
        minApi = MIN_API
        category = Category.Other
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.MenuEntry
        )

        val mPageName = stringParameter {
            name = "Create Page Name"
            constraints = listOf(Constraint.UNIQUE, Constraint.NONEMPTY)
            default = "Main"
            help = "需要生成页面的名字,不需要再写 名字后缀:如Activity、Fragment,会自动生成,以及对应文件名后缀"
        }

        val mIsGenerateAdapterLayout = booleanParameter {
            name = "Generate TreeAdapter Layout"
            default = true
            help = "默认勾选,如果使用已存在布局 则无需勾选,若不勾选,创建后记得修改Act或 Fragment 绑定的视图文件！！！"
        }

        val mAdapterLayoutName = stringParameter {
            name = "TreeAdapter Layout Name"
            default = "item_main"
            visible = { mIsGenerateAdapterLayout.value }
            constraints = listOf(Constraint.LAYOUT, Constraint.NONEMPTY)
            suggest = { "item${getLayoutName(mPageName.value)}" }
        }

        val mAdapterPackageName = stringParameter {
            name = "Root Package Name"
            constraints = listOf(Constraint.PACKAGE, Constraint.NONEMPTY)
            default = "com.zhangteng.baselibrary"
            visible = { !isNewModule }
            help = "TreeAdapter 包名"
        }

        val mBeanName = stringParameter {
            name = "TreeAdapter Bean Name"
            constraints = listOf(Constraint.UNIQUE, Constraint.NONEMPTY)
            default = "Bean"
            suggest = { "${mPageName.value}Bean" }
            help = "TreeAdapter 的数据类"
        }

        val mAdapterName = stringParameter {
            name = "TreeAdapter Name"
            constraints = listOf(Constraint.UNIQUE, Constraint.NONEMPTY)
            default = "Adapter"
            suggest = { "${mPageName.value}Adapter" }
            help = "TreeAdapter 的Adapter"
        }

        thumb { File("template_empty_activity.png") }

        widgets(
            TextFieldWidget(mPageName),
            TextFieldWidget(mAdapterLayoutName),
            CheckBoxWidget(mIsGenerateAdapterLayout),
            TextFieldWidget(mBeanName),
            TextFieldWidget(mAdapterName),
            PackageNameWidget(mAdapterPackageName),
        )

        recipe = { data: TemplateData ->
            treeAdapterRecipe(
                data as ModuleTemplateData,
                mAdapterLayoutName.value,
                mIsGenerateAdapterLayout.value,
                mBeanName.value,
                mAdapterName.value,
                mAdapterPackageName.value,
            )
        }

    }
