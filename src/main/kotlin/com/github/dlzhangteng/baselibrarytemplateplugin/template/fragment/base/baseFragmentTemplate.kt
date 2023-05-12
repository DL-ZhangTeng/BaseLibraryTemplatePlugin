package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.base

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
import com.github.dlzhangteng.baselibrarytemplateplugin.template.DependencyInjectionEnum
import com.github.dlzhangteng.baselibrarytemplateplugin.template.MvpBindingEnum
import com.github.dlzhangteng.baselibrarytemplateplugin.template.MvvmBindingEnum
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvi.mviFragmentRecipe
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvidb.mviDbFragmentRecipe
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvivb.mviVbFragmentRecipe
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvp.mvpFragmentRecipe
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvpvb.mvpVbFragmentRecipe
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvvm.mvvmFragmentRecipe
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvvmdb.mvvmDbFragmentRecipe
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvvmvb.mvvmVbFragmentRecipe
import com.github.dlzhangteng.baselibrarytemplateplugin.template.getLayoutName
import java.io.File

val baseFragmentTemplate
    get() = template {
//        revision = 1
        name = "ZTBaseFragment"
        description = "一键创建 BaseFragment "
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

        val mDependencyInjectionEnum = enumParameter<DependencyInjectionEnum> {
            name = "Select Dependency Injection"
            default = DependencyInjectionEnum.NONE
            visible = { mArchitectureEnum.value != ArchitectureEnum.NONE }
            help = "默认不使用依赖注入,如果使用依赖注入请选择HILT（暂不支持DAGGER2）"
        }

        thumb { File("template_blank_fragment.png") }

        widgets(
            TextFieldWidget(mPageName),
            TextFieldWidget(mFragmentLayoutName),
            CheckBoxWidget(mIsGenerateFragmentLayout),
            PackageNameWidget(mFragmentPackageName),
            EnumWidget(mArchitectureEnum),
            EnumWidget(mMvpBindingEnum),
            EnumWidget(mMvvmBindingEnum),
            EnumWidget(mDependencyInjectionEnum),
        )

        recipe = { data: TemplateData ->
            run {
                when (mArchitectureEnum.value) {
                    ArchitectureEnum.NONE -> {
                        baseFragmentRecipe(
                            data as ModuleTemplateData,
                            mPageName.value,
                            mFragmentLayoutName.value,
                            mIsGenerateFragmentLayout.value,
                            mFragmentPackageName.value,
                            mDependencyInjectionEnum.value,
                        )
                    }

                    ArchitectureEnum.MVP -> {
                        when (mMvpBindingEnum.value) {
                            MvpBindingEnum.NONE -> {
                                mvpFragmentRecipe(
                                    data as ModuleTemplateData,
                                    mPageName.value,
                                    mFragmentLayoutName.value,
                                    mIsGenerateFragmentLayout.value,
                                    mFragmentPackageName.value,
                                    mDependencyInjectionEnum.value,
                                )
                            }

                            MvpBindingEnum.VIEW_BINDING -> {
                                mvpVbFragmentRecipe(
                                    data as ModuleTemplateData,
                                    mPageName.value,
                                    mFragmentLayoutName.value,
                                    mIsGenerateFragmentLayout.value,
                                    mFragmentPackageName.value,
                                    mDependencyInjectionEnum.value,
                                )
                            }
                        }
                    }

                    ArchitectureEnum.MVVM -> {
                        when (mMvvmBindingEnum.value) {
                            MvvmBindingEnum.NONE -> {
                                mvvmFragmentRecipe(
                                    data as ModuleTemplateData,
                                    mPageName.value,
                                    mFragmentLayoutName.value,
                                    mIsGenerateFragmentLayout.value,
                                    mFragmentPackageName.value,
                                    mDependencyInjectionEnum.value,
                                )
                            }

                            MvvmBindingEnum.VIEW_BINDING -> {
                                mvvmVbFragmentRecipe(
                                    data as ModuleTemplateData,
                                    mPageName.value,
                                    mFragmentLayoutName.value,
                                    mIsGenerateFragmentLayout.value,
                                    mFragmentPackageName.value,
                                    mDependencyInjectionEnum.value,
                                )
                            }

                            MvvmBindingEnum.DATA_BINDING -> {
                                mvvmDbFragmentRecipe(
                                    data as ModuleTemplateData,
                                    mPageName.value,
                                    mFragmentLayoutName.value,
                                    mIsGenerateFragmentLayout.value,
                                    mFragmentPackageName.value,
                                    mDependencyInjectionEnum.value,
                                )
                            }
                        }
                    }

                    ArchitectureEnum.MVI -> {
                        when (mMvvmBindingEnum.value) {
                            MvvmBindingEnum.NONE -> {
                                mviFragmentRecipe(
                                    data as ModuleTemplateData,
                                    mPageName.value,
                                    mFragmentLayoutName.value,
                                    mIsGenerateFragmentLayout.value,
                                    mFragmentPackageName.value,
                                    mDependencyInjectionEnum.value,
                                )
                            }

                            MvvmBindingEnum.VIEW_BINDING -> {
                                mviVbFragmentRecipe(
                                    data as ModuleTemplateData,
                                    mPageName.value,
                                    mFragmentLayoutName.value,
                                    mIsGenerateFragmentLayout.value,
                                    mFragmentPackageName.value,
                                    mDependencyInjectionEnum.value,
                                )
                            }

                            MvvmBindingEnum.DATA_BINDING -> {
                                mviDbFragmentRecipe(
                                    data as ModuleTemplateData,
                                    mPageName.value,
                                    mFragmentLayoutName.value,
                                    mIsGenerateFragmentLayout.value,
                                    mFragmentPackageName.value,
                                    mDependencyInjectionEnum.value,
                                )
                            }
                        }
                    }
                }
            }
        }

    }