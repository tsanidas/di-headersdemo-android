package com.diveinsoft.blog.headers.apis

import com.diveinsoft.blog.headers.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Call
import okhttp3.RequestBody
import com.google.gson.annotations.SerializedName

import com.diveinsoft.blog.headers.models.Product

interface ProductControllerApi {
    /**
     * Emits a \&quot;max-age&#x3D;60\&quot; cache control header
     * Emits a \&quot;max-age&#x3D;60\&quot; cache control header
     * Responses:
     *  - 200: OK
     *
     * @param productId 
     * @return [Call]<[Product]>
     */
    @GET("products/{productId}")
    fun getProductById(@Path("productId") productId: kotlin.Long): Call<Product>

}
