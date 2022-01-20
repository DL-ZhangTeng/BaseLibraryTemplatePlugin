package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvp.model

fun mvpFragmentModel(
    mRootPackageName: String?,
    mPageName: String
) = """
package ${mRootPackageName}.mvp.model

import com.zhangteng.mvp.base.BaseModel
import ${mRootPackageName}.mvp.model.imodel.I${mPageName}FragmentModel

class ${mPageName}FragmentModel : BaseModel(), I${mPageName}FragmentModel
"""