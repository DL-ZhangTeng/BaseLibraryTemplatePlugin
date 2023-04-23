package com.github.dlzhangteng.baselibrarytemplateplugin.template.layout

fun mviDbListXml(
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
            type="${mRootPackageName}.mvi.vm.${mPageName}DbViewModel" />
    </data>

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:orientation="vertical"
        tools:context="${mPagePackageName.ifEmpty { "" }}.${mPageName}DbActivity">
        
        <com.scwang.smart.refresh.layout.SmartRefreshLayout
            android:id="@+id/smartRefreshLayout"
            android:layout_width="match_parent"
            android:layout_height="match_parent">
    
            <com.scwang.smart.refresh.header.ClassicsHeader
                android:layout_width="match_parent"
                android:layout_height="wrap_content" />
    
            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="match_parent">
                
            <androidx.recyclerview.widget.RecyclerView
                android:id="@+id/recyclerView"
                android:layout_width="match_parent"
                android:layout_height="match_parent" />
            </LinearLayout>
    
            <com.scwang.smart.refresh.footer.ClassicsFooter
                android:layout_width="match_parent"
                android:layout_height="wrap_content" />
        </com.scwang.smart.refresh.layout.SmartRefreshLayout>
    </LinearLayout>
</layout>
"""