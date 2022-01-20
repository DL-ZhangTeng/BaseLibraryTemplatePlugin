package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.list

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import java.io.File
import java.util.*

val listFragmentTemplate
    get() = template {
//        revision = 1
        name = "ZTBaseListFragment"
        description = "一键创建 BaseListFragment "
        minApi = MIN_API
        category = Category.Fragment
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.FragmentGallery,
            WizardUiContext.MenuEntry
        )

        val mPageName = stringParameter {
            name = "Create Page Name"
            constraints = listOf(Constraint.UNIQUE, Constraint.NONEMPTY)
            default = "Main"
            help = "需要生成页面的名字,不需要再写 名字后缀:如Activity、Fragment,会自动生成,以及对应文件名后缀"
        }

        val mIsGenerateFragmentLayout = booleanParameter {
            name = "Generate Fragment Layout"
            default = true
            help = "默认勾选,如果使用已存在布局 则无需勾选,若不勾选,创建后记得修改Act或 Fragment 绑定的视图文件！！！"
        }

        val mFragmentLayoutName = stringParameter {
            name = "Fragment Layout Name"
            default = "fragment_main"
            visible = { mIsGenerateFragmentLayout.value }
            constraints = listOf(Constraint.LAYOUT, Constraint.NONEMPTY)
            suggest = { fragmentToLayout(mPageName.value) }
        }

        val mFragmentPackageName = stringParameter {
            name = "Root Package Name"
            constraints = listOf(Constraint.PACKAGE, Constraint.NONEMPTY)
            default = "com.zhangteng.baselibrary"
            visible = { !isNewModule }
            help = "Fragment 包名"
        }

        val mBeanName = stringParameter {
            name = "Fragment Bean Name"
            constraints = listOf(Constraint.UNIQUE, Constraint.NONEMPTY)
            default = "Bean"
            suggest = { "${mPageName.value}Bean" }
            help = "ListFragment 的数据类"
        }

        val mAdapterName = stringParameter {
            name = "Fragment Adapter Name"
            constraints = listOf(Constraint.UNIQUE, Constraint.NONEMPTY)
            default = "Adapter"
            suggest = { "${mPageName.value}Adapter" }
            help = "ListFragment 的Adapter"
        }

        thumb { File("template_list_fragment.png") }

        widgets(
            TextFieldWidget(mPageName),
            TextFieldWidget(mFragmentLayoutName),
            CheckBoxWidget(mIsGenerateFragmentLayout),
            TextFieldWidget(mBeanName),
            TextFieldWidget(mAdapterName),
            PackageNameWidget(mFragmentPackageName),
        )

        recipe = { data: TemplateData ->
            listFragmentRecipe(
                data as ModuleTemplateData,
                mPageName.value,
                mFragmentLayoutName.value,
                mIsGenerateFragmentLayout.value,
                mBeanName.value,
                mAdapterName.value,
                mFragmentPackageName.value,
            )
        }

    }

/**
 * 获取layoutName
 */
fun getLayoutName(pageName: String): String {
    val stringBuilder: StringBuilder = StringBuilder()
    val fragmentChildNames: ArrayList<String> = splitByUpperCase(pageName)
    fragmentChildNames.forEach {
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
