package com.github.dlzhangteng.baselibrarytemplateplugin.template.layout

fun baseDbXml(
    mRootPackageName: String?,
    mBeanClass: String,
) = """
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android">

    <data>

        <!--BindingAdapter.kt使用item绑定数据，所以此处name不可修改，除非自定义BindingAdapter并重写绑定数据逻辑-->
        <variable
            name="item"
            type="${mRootPackageName}.bean.${mBeanClass}" />
    </data>

    <androidx.constraintlayout.widget.ConstraintLayout
        android:layout_width="match_parent"
        android:layout_height="100dp"
        android:layout_marginTop="10dp"
        android:background="@color/base_theme_color">

    </androidx.constraintlayout.widget.ConstraintLayout>
</layout>
"""
