package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvvmdbList

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.github.dlzhangteng.baselibrarytemplateplugin.template.DependencyInjectionEnum
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvvmdb.mvvmDbFragmentRepository
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvvmdb.mvvmDbFragmentViewModel
import com.github.dlzhangteng.baselibrarytemplateplugin.template.getLayoutName
import com.github.dlzhangteng.baselibrarytemplateplugin.template.layout.baseDbXml
import com.github.dlzhangteng.baselibrarytemplateplugin.template.layout.mvvmDbListFragmentXml
import com.github.dlzhangteng.baselibrarytemplateplugin.template.other.bindingadapter.bindingAdapter
import com.github.dlzhangteng.baselibrarytemplateplugin.template.other.bindingadapter.bindingBean
import java.io.File


fun RecipeExecutor.mvvmDbListFragmentRecipe(
    moduleTemplateData: ModuleTemplateData,
    mPageName: String,
    mFragmentLayoutName: String,
    mIsGenerateActivityLayout: Boolean,
    mBeanClass: String,
    mAdapterClass: String,
    mFragmentPackageName: String,
    mDependencyInjectionEnum: DependencyInjectionEnum,
) {
    val packageNameStr =
        if (moduleTemplateData.projectTemplateData.applicationPackage == null) ""
        else mFragmentPackageName
            .replace(moduleTemplateData.projectTemplateData.applicationPackage.toString(), "")
    val rootPath =
        if (packageNameStr.isNotEmpty()) moduleTemplateData.projectTemplateData.applicationPackage.toString()
        else mFragmentPackageName
    val mvvmDbFragment = mvvmDbListFragment(
        rootPath,
        packageNameStr,
        mPageName,
        mFragmentLayoutName,
        mBeanClass,
        mAdapterClass
    )
    val mvvmDbFragmentViewModel = mvvmDbFragmentViewModel(
        rootPath,
        mPageName
    )
    val mvvmDbFragmentRepository = mvvmDbFragmentRepository(
        rootPath,
        mPageName
    )
    val listBean = bindingBean(rootPath, mBeanClass)
    val listAdapter =
        bindingAdapter(
            rootPath,
            "item${
                getLayoutName(
                    mPageName
                )
            }",
            mBeanClass,
            mAdapterClass
        )
    // 保存Activity
    save(
        mvvmDbFragment,
        moduleTemplateData.srcDir.resolve("${mPageName}Fragment.kt")
    )
    if (mIsGenerateActivityLayout) {
        // 保存xml
        save(
            mvvmDbListFragmentXml(rootPath, packageNameStr, mPageName),
            moduleTemplateData.resDir.resolve("layout/${mFragmentLayoutName}.xml")
        )
    }
    save(
        mvvmDbFragmentViewModel,
        File(
            moduleTemplateData.rootDir.absolutePath
                    + "/src/main/java/"
                    + rootPath.replace(".", "/")
                    + "/mvvm/vm/"
        ).apply { mkdirs() }
            .resolve("${mPageName}FragmentViewModel.kt")
    )
    save(
        mvvmDbFragmentRepository,
        File(
            moduleTemplateData.rootDir.absolutePath
                    + "/src/main/java/"
                    + rootPath.replace(".", "/")
                    + "/mvvm/repository/"
        ).apply { mkdirs() }
            .resolve("${mPageName}FragmentRepository.kt")
    )

    save(
        listBean,
        File(
            moduleTemplateData.rootDir.absolutePath
                    + "/src/main/java/"
                    + rootPath.replace(".", "/")
                    + "/bean/"
        ).apply { mkdirs() }
            .resolve("${mBeanClass}.kt")
    )
    save(
        listAdapter,
        File(
            moduleTemplateData.rootDir.absolutePath
                    + "/src/main/java/"
                    + rootPath.replace(".", "/")
                    + "/adapter/"
        ).apply { mkdirs() }
            .resolve("${mAdapterClass}.kt")
    )

    save(
        baseDbXml(rootPath, mBeanClass),
        moduleTemplateData.resDir.resolve(
            "layout/item${
                getLayoutName(
                    mPageName
                )
            }.xml"
        )
    )
}