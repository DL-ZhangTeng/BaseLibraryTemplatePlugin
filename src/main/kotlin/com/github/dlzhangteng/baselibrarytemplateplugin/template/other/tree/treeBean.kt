package com.github.dlzhangteng.baselibrarytemplateplugin.template.other.tree


fun baseBean(
    mRootPackageName: String?,
    mBeanClass: String,
) = """
package ${mRootPackageName}.bean

import com.zhangteng.base.tree.TreeNodeChildren
import com.zhangteng.base.tree.TreeNodeId
import com.zhangteng.base.tree.TreeNodeLabel
import com.zhangteng.base.tree.TreeNodeParent

class ${mBeanClass} {
    @TreeNodeId
    var id: String? = null

    @TreeNodeLabel
    var label: String? = null

    @TreeNodeChildren
    var children: ArrayList<${mBeanClass}?>? = null

    @TreeNodeParent
    var parent: ${mBeanClass}? = null
}
"""
