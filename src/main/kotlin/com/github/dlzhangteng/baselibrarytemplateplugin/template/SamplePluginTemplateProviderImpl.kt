package com.github.dlzhangteng.baselibrarytemplateplugin.template

import com.android.tools.idea.wizard.template.Template
import com.android.tools.idea.wizard.template.WizardTemplateProvider
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.base.baseActivityTemplate
import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.list.listActivityTemplate
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.base.baseFragmentTemplate
import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.list.listFragmentTemplate
import com.github.dlzhangteng.baselibrarytemplateplugin.template.other.adapter.baseAdapterTemplate
import com.github.dlzhangteng.baselibrarytemplateplugin.template.other.bindingadapter.bindingAdapterTemplate
import com.github.dlzhangteng.baselibrarytemplateplugin.template.other.header.headerOrFooterAdapterTemplate
import com.github.dlzhangteng.baselibrarytemplateplugin.template.other.tree.treeAdapterTemplate
import com.github.dlzhangteng.baselibrarytemplateplugin.template.widget.dialog.baseDialogTemplate
import com.github.dlzhangteng.baselibrarytemplateplugin.template.widget.popup.basePopupWindowTemplate

class SamplePluginTemplateProviderImpl : WizardTemplateProvider() {
    override fun getTemplates(): List<Template> = listOf(
        baseActivityTemplate,
        listActivityTemplate,

        baseFragmentTemplate,
        listFragmentTemplate,

        baseAdapterTemplate,
        bindingAdapterTemplate,
        headerOrFooterAdapterTemplate,
        treeAdapterTemplate,

        baseDialogTemplate,
        basePopupWindowTemplate,
    )
}