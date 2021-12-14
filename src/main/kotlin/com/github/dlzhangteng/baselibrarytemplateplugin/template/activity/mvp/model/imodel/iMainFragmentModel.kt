package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvp.model.imodel

fun mvpIModel(
    mRootPackageName: String?,
    mPageName: String
) = """
package ${mRootPackageName}.mvp.model.imodel

import com.zhangteng.base.mvp.base.IModel

interface I${mPageName}Model : IModel
"""