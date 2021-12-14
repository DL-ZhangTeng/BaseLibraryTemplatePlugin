package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.list


fun listFragment(
    mRootPackageName: String?,
    mActivityPackageName: String,
    mPageName: String,
    mBeanClass: String,
    mAdapterClass: String,
) = """
package ${mRootPackageName}${if (mActivityPackageName.isNullOrEmpty()) "" else ".${mActivityPackageName}"}

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ${mRootPackageName}.R
import com.zhangteng.base.base.BaseListFragment
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import ${mRootPackageName}.bean.${mBeanClass}
import ${mRootPackageName}.adapter.${mAdapterClass}

class ${mPageName}Fragment : BaseListFragment<${mBeanClass}, ${mAdapterClass}>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment${getLayoutName(mPageName)}, container, false)
    }

    override fun initView(view: View, savedInstanceState: Bundle?) {
        super.initView(view, savedInstanceState)
    }

    override fun initData(savedInstanceState: Bundle?) {
        super.initData(savedInstanceState)
    }
    
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
    
    companion object {
        fun newInstance(): ${mPageName}Fragment {
            return BlankFragment().apply {
                arguments = Bundle()
            }
        }
    }
}
"""
