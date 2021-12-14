package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvp.model.imodel

fun mvpIFragmentModel(
    mRootPackageName: String?,
    mPageName: String
) = """
package ${mRootPackageName}.mvp.model.imodel

import com.zhangteng.base.mvp.base.IModel

interface I${mPageName}FragmentModel : IModel
"""