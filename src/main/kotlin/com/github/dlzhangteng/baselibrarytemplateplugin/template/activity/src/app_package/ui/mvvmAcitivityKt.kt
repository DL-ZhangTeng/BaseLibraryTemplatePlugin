package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.src.app_package.ui

import java.util.*


fun mvvmActivityKt(
    mRootPackageName: String?,
    mActivityPackageName: String,
    mPageName: String
) = """
package ${mRootPackageName}.${mActivityPackageName}

import android.os.Bundle

import com.zhangteng.base.base.BaseActivity
import ${mRootPackageName}.R

class ${mPageName}Activity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity${getLayoutName(mPageName)})
    }

    override fun initView() {
     
    }

    override fun initData() {

    }
}
"""

/**
 * 获取layoutName
 */
fun getLayoutName(pageName: String): String {
    val stringBuilder: StringBuilder = StringBuilder()
    val activityChildNames: ArrayList<String> = splitByUpperCase(pageName)
    activityChildNames.forEach {
        stringBuilder.append("_").append(it.toLowerCase())
    }
    return stringBuilder.toString()
}

/**
 * 根据大写字母拆分数组
 */
fun splitByUpperCase(str: String): ArrayList<String> {
    val rs = ArrayList<String>()
    var index = 0
    val len = str.length
    for (i in 1 until len) {
        if (Character.isUpperCase(str[i])) {
            rs.add(str.substring(index, i))
            index = i
        }
    }
    rs.add(str.substring(index, len))
    return rs
}
