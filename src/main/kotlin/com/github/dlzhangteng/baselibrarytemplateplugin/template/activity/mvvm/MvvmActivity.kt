package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvm

import com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.base.getLayoutName

fun mvvmActivity(
    mRootPackageName: String?,
    mActivityPackageName: String,
    mPageName: String
) = """
package ${mRootPackageName}${if (mActivityPackageName.isNullOrEmpty()) "" else ".${mActivityPackageName}"}

import android.content.IntentFilter
import android.os.Bundle
import com.zhangteng.base.base.BaseMvvmActivity
import com.zhangteng.base.mvvm.manager.NetworkStateReceive
import ${mRootPackageName}.R
import ${mRootPackageName}.mvvm.vm.${mPageName}ViewModel


class ${mPageName}Activity : BaseMvvmActivity<${mPageName}ViewModel>() {

    private var intentFilter: IntentFilter? = null
    private var netChangeReceiver: NetworkStateReceive? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity${getLayoutName(mPageName)})
    }

    override fun initView() {
        intentFilter = IntentFilter()
        // 添加action,当网络情况发生变化时，系统就是发送一条值为android.net.conn.CONNECTIVITY_CHANGE的广播
        intentFilter?.addAction("android.net.conn.CONNECTIVITY_CHANGE")
        netChangeReceiver = NetworkStateReceive()
        // 注册广播
        registerReceiver(netChangeReceiver, intentFilter)
    }

    override fun initData() {

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(netChangeReceiver)
    }
}
"""