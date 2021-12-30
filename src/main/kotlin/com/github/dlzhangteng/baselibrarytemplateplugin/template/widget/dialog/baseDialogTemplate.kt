package com.github.dlzhangteng.baselibrarytemplateplugin.template.widget.dialog

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import java.io.File
import java.util.*

val baseDialogTemplate
    get() = template {
//        revision = 1
        name = "ZTBaseDialog"
        description = "一键创建 BaseDialog "
        minApi = MIN_API
        category = Category.Widget
        formFactor = FormFactor.Mobile
        screens = listOf(
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

        val mIsGenerateDialogLayout = booleanParameter {
            name = "Generate BaseDialog Layout"
            default = true
            help = "默认勾选,如果使用已存在布局 则无需勾选,若不勾选,创建后记得修改Act或 Fragment 绑定的视图文件！！！"
        }

        val mDialogLayoutName = stringParameter {
            name = "BaseDialog Layout Name"
            default = "item_main"
            visible = { mIsGenerateDialogLayout.value }
            constraints = listOf(Constraint.LAYOUT, Constraint.NONEMPTY)
            suggest = { "layout_dialog${getLayoutName(mPageName.value)}" }
        }

        val mDialogPackageName = stringParameter {
            name = "Root Package Name"
            constraints = listOf(Constraint.PACKAGE, Constraint.NONEMPTY)
            default = "com.zhangteng.baselibrary"
            visible = { !isNewModule }
            help = "BaseDialog 包名"
        }

        val mDialogName = stringParameter {
            name = "BaseDialog Name"
            constraints = listOf(Constraint.UNIQUE, Constraint.NONEMPTY)
            default = "Dialog"
            suggest = { "${mPageName.value}Dialog" }
            help = "BaseDialog 的Dialog"
        }

        thumb { File("template_empty_activity.png") }

        widgets(
            TextFieldWidget(mPageName),
            TextFieldWidget(mDialogName),
            TextFieldWidget(mDialogLayoutName),
            CheckBoxWidget(mIsGenerateDialogLayout),
            PackageNameWidget(mDialogPackageName),
        )

        recipe = { data: TemplateData ->
            baseDialogRecipe(
                data as ModuleTemplateData,
                mDialogLayoutName.value,
                mIsGenerateDialogLayout.value,
                mDialogName.value,
                mDialogPackageName.value,
            )
        }

    }

/**
 * 获取layoutName
 */
fun getLayoutName(pageName: String): String {
    val stringBuilder: StringBuilder = StringBuilder()
    val activityChildNames: ArrayList<String> =
        splitByUpperCase(
            pageName
        )
    activityChildNames.forEach {
        stringBuilder.append("_").append(it.toLowerCase())
    }
    return stringBuilder.toString()
}

/**
 * 根据大写字母拆分数组
 */
fun splitByUpperCase(str: String): ArrayList<String> {
    val rs = ArrayList<String>()
    var index = 0
    val len = str.length
    for (i in 1 until len) {
        if (Character.isUpperCase(str[i])) {
            rs.add(str.substring(index, i))
            index = i
        }
    }
    rs.add(str.substring(index, len))
    return rs
}
