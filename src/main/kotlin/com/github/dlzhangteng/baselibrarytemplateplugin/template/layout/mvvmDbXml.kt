package com.github.dlzhangteng.baselibrarytemplateplugin.template.layout

fun mvvmDbXml(
    mRootPackageName: String?,
    mPagePackageName: String,
    mPageName: String
) = """
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools">

    <data>

        <variable
            name="viewModel"
            type="${mRootPackageName}.mvvm.vm.${mPageName}ViewModel" />
    </data>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        tools:context="${mPagePackageName.ifEmpty { "" }}.${mPageName}Activity">

    </LinearLayout>
</layout>
"""