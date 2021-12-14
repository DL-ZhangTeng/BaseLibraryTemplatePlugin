package com.github.dlzhangteng.baselibrarytemplateplugin.template

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.base.baseActivityTemplate
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.list.listActivityTemplate
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvp.mvpActivityTemplate
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvm.mvvmActivityTemplate
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvmdb.mvvmDbActivityTemplate
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.titlebar.titlebarAcitivityTemplate
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.base.baseFragmentTemplate
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.list.listFragmentTemplate
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvp.mvpFragmentTemplate
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvvm.mvvmFragmentTemplate
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvvmdb.mvvmDbFragmentTemplate

class SamplePluginTemplateProviderImpl : WizardTemplateProvider() {
    override fun getTemplates(): List<Template> = listOf(
        baseActivityTemplate,
        listActivityTemplate,
        titlebarAcitivityTemplate,
        mvpActivityTemplate,
        mvvmActivityTemplate,
        mvvmDbActivityTemplate,

        baseFragmentTemplate,
        listFragmentTemplate,
        mvpFragmentTemplate,
        mvvmFragmentTemplate,
        mvvmDbFragmentTemplate,
    )
}