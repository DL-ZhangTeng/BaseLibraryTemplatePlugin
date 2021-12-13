package com.github.dlzhangteng.baselibrarytemplateplugin.template

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvmActivityTemplate

class SamplePluginTemplateProviderImpl: WizardTemplateProvider() {
    override fun getTemplates(): List<Template> = listOf(
        mvvmActivityTemplate
    )
}