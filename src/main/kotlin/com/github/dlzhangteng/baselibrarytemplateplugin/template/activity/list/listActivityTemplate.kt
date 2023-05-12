package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.list

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
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mviList.mviListActivityRecipe
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvidbList.mviDbListActivityRecipe
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvivbList.mviVbListActivityRecipe
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvpList.mvpListActivityRecipe
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvpvbList.mvpVbListActivityRecipe
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvmList.mvvmListActivityRecipe
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvmdbList.mvvmDbListActivityRecipe
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvmvbList.mvvmVbListActivityRecipe
import com.github.dlzhangteng.baselibrarytemplateplugin.template.getLayoutName
import java.io.File

val listActivityTemplate
    get() = template {
//        revision = 1
        name = "ZTBaseListActivity"
        description = "一键创建 BaseListActivity "
        minApi = MIN_API
        category = Category.Activity
        formFactor = FormFactor.Mobile
        screens = listOf(
            WizardUiContext.ActivityGallery,
            WizardUiContext.MenuEntry,
            WizardUiContext.NewProject,
            WizardUiContext.NewModule
        )

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

        val mActivityPackageName = stringParameter {
            name = "Root Package Name"
            constraints = listOf(Constraint.PACKAGE, Constraint.NONEMPTY)
            default = "com.zhangteng.baselibrary"
            visible = { !isNewModule }
            help = "Activity 包名"
        }

        val mBeanName = stringParameter {
            name = "Activity Bean Name"
            constraints = listOf(Constraint.UNIQUE, Constraint.NONEMPTY)
            default = "Bean"
            suggest = { "${mPageName.value}Bean" }
            help = "ListActivity 的数据类"
        }

        val mAdapterName = stringParameter {
            name = "Activity Adapter Name"
            constraints = listOf(Constraint.UNIQUE, Constraint.NONEMPTY)
            default = "Adapter"
            suggest = { "${mPageName.value}Adapter" }
            help = "ListActivity 的Adapter"
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
            TextFieldWidget(mBeanName),
            TextFieldWidget(mAdapterName),
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
                        listActivityRecipe(
                            data as ModuleTemplateData,
                            mPageName.value,
                            mActivityLayoutName.value,
                            mIsGenerateActivityLayout.value,
                            mBeanName.value,
                            mAdapterName.value,
                            mActivityPackageName.value,
                            mDependencyInjectionEnum.value,
                        )
                    }

                    ArchitectureEnum.MVP -> {
                        when (mMvpBindingEnum.value) {
                            MvpBindingEnum.NONE -> {
                                mvpListActivityRecipe(
                                    data as ModuleTemplateData,
                                    mPageName.value,
                                    mActivityLayoutName.value,
                                    mIsGenerateActivityLayout.value,
                                    mBeanName.value,
                                    mAdapterName.value,
                                    mActivityPackageName.value,
                                    mDependencyInjectionEnum.value,
                                )
                            }

                            MvpBindingEnum.VIEW_BINDING -> {
                                mvpVbListActivityRecipe(
                                    data as ModuleTemplateData,
                                    mPageName.value,
                                    mActivityLayoutName.value,
                                    mIsGenerateActivityLayout.value,
                                    mBeanName.value,
                                    mAdapterName.value,
                                    mActivityPackageName.value,
                                    mDependencyInjectionEnum.value,
                                )
                            }
                        }
                    }

                    ArchitectureEnum.MVVM -> {
                        when (mMvvmBindingEnum.value) {
                            MvvmBindingEnum.NONE -> {
                                mvvmListActivityRecipe(
                                    data as ModuleTemplateData,
                                    mPageName.value,
                                    mActivityLayoutName.value,
                                    mIsGenerateActivityLayout.value,
                                    mBeanName.value,
                                    mAdapterName.value,
                                    mActivityPackageName.value,
                                    mDependencyInjectionEnum.value,
                                )
                            }

                            MvvmBindingEnum.VIEW_BINDING -> {
                                mvvmVbListActivityRecipe(
                                    data as ModuleTemplateData,
                                    mPageName.value,
                                    mActivityLayoutName.value,
                                    mIsGenerateActivityLayout.value,
                                    mBeanName.value,
                                    mAdapterName.value,
                                    mActivityPackageName.value,
                                    mDependencyInjectionEnum.value,
                                )
                            }

                            MvvmBindingEnum.DATA_BINDING -> {
                                mvvmDbListActivityRecipe(
                                    data as ModuleTemplateData,
                                    mPageName.value,
                                    mActivityLayoutName.value,
                                    mIsGenerateActivityLayout.value,
                                    mBeanName.value,
                                    mAdapterName.value,
                                    mActivityPackageName.value,
                                    mDependencyInjectionEnum.value,
                                )
                            }
                        }
                    }

                    ArchitectureEnum.MVI -> {
                        when (mMvvmBindingEnum.value) {
                            MvvmBindingEnum.NONE -> {
                                mviListActivityRecipe(
                                    data as ModuleTemplateData,
                                    mPageName.value,
                                    mActivityLayoutName.value,
                                    mIsGenerateActivityLayout.value,
                                    mBeanName.value,
                                    mAdapterName.value,
                                    mActivityPackageName.value,
                                    mDependencyInjectionEnum.value,
                                )
                            }

                            MvvmBindingEnum.VIEW_BINDING -> {
                                mviVbListActivityRecipe(
                                    data as ModuleTemplateData,
                                    mPageName.value,
                                    mActivityLayoutName.value,
                                    mIsGenerateActivityLayout.value,
                                    mBeanName.value,
                                    mAdapterName.value,
                                    mActivityPackageName.value,
                                    mDependencyInjectionEnum.value,
                                )
                            }

                            MvvmBindingEnum.DATA_BINDING -> {
                                mviDbListActivityRecipe(
                                    data as ModuleTemplateData,
                                    mPageName.value,
                                    mActivityLayoutName.value,
                                    mIsGenerateActivityLayout.value,
                                    mBeanName.value,
                                    mAdapterName.value,
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