package com.github.dlzhangteng.baselibrarytemplateplugin.template.other.adapter


fun baseBean(
    mRootPackageName: String?,
    mBeanClass: String,
) = """
package ${mRootPackageName}.bean

class ${mBeanClass} {

}
"""
