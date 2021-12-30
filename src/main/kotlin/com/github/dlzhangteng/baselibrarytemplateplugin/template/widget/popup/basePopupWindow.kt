package com.github.dlzhangteng.baselibrarytemplateplugin.template.widget.popup

fun basePopupWindow(
    mRootPackageName: String?,
    mPopupWindowLayoutName: String,
    mPopupWindowClass: String,
) = """
package ${mRootPackageName}.widget

import android.content.Context
import android.view.View
import com.zhangteng.base.base.BasePopupWindow
import ${mRootPackageName}.R

class ${mPopupWindowClass}(context: Context?) : BasePopupWindow(context) {
    override fun getSelfTitleView(): Int {
        return 0
    }

    override fun getSelfContentView(): Int {
        return R.layout.${mPopupWindowLayoutName}
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
