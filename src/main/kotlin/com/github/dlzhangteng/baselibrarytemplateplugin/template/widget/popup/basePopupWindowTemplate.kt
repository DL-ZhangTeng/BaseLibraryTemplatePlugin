package com.github.dlzhangteng.baselibrarytemplateplugin.template.widget.popup

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import com.github.dlzhangteng.baselibrarytemplateplugin.template.getLayoutName
import java.io.File
import java.util.*

val basePopupWindowTemplate
    get() = template {
//        revision = 1
        name = "ZTBasePopupWindow"
        description = "一键创建 BasePopupWindow "
        minApi = MIN_API
        category = Category.Widget
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

        val mIsGeneratePopupWindowLayout = booleanParameter {
            name = "Generate BasePopupWindow Layout"
            default = true
            help = "默认勾选,如果使用已存在布局 则无需勾选,若不勾选,创建后记得修改Act或 Fragment 绑定的视图文件！！！"
        }

        val mPopupWindowLayoutName = stringParameter {
            name = "BasePopupWindow Layout Name"
            default = "item_main"
            visible = { mIsGeneratePopupWindowLayout.value }
            constraints = listOf(Constraint.LAYOUT, Constraint.NONEMPTY)
            suggest = { "layout_popup${getLayoutName(mPageName.value)}" }
        }

        val mPopupWindowPackageName = stringParameter {
            name = "Root Package Name"
            constraints = listOf(Constraint.PACKAGE, Constraint.NONEMPTY)
            default = "com.zhangteng.baselibrary"
            visible = { !isNewModule }
            help = "BasePopupWindow 包名"
        }

        val mPopupWindowName = stringParameter {
            name = "BasePopupWindow Name"
            constraints = listOf(Constraint.UNIQUE, Constraint.NONEMPTY)
            default = "PopupWindow"
            suggest = { "${mPageName.value}PopupWindow" }
            help = "BasePopupWindow 的PopupWindow"
        }

        thumb { File("template_empty_activity.png") }

        widgets(
            TextFieldWidget(mPageName),
            TextFieldWidget(mPopupWindowName),
            TextFieldWidget(mPopupWindowLayoutName),
            CheckBoxWidget(mIsGeneratePopupWindowLayout),
            PackageNameWidget(mPopupWindowPackageName),
        )

        recipe = { data: TemplateData ->
            basePopupWindowRecipe(
                data as ModuleTemplateData,
                mPopupWindowLayoutName.value,
                mIsGeneratePopupWindowLayout.value,
                mPopupWindowName.value,
                mPopupWindowPackageName.value,
            )
        }

    }
