package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.list


fun listBean(
    mRootPackageName: String?,
    mBeanClass: String,
) = """
package ${mRootPackageName}.bean

class ${mBeanClass} {

}
"""
