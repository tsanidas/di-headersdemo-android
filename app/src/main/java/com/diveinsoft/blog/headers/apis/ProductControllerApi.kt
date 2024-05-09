package com.diveinsoft.blog.headers.apis

import com.diveinsoft.blog.headers.models.Product
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Header

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

    /**
     * Emits an \&quot;etag&#x3D;\&quot; cache control header.
     * Emits an \&quot;etag&#x3D;\&quot; cache control header.  Fake product IDS are 1,2 or 3
     * Responses:
     *  - 200: OK
     *
     * @param productId
     * @param ifNoneMatch  (optional)
     * @return [Call]<[Product]>
     */
    @GET("products/v2/{productId}")
    fun getProductByIdWithCache(@Path("productId") productId: kotlin.Long, @Header("If-None-Match") ifNoneMatch: kotlin.String? = null): Call<Product>
}
