package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.list

import com.android.tools.idea.wizard.template.Category
import com.android.tools.idea.wizard.template.CheckBoxWidget
import com.android.tools.idea.wizard.template.Constraint
import com.android.tools.idea.wizard.template.EnumWidget
import com.android.tools.idea.wizard.template.FormFactor
import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.PackageNameWidget
import com.android.tools.idea.wizard.template.TemplateData
import com.android.tools.idea.wizard.template.TextFieldWidget
import com.android.tools.idea.wizard.template.WizardUiContext
import com.android.tools.idea.wizard.template.booleanParameter
import com.android.tools.idea.wizard.template.enumParameter
import com.android.tools.idea.wizard.template.impl.activities.common.MIN_API
import com.android.tools.idea.wizard.template.stringParameter
import com.android.tools.idea.wizard.template.template
import com.github.dlzhangteng.baselibrarytemplateplugin.template.ArchitectureEnum
import com.github.dlzhangteng.baselibrarytemplateplugin.template.MvpBindingEnum
import com.github.dlzhangteng.baselibrarytemplateplugin.template.MvvmBindingEnum
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mviList.mviListFragmentRecipe
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvidbList.mviDbListFragmentRecipe
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvivbList.mviVbListFragmentRecipe
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvpList.mvpListFragmentRecipe
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvpvbList.mvpVbListFragmentRecipe
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvvmList.mvvmListFragmentRecipe
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvvmdbList.mvvmDbListFragmentRecipe
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvvmvbList.mvvmVbListFragmentRecipe
import com.github.dlzhangteng.baselibrarytemplateplugin.template.getLayoutName
import java.io.File

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
            help =
                "需要生成页面的名字,不需要再写 名字后缀:如Activity、Fragment,会自动生成,以及对应文件名后缀"
        }

        val mIsGenerateFragmentLayout = booleanParameter {
            name = "Generate Fragment Layout"
            default = true
            help =
                "默认勾选,如果使用已存在布局 则无需勾选,若不勾选,创建后记得修改Act或 Fragment 绑定的视图文件！！！"
        }

        val mFragmentLayoutName = stringParameter {
            name = "Fragment Layout Name"
            default = "fragment_main"
            visible = { mIsGenerateFragmentLayout.value }
            constraints = listOf(Constraint.LAYOUT, Constraint.NONEMPTY)
            suggest = { "fragment${getLayoutName(mPageName.value)}" }
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

        val mArchitectureEnum = enumParameter<ArchitectureEnum> {
            name = "Select Architecture"
            default = ArchitectureEnum.NONE
            help = "默认不使用架构,如果使用架构模板请选择MVP、 MVVM或MVI"
        }

        val mMvpBindingEnum = enumParameter<MvpBindingEnum> {
            name = "Select Binding"
            default = MvpBindingEnum.NONE
            visible = { mArchitectureEnum.value == ArchitectureEnum.MVP }
            help = "默认不使用Binding,如果使用页面绑定或者数据绑定请选择VIEW_BINDING或DATA_BINDING"
        }

        val mMvvmBindingEnum = enumParameter<MvvmBindingEnum> {
            name = "Select Binding"
            default = MvvmBindingEnum.NONE
            visible =
                { mArchitectureEnum.value == ArchitectureEnum.MVVM || mArchitectureEnum.value == ArchitectureEnum.MVI }
            help = "默认不使用Binding,如果使用页面绑定或者数据绑定请选择VIEW_BINDING或DATA_BINDING"
        }

        thumb { File("template_list_fragment.png") }

        widgets(
            TextFieldWidget(mPageName),
            TextFieldWidget(mFragmentLayoutName),
            CheckBoxWidget(mIsGenerateFragmentLayout),
            TextFieldWidget(mBeanName),
            TextFieldWidget(mAdapterName),
            PackageNameWidget(mFragmentPackageName),
            EnumWidget(mArchitectureEnum),
            EnumWidget(mMvpBindingEnum),
            EnumWidget(mMvvmBindingEnum)
        )

        recipe = { data: TemplateData ->
            run {
                when (mArchitectureEnum.value) {
                    ArchitectureEnum.NONE -> {
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

                    ArchitectureEnum.MVP -> {
                        when (mMvpBindingEnum.value) {
                            MvpBindingEnum.NONE -> {
                                mvpListFragmentRecipe(
                                    data as ModuleTemplateData,
                                    mPageName.value,
                                    mFragmentLayoutName.value,
                                    mIsGenerateFragmentLayout.value,
                                    mBeanName.value,
                                    mAdapterName.value,
                                    mFragmentPackageName.value,
                                )
                            }

                            MvpBindingEnum.VIEW_BINDING -> {
                                mvpVbListFragmentRecipe(
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
                    }

                    ArchitectureEnum.MVVM -> {
                        when (mMvvmBindingEnum.value) {
                            MvvmBindingEnum.NONE -> {
                                mvvmListFragmentRecipe(
                                    data as ModuleTemplateData,
                                    mPageName.value,
                                    mFragmentLayoutName.value,
                                    mIsGenerateFragmentLayout.value,
                                    mBeanName.value,
                                    mAdapterName.value,
                                    mFragmentPackageName.value
                                )
                            }

                            MvvmBindingEnum.VIEW_BINDING -> {
                                mvvmVbListFragmentRecipe(
                                    data as ModuleTemplateData,
                                    mPageName.value,
                                    mFragmentLayoutName.value,
                                    mIsGenerateFragmentLayout.value,
                                    mBeanName.value,
                                    mAdapterName.value,
                                    mFragmentPackageName.value
                                )
                            }

                            MvvmBindingEnum.DATA_BINDING -> {
                                mvvmDbListFragmentRecipe(
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
                    }

                    ArchitectureEnum.MVI -> {
                        when (mMvvmBindingEnum.value) {
                            MvvmBindingEnum.NONE -> {
                                mviListFragmentRecipe(
                                    data as ModuleTemplateData,
                                    mPageName.value,
                                    mFragmentLayoutName.value,
                                    mIsGenerateFragmentLayout.value,
                                    mBeanName.value,
                                    mAdapterName.value,
                                    mFragmentPackageName.value
                                )
                            }

                            MvvmBindingEnum.VIEW_BINDING -> {
                                mviVbListFragmentRecipe(
                                    data as ModuleTemplateData,
                                    mPageName.value,
                                    mFragmentLayoutName.value,
                                    mIsGenerateFragmentLayout.value,
                                    mBeanName.value,
                                    mAdapterName.value,
                                    mFragmentPackageName.value
                                )
                            }

                            MvvmBindingEnum.DATA_BINDING -> {
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
                    }
                }
            }
        }

    }
