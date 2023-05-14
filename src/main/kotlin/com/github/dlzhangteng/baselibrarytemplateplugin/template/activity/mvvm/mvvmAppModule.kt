package com.github.dlzhangteng.baselibrarytemplateplugin.template.activity.mvvm

fun mvvmAppModule(
    mRootPackageName: String?,
) = """
package ${mRootPackageName}.mvvm.di

import com.zhangteng.httputils.http.HttpUtils
import ${mRootPackageName}.http.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun createApi(): Api {
        return HttpUtils.instance.ConfigGlobalHttpUtils().createService(Api::class.java)
    }
}
"""