package com.github.dlzhangteng.baselibrarytemplateplugin.template.other.adapter

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import java.io.File
import java.util.*

val baseAdapterTemplate
    get() = template {
//        revision = 1
        name = "ZTBaseAdapter"
        description = "一键创建 BaseAdapter "
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
            name = "Generate BaseAdapter Layout"
            default = true
            help = "默认勾选,如果使用已存在布局 则无需勾选,若不勾选,创建后记得修改Act或 Fragment 绑定的视图文件！！！"
        }

        val mAdapterLayoutName = stringParameter {
            name = "BaseAdapter Layout Name"
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
            help = "BaseAdapter 包名"
        }

        val mBeanName = stringParameter {
            name = "BaseAdapter Bean Name"
            constraints = listOf(Constraint.UNIQUE, Constraint.NONEMPTY)
            default = "Bean"
            suggest = { "${mPageName.value}Bean" }
            help = "BaseAdapter 的数据类"
        }

        val mAdapterName = stringParameter {
            name = "BaseAdapter Name"
            constraints = listOf(Constraint.UNIQUE, Constraint.NONEMPTY)
            default = "Adapter"
            suggest = { "${mPageName.value}Adapter" }
            help = "BaseAdapter 的Adapter"
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
            baseAdapterRecipe(
                data as ModuleTemplateData,
                mAdapterLayoutName.value,
                mIsGenerateAdapterLayout.value,
                mBeanName.value,
                mAdapterName.value,
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
