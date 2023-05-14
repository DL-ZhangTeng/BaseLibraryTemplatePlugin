package com.github.dlzhangteng.baselibrarytemplateplugin.template.fragment.mvi

import com.github.dlzhangteng.baselibrarytemplateplugin.template.DependencyInjectionEnum

fun mviFragmentRepository(
    mRootPackageName: String?,
    mPageName: String,
    mDependencyInjectionEnum: DependencyInjectionEnum
) = """
package ${mRootPackageName}.mvi.repository

import com.zhangteng.mvvm.base.BaseNetRepository${
    if (mDependencyInjectionEnum != DependencyInjectionEnum.HILT)
        """
import com.zhangteng.httputils.http.HttpUtils"""
    else """"""
}
import ${mRootPackageName}.http.Api
${
    if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT)
        """import javax.inject.Inject
    
""" else """
    
"""
}
class ${mPageName}FragmentRepository ${if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT) "@Inject constructor() " else ""}: BaseNetRepository() {

${
    if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT) """
    @Inject
    override lateinit var mService: Api
"""
    else """
    override var mService by lazy {
        HttpUtils.getInstance().ConfigGlobalHttpUtils().createService(Api::class.java)
    }
"""
}

}
"""