package com.github.dlzhangteng.baselibrarytemplateplugin.template.widget.dialog


fun baseDialog(
    mRootPackageName: String?,
    mDialogLayoutName: String,
    mDialogClass: String,
) = """
package ${mRootPackageName}.widget

import android.content.Context
import android.view.View
import com.zhangteng.base.base.BaseDialog
import ${mRootPackageName}.R

class ${mDialogClass}(context: Context) : BaseDialog(context, R.style.SelfDialog) {
    override fun getSelfTitleView(): Int {
        return 0
    }

    override fun getSelfContentView(): Int {
        return R.layout.${mDialogLayoutName}
    }

    override fun getSelfButtonView(): Int {
        return 0
    }

    override fun initView(parent: View) {
        
    }
    
    override fun initData() {
        
    }

    init {
        initData()
    }
}
"""
