package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvmList

import com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.base.getLayoutName

fun mvvmListActivity(
    mRootPackageName: String?,
    mActivityPackageName: String,
    mPageName: String
) = """
package ${mRootPackageName}${if (mActivityPackageName.isNullOrEmpty()) "" else ".${mActivityPackageName}"}

import android.content.IntentFilter
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.zhangteng.mvvm.mvvm.BaseListMvvmActivity
import com.zhangteng.mvvm.manager.NetworkStateReceive
import ${mRootPackageName}.R
import ${mRootPackageName}.adapter.${mPageName}Adapter
import ${mRootPackageName}.bean.${mPageName}Bean
import ${mRootPackageName}.mvvm.vm.${mPageName}ViewModel


class ${mPageName}Activity : BaseListMvvmActivity<${mPageName}ViewModel, ${mPageName}Bean, ${mPageName}Adapter>() {

    private var intentFilter: IntentFilter? = null
    private var netChangeReceiver: NetworkStateReceive? = null
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity${getLayoutName(mPageName)})
    }

    override fun initView() {
        super.initView()
        intentFilter = IntentFilter()
        // 添加action,当网络情况发生变化时，系统就是发送一条值为android.net.conn.CONNECTIVITY_CHANGE的广播
        intentFilter?.addAction("android.net.conn.CONNECTIVITY_CHANGE")
        netChangeReceiver = NetworkStateReceive()
        // 注册广播
        registerReceiver(netChangeReceiver, intentFilter)
    }

    override fun initData() {}

    override fun createAdapter(): ${mPageName}Adapter {
        return ${mPageName}Adapter(mList)
    }

    override fun getRecyclerView(): RecyclerView {
        return findViewById(R.id.recyclerView)
    }

    override fun getSmartRefreshLayout(): SmartRefreshLayout {
        return findViewById(R.id.smartRefreshLayout)
    }

    override fun loadData(i: Int) {}

    override fun setLayoutManager() {
        setLinearLayoutManager(LinearLayoutManager.VERTICAL)
    }
    
    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(netChangeReceiver)
    }
}
"""