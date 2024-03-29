package com.github.dlzhangteng.baselibrarytemplateplugin.template

import com.android.tools.idea.wizard.template.ModuleTemplateData
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.impl.activities.common.generateManifest
import java.util.Locale

/**
 * 获取layoutName
 */
fun getLayoutName(pageName: String): String {
    val stringBuilder: StringBuilder = StringBuilder()
    val activityChildNames: ArrayList<String> = splitByUpperCase(pageName)
    activityChildNames.forEach {
        stringBuilder.append("_").append(it.lowercase(Locale.getDefault()))
    }
    return stringBuilder.toString()
}

/**
 * 获取pageName
 */
fun getPageName(layoutName: String): String {
    val stringBuilder: StringBuilder = StringBuilder()
    val activityChildNames: List<String> = splitByCrossing(layoutName)
    activityChildNames.forEach {
        stringBuilder.append(upCaseKeyFirstChar(it.lowercase(Locale.getDefault())))
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

/**
 * 根据横线拆分数组
 */
fun splitByCrossing(str: String): List<String> {
    return str.split("_")
}

/**
 * 首字母转大写
 */
fun upCaseKeyFirstChar(key: String): String {
    return if (Character.isUpperCase(key[0])) {
        key
    } else {
        StringBuilder()
            .append(Character.toUpperCase(key[0]))
            .append(key.substring(1))
            .toString()
    }
}

/**
 * description 添加activity到Manifest
 * @param activityClass activity相对路径如：.activity.BaseActivity
 */
fun addActivityToManifest(
    recipeExecutor: RecipeExecutor,
    moduleTemplateData: ModuleTemplateData,
    activityClass: String
) {
    recipeExecutor.generateManifest(
        moduleData = moduleTemplateData,
        activityClass = activityClass,
        packageName = "",
        isLauncher = false,
        hasNoActionBar = false,
        generateActivityTitle = false
    )
}
