package com.github.dlzhangteng.baselibrarytemplateplugin.template.other.header

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import java.io.File
import java.util.*

val headerOrFooterAdapterTemplate
    get() = template {
//        revision = 1
        name = "ZTHeaderOrFooterAdapter"
        description = "一键创建 HeaderOrFooterAdapter "
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

        val mAdapterPackageName = stringParameter {
            name = "Root Package Name"
            constraints = listOf(Constraint.PACKAGE, Constraint.NONEMPTY)
            default = "com.zhangteng.baselibrary"
            visible = { !isNewModule }
            help = "HeaderOrFooterAdapter 包名"
        }

        val mBeanName = stringParameter {
            name = "HeaderOrFooterAdapter Bean Name"
            constraints = listOf(Constraint.UNIQUE, Constraint.NONEMPTY)
            default = "Bean"
            suggest = { "${mPageName.value}Bean" }
            help = "HeaderOrFooterAdapter 的数据类"
        }

        val headerOrFooterAdapter = stringParameter {
            name = "HeaderOrFooterAdapter Name"
            constraints = listOf(Constraint.UNIQUE, Constraint.NONEMPTY)
            default = "Adapter"
            suggest = { "${mPageName.value}Adapter" }
            help = "HeaderOrFooterAdapter 的Adapter"
        }

        thumb { File("template_empty_activity.png") }

        widgets(
            TextFieldWidget(mPageName),
            TextFieldWidget(mBeanName),
            TextFieldWidget(headerOrFooterAdapter),
            PackageNameWidget(mAdapterPackageName),
        )

        recipe = { data: TemplateData ->
            headerOrFooterAdapterRecipe(
                data as ModuleTemplateData,
                mBeanName.value,
                headerOrFooterAdapter.value,
                mAdapterPackageName.value,
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
