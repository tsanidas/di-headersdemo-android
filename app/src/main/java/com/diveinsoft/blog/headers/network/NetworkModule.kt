package com.diveinsoft.blog.headers.network

import android.content.Context
import com.diveinsoft.blog.headers.apis.CustomerControllerApi
import com.diveinsoft.blog.headers.apis.OrderControllerApi
import com.diveinsoft.blog.headers.apis.ProductControllerApi
import com.diveinsoft.blog.headers.apis.PromotionControllerApi
import com.diveinsoft.blog.headers.infrastructure.ApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.io.File
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * A Hilt module for DI.
 * It is here that we enable caching, which is not enabled by default.
 * While we do have the generated ApiClient class, we need to modify the
 * OkHttpClient to enable caching.
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    private var apiClient: ApiClient? = null

    private fun provideHttpClient(@ApplicationContext appContext: Context): OkHttpClient.Builder {
        return OkHttpClient
            .Builder()
            // https://square.github.io/okhttp/features/caching/
            .cache(
                Cache(
                    directory = File(appContext.cacheDir, "http_cache"),
                    maxSize = 50L * 1024L * 1024L // 50 MiB
                )
            )
            .readTimeout(60, TimeUnit.SECONDS)
            .connectTimeout(60, TimeUnit.SECONDS)
    }

    @Singleton
    @Provides
    fun provideProductService(@ApplicationContext appContext: Context): ProductControllerApi {
        if (apiClient == null) {
            apiClient = ApiClient(okHttpClientBuilder = provideHttpClient(appContext))
        }
        return apiClient!!.createService(ProductControllerApi::class.java)
    }

    @Singleton
    @Provides
    fun provideOrderService(@ApplicationContext appContext: Context): OrderControllerApi {
        if (apiClient == null) {
            apiClient = ApiClient(okHttpClientBuilder = provideHttpClient(appContext))
        }
        return apiClient!!.createService(OrderControllerApi::class.java)
    }

    @Singleton
    @Provides
    fun provideCustomerService(@ApplicationContext appContext: Context): CustomerControllerApi {
        if (apiClient == null) {
            apiClient = ApiClient(okHttpClientBuilder = provideHttpClient(appContext))
        }
        return apiClient!!.createService(CustomerControllerApi::class.java)
    }

    @Singleton
    @Provides
    fun providePromotionService(@ApplicationContext appContext: Context): PromotionControllerApi {
        if (apiClient == null) {
            apiClient = ApiClient(okHttpClientBuilder = provideHttpClient(appContext))
        }
        return apiClient!!.createService(PromotionControllerApi::class.java)
    }
}