package com.diveinsoft.blog.headers.apis

import com.diveinsoft.blog.headers.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Call
import okhttp3.RequestBody
import com.google.gson.annotations.SerializedName

import com.diveinsoft.blog.headers.models.Customer

interface CustomerControllerApi {
    /**
     * Emits a \&quot;noStore\&quot; cache control header
     * Emits a \&quot;noStore\&quot; cache control header
     * Responses:
     *  - 200: OK
     *
     * @param customerId 
     * @return [Call]<[Customer]>
     */
    @GET("customers/{customerId}")
    fun getCustomerById(@Path("customerId") customerId: kotlin.Long): Call<Customer>

}
