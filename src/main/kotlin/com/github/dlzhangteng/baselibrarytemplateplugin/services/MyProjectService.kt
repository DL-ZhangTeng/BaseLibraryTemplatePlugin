package com.github.dlzhangteng.baselibrarytemplateplugin.services

import com.intellij.openapi.project.Project
import com.github.dlzhangteng.baselibrarytemplateplugin.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
