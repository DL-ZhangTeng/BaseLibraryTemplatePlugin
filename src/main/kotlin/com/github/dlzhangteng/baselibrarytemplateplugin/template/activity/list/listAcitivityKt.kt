package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.list


fun listActivityKt(
    mRootPackageName: String?,
    mActivityPackageName: String,
    mPageName: String,
    mBeanClass: String,
    mAdapterClass: String,
) = """
package ${mRootPackageName}.${mActivityPackageName}

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ${mRootPackageName}.R
import com.zhangteng.base.base.BaseListActivity
import com.scwang.smart.refresh.layout.SmartRefreshLayout

class ActivityCenterActivity : BaseListActivity<${mBeanClass}, ${mAdapterClass}>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         setContentView(R.layout.activity${getLayoutName(mPageName)})
    }

    override fun initView() {
        super.initView()

    }

    override fun initData() {}
    
    override fun createAdapter(): ${mAdapterClass} {
        return ${mAdapterClass}()
    }

    override fun getRecyclerView(): RecyclerView {
        return
    }

    override fun getSmartRefreshLayout(): SmartRefreshLayout {
        return
    }

    override fun loadData(i: Int) {}
    override fun setLayoutManager() {
        setLinearLayoutManager(LinearLayoutManager.VERTICAL)
    }
}
"""
