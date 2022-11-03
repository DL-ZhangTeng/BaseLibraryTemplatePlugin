package com.github.dlzhangteng.baselibrarytemplateplugin.template.layout

fun basePageXml(
    mPageClassName: String
) = """
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="$mPageClassName">
    
    
</androidx.constraintlayout.widget.ConstraintLayout>
"""
