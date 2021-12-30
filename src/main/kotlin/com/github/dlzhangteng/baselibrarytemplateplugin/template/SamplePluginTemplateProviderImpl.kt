package com.github.dlzhangteng.baselibrarytemplateplugin.template

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.base.baseActivityTemplate
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.list.listActivityTemplate
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvp.mvpActivityTemplate
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvpList.mvpListActivityTemplate
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvm.mvvmActivityTemplate
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvmdb.mvvmDbActivityTemplate
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.titlebar.titlebarAcitivityTemplate
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.base.baseFragmentTemplate
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.list.listFragmentTemplate
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvp.mvpFragmentTemplate
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvpList.mvpListFragmentTemplate
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvvm.mvvmFragmentTemplate
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvvmdb.mvvmDbFragmentTemplate
import com.github.dlzhangteng.baselibrarytemplateplugin.template.other.adapter.baseAdapterTemplate
import com.github.dlzhangteng.baselibrarytemplateplugin.template.widget.dialog.baseDialogTemplate

class SamplePluginTemplateProviderImpl : WizardTemplateProvider() {
    override fun getTemplates(): List<Template> = listOf(
        baseActivityTemplate,
        listActivityTemplate,
        titlebarAcitivityTemplate,
        mvpActivityTemplate,
        mvpListActivityTemplate,
        mvvmActivityTemplate,
        mvvmDbActivityTemplate,

        baseFragmentTemplate,
        listFragmentTemplate,
        mvpFragmentTemplate,
        mvpListFragmentTemplate,
        mvvmFragmentTemplate,
        mvvmDbFragmentTemplate,

        baseAdapterTemplate,

        baseDialogTemplate,
    )
}