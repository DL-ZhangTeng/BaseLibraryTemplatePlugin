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
        """import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped""" else
        """
    
"""
}
class ${mPageName}FragmentRepository ${if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT) "@Inject constructor() " else ""}: BaseNetRepository() {

${
    if (mDependencyInjectionEnum == DependencyInjectionEnum.HILT) """
    @Inject
    lateinit var mService: Api
"""
    else """
    val mService by lazy {
        HttpUtils.instance.ConfigGlobalHttpUtils().createService(Api::class.java)
    }
"""
}

}
"""