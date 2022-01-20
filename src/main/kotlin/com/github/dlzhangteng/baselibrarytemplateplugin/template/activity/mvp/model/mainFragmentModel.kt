package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvp.model

fun mvpModel(
    mRootPackageName: String?,
    mPageName: String
) = """
package ${mRootPackageName}.mvp.model

import com.zhangteng.mvp.base.BaseModel
import ${mRootPackageName}.mvp.model.imodel.I${mPageName}Model

class ${mPageName}Model : BaseModel(), I${mPageName}Model
"""