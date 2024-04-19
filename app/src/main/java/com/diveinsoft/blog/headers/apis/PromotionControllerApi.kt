package com.diveinsoft.blog.headers.apis

import com.diveinsoft.blog.headers.infrastructure.CollectionFormats.*
import retrofit2.http.*
import retrofit2.Call
import okhttp3.RequestBody
import com.google.gson.annotations.SerializedName

import com.diveinsoft.blog.headers.models.Promotion

interface PromotionControllerApi {
    /**
     * Does not emit any specific cache control header
     * Does not emit any specific cache control header
     * Responses:
     *  - 200: OK
     *
     * @param productId 
     * @return [Call]<[kotlin.collections.List<Promotion>]>
     */
    @GET("promotions/product/{productId}")
    fun getCurrentPromotions(@Path("productId") productId: kotlin.String): Call<kotlin.collections.List<Promotion>>

}
