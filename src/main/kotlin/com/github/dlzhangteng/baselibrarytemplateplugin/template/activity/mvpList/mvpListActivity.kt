package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvpList

fun mvpListActivity(
    mRootPackageName: String?,
    mActivityPackageName: String,
    mPageName: String,
    mActivityLayoutName: String,
    mBeanClass: String,
    mAdapterClass: String,
) = """
package ${mRootPackageName}${mActivityPackageName.ifEmpty { "" }}

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.zhangteng.mvp.mvp.BaseListMvpActivity
import ${mRootPackageName}.mvp.model.imodel.I${mPageName}Model
import ${mRootPackageName}.mvp.presenter.${mPageName}Presenter
import ${mRootPackageName}.mvp.presenter.ipresenter.I${mPageName}Presenter
import ${mRootPackageName}.mvp.view.I${mPageName}View
import ${mRootPackageName}.R
import ${mRootPackageName}.adapter.${mPageName}Adapter
import ${mRootPackageName}.bean.${mPageName}Bean

class ${mPageName}Activity : BaseListMvpActivity<I${mPageName}View, I${mPageName}Model, I${mPageName}Presenter, ${mBeanClass}, ${mAdapterClass}>(), I${mPageName}View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.${mActivityLayoutName})
    }

    override fun createPresenter(): I${mPageName}Presenter? {
        return ${mPageName}Presenter()
//        return Proxy.newProxyInstance(
//            ${mPageName}Presenter::class.java.classLoader,
//            arrayOf(I${mPageName}Presenter::class.java),
//            LoadingPresenterHandler(${mPageName}Presenter())
//        ) as I${mPageName}Presenter
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