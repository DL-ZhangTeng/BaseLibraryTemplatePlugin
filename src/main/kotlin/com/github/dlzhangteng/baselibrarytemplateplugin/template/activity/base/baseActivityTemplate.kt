package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.base

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
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvi.mviActivityRecipe
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvidb.mviDbActivityRecipe
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvivb.mviVbActivityRecipe
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvp.mvpActivityRecipe
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvpvb.mvpVbActivityRecipe
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvm.mvvmActivityRecipe
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvmdb.mvvmDbActivityRecipe
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvmvb.mvvmVbActivityRecipe
import com.github.dlzhangteng.baselibrarytemplateplugin.template.getLayoutName
import java.io.File

val baseActivityTemplate
    get() = template {
//        revision = 1
        name = "ZTBaseActivity"
        description = "一键创建 BaseActivity "
        minApi = MIN_API
        category = Category.Activity
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.ActivityGallery,
            WizardUiContext.MenuEntry,
            WizardUiContext.NewProject,
            WizardUiContext.NewModule
        )

        val mActivityPackageName = stringParameter {
            name = "Root Package Name"
            constraints = listOf(Constraint.PACKAGE, Constraint.NONEMPTY)
            default = "com.zhangteng.baselibrary"
            visible = { !isNewModule }
            help = "Activity 包名"
        }

        val mPageName = stringParameter {
            name = "Create Page Name"
            constraints = listOf(Constraint.UNIQUE, Constraint.NONEMPTY)
            default = "Main"
            help =
                "需要生成页面的名字,不需要再写 名字后缀:如Activity、Fragment,会自动生成,以及对应文件名后缀"
        }

        val mIsGenerateActivityLayout = booleanParameter {
            name = "Generate Activity Layout"
            default = true
            help =
                "默认勾选,如果使用已存在布局 则无需勾选,若不勾选,创建后记得修改Act或 Fragment 绑定的视图文件！！！"
        }

        val mActivityLayoutName = stringParameter {
            name = "Activity Layout Name"
            default = "activity_main"
            visible = { mIsGenerateActivityLayout.value }
            constraints = listOf(Constraint.LAYOUT, Constraint.NONEMPTY)
            suggest = { "activity${getLayoutName(mPageName.value)}" }
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

        thumb { File("template_empty_activity.png") }

        widgets(
            TextFieldWidget(mPageName),
            TextFieldWidget(mActivityLayoutName),
            CheckBoxWidget(mIsGenerateActivityLayout),
            PackageNameWidget(mActivityPackageName),
            EnumWidget(mArchitectureEnum),
            EnumWidget(mMvpBindingEnum),
            EnumWidget(mMvvmBindingEnum),
            EnumWidget(mDependencyInjectionEnum),
        )

        recipe = { data: TemplateData ->
            run {
                when (mArchitectureEnum.value) {
                    ArchitectureEnum.NONE -> {
                        baseActivityRecipe(
                            data as ModuleTemplateData,
                            mPageName.value,
                            mActivityLayoutName.value,
                            mIsGenerateActivityLayout.value,
                            mActivityPackageName.value,
                            mDependencyInjectionEnum.value,
                        )
                    }

                    ArchitectureEnum.MVP -> {
                        when (mMvpBindingEnum.value) {
                            MvpBindingEnum.NONE -> {
                                mvpActivityRecipe(
                                    data as ModuleTemplateData,
                                    mPageName.value,
                                    mActivityLayoutName.value,
                                    mIsGenerateActivityLayout.value,
                                    mActivityPackageName.value,
                                    mDependencyInjectionEnum.value,
                                )
                            }

                            MvpBindingEnum.VIEW_BINDING -> {
                                mvpVbActivityRecipe(
                                    data as ModuleTemplateData,
                                    mPageName.value,
                                    mActivityLayoutName.value,
                                    mIsGenerateActivityLayout.value,
                                    mActivityPackageName.value,
                                    mDependencyInjectionEnum.value,
                                )
                            }
                        }
                    }

                    ArchitectureEnum.MVVM -> {
                        when (mMvvmBindingEnum.value) {
                            MvvmBindingEnum.NONE -> {
                                mvvmActivityRecipe(
                                    data as ModuleTemplateData,
                                    mPageName.value,
                                    mActivityLayoutName.value,
                                    mIsGenerateActivityLayout.value,
                                    mActivityPackageName.value,
                                    mDependencyInjectionEnum.value,
                                )
                            }

                            MvvmBindingEnum.VIEW_BINDING -> {
                                mvvmVbActivityRecipe(
                                    data as ModuleTemplateData,
                                    mPageName.value,
                                    mActivityLayoutName.value,
                                    mIsGenerateActivityLayout.value,
                                    mActivityPackageName.value,
                                    mDependencyInjectionEnum.value,
                                )
                            }

                            MvvmBindingEnum.DATA_BINDING -> {
                                mvvmDbActivityRecipe(
                                    data as ModuleTemplateData,
                                    mPageName.value,
                                    mActivityLayoutName.value,
                                    mIsGenerateActivityLayout.value,
                                    mActivityPackageName.value,
                                    mDependencyInjectionEnum.value,
                                )
                            }
                        }
                    }

                    ArchitectureEnum.MVI -> {
                        when (mMvvmBindingEnum.value) {
                            MvvmBindingEnum.NONE -> {
                                mviActivityRecipe(
                                    data as ModuleTemplateData,
                                    mPageName.value,
                                    mActivityLayoutName.value,
                                    mIsGenerateActivityLayout.value,
                                    mActivityPackageName.value,
                                    mDependencyInjectionEnum.value,
                                )
                            }

                            MvvmBindingEnum.VIEW_BINDING -> {
                                mviVbActivityRecipe(
                                    data as ModuleTemplateData,
                                    mPageName.value,
                                    mActivityLayoutName.value,
                                    mIsGenerateActivityLayout.value,
                                    mActivityPackageName.value,
                                    mDependencyInjectionEnum.value,
                                )
                            }

                            MvvmBindingEnum.DATA_BINDING -> {
                                mviDbActivityRecipe(
                                    data as ModuleTemplateData,
                                    mPageName.value,
                                    mActivityLayoutName.value,
                                    mIsGenerateActivityLayout.value,
                                    mActivityPackageName.value,
                                    mDependencyInjectionEnum.value,
                                )
                            }
                        }
                    }
                }
            }
        }

    }