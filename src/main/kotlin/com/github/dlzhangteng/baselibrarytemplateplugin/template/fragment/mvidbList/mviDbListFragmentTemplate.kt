package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvidbList

import com.android.tools.idea.wizard.template.*
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import com.github.dlzhangteng.baselibrarytemplateplugin.template.getLayoutName
import java.io.File

val mviDbListFragmentTemplate
    get() = template {
//        revision = 1
        name = "ZTBaseListMviDbFragment"
        description = "一键创建 BaseListMviDbFragment "
        minApi = MIN_API
        category = Category.Fragment
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.FragmentGallery,
            WizardUiContext.MenuEntry
        )

        val mFragmentPackageName = stringParameter {
            name = "Root Package Name"
            constraints = listOf(Constraint.PACKAGE, Constraint.NONEMPTY)
            default = "com.zhangteng.baselibrary"
            visible = { !isNewModule }
            help = "Fragment 包名"
        }

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
            suggest = { "fragment${getLayoutName(mPageName.value)}" }
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

        thumb { File("template_blank_fragment.png") }

        widgets(
            TextFieldWidget(mPageName),
            TextFieldWidget(mFragmentLayoutName),
            CheckBoxWidget(mIsGenerateFragmentLayout),
            TextFieldWidget(mBeanName),
            TextFieldWidget(mAdapterName),
            PackageNameWidget(mFragmentPackageName),
        )

        recipe = { data: TemplateData ->
            mviDbListFragmentRecipe(
                data as ModuleTemplateData,
                mPageName.value,
                mFragmentLayoutName.value,
                mIsGenerateFragmentLayout.value,
                mBeanName.value,
                mAdapterName.value,
                mFragmentPackageName.value
            )
        }

    }
