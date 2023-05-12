package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvpList

import com.github.dlzhangteng.baselibrarytemplateplugin.template.DependencyInjectionEnum

fun mvpListActivity(
    mRootPackageName: String?,
    mActivityPackageName: String,
    mPageName: String,
    mActivityLayoutName: String,
    mBeanClass: String,
    mAdapterClass: String,
    mDependencyInjectionEnum: DependencyInjectionEnum,
) = """
package ${mRootPackageName}${mActivityPackageName.ifEmpty { "" }}

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.zhangteng.base.base.BaseAdapter
import com.zhangteng.mvp.mvp.BaseListMvpActivity
import ${mRootPackageName}.R
import ${mRootPackageName}.mvp.model.imodel.I${mPageName}Model
${
    if (mDependencyInjectionEnum != DependencyInjectionEnum.HILT) """import ${mRootPackageName}.mvp.presenter.${mPageName}Presenter""" else ""
}
import ${mRootPackageName}.mvp.presenter.ipresenter.I${mPageName}Presenter
import ${mRootPackageName}.mvp.view.I${mPageName}View
import ${mRootPackageName}.adapter.${mPageName}Adapter
import ${mRootPackageName}.bean.${mPageName}Bean
${
    if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT)
        """import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint""" else
        """
    
"""
}
class ${mPageName}Activity : BaseListMvpActivity<I${mPageName}View, I${mPageName}Model, I${mPageName}Presenter, ${mBeanClass}, BaseAdapter.DefaultViewHolder, ${mAdapterClass}>(), I${mPageName}View {

${
    if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT) """
    @Inject
    override lateinit var mPresenter: I${mPageName}Presenter
"""
    else """
    override var mPresenter: I${mPageName}Presenter = ${mPageName}Presenter()
"""
}
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.${mActivityLayoutName})
    }

    override fun initView() {
        super.initView()

    }

    override fun initData() {}

    override fun createAdapter(): $mAdapterClass {
        return ${mAdapterClass}(mList)
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
}
"""