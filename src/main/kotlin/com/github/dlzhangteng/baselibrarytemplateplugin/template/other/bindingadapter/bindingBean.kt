package com.github.dlzhangteng.baselibrarytemplateplugin.template.other.bindingadapter


fun bindingBean(
    mRootPackageName: String?,
    mBeanClass: String,
) = """
package ${mRootPackageName}.bean

import com.zhangteng.mvvm.adapter.BindingIdBean

class ${mBeanClass} : BindingIdBean() {

}
"""
