package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.list

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import java.util.*

val listActivityTemplate
    get() = template {
//        revision = 1
        name = "BaseListActivity"
        description = "一键创建 BaseListActivity "
        minApi = MIN_API
        minApi = MIN_API
        category = Category.Activity
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
            suggest = { activityToLayout(mPageName.value.toLowerCase()) }
        }

        val mActivityPackageName = stringParameter {
            name = "Activity Package Name"
            constraints = listOf(Constraint.NONEMPTY)
            default = "activity"
            help = "Activity 将被输出到此包下,请认真核实此包名是否是你需要输出的目标包名 (基于 Root Package Name )"
        }

        val mIsBean = booleanParameter {
            name = "Generate Bean Layout"
            default = true
            help = "默认勾选,如果使用已存在Bean 则无需勾选"
        }

        val mBeanName = stringParameter {
            name = "Activity Bean Name"
            constraints = listOf(Constraint.UNIQUE, Constraint.NONEMPTY)
            default = "Bean"
            help = "ListActivity 的数据类"
        }

        val mIsAdapter = booleanParameter {
            name = "Generate Adapter"
            default = true
            help = "默认勾选,如果使用已存在Adapter 则无需勾选"
        }

        val mAdapterName = stringParameter {
            name = "Activity Adapter Name"
            constraints = listOf(Constraint.UNIQUE, Constraint.NONEMPTY)
            default = "Adapter"
            help = "ListActivity 的Adapter"
        }

        widgets(
            PackageNameWidget(mRootPackageName),
            TextFieldWidget(mPageName),
            TextFieldWidget(mActivityLayoutName),
            CheckBoxWidget(mIsGenerateActivityLayout),
            TextFieldWidget(mActivityPackageName),
            TextFieldWidget(mBeanName),
            CheckBoxWidget(mIsBean),
            TextFieldWidget(mAdapterName),
            CheckBoxWidget(mIsAdapter),
        )

        recipe = { data: TemplateData ->
            listActivityRecipe(
                data as ModuleTemplateData,
                mRootPackageName.value,
                mPageName.value,
                mActivityLayoutName.value,
                mIsGenerateActivityLayout.value,
                mBeanName.value,
                mIsBean.value,
                mAdapterName.value,
                mIsAdapter.value,
                mActivityPackageName.value,
            )
        }

    }

/**
 * 获取layoutName
 */
fun getLayoutName(pageName: String): String {
    val stringBuilder: StringBuilder = StringBuilder()
    val activityChildNames: ArrayList<String> = splitByUpperCase(pageName)
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
