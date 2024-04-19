package com.diveinsoft.blog.headers.apis

import com.diveinsoft.blog.headers.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Call
import okhttp3.RequestBody
import com.google.gson.annotations.SerializedName

import com.diveinsoft.blog.headers.models.Order

interface OrderControllerApi {
    /**
     * Emits a \&quot;nocache\&quot; cache control header
     * Emits a \&quot;nocache\&quot; cache control header
     * Responses:
     *  - 200: OK
     *
     * @param customerId 
     * @return [Call]<[kotlin.collections.List<Order>]>
     */
    @GET("orders/customer/{customerId}")
    fun getOrdersByCustomer(@Path("customerId") customerId: kotlin.Long): Call<kotlin.collections.List<Order>>

}
