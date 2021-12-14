package com.github.dlzhangteng.baselibrarytemplateplugin.template

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.base.baseActivityTemplate
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.list.listActivityTemplate
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvp.mvpActivityTemplate
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.titlebar.titlebarAcitivityTemplate

class SamplePluginTemplateProviderImpl : WizardTemplateProvider() {
    override fun getTemplates(): List<Template> = listOf(
        baseActivityTemplate,
        listActivityTemplate,
        titlebarAcitivityTemplate,
        mvpActivityTemplate,
    )
}