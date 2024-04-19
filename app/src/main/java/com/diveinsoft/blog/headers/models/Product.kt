/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package com.diveinsoft.blog.headers.models


import com.google.gson.annotations.SerializedName

/**
 * 
 *
 * @param id 
 * @param brand 
 * @param description 
 * @param imageUrl 
 * @param price 
 * @param weight 
 * @param category 
 * @param rating 
 */


data class Product (

    @SerializedName("id")
    val id: kotlin.Long? = null,

    @SerializedName("brand")
    val brand: kotlin.String? = null,

    @SerializedName("description")
    val description: kotlin.String? = null,

    @SerializedName("imageUrl")
    val imageUrl: kotlin.String? = null,

    @SerializedName("price")
    val price: kotlin.Double? = null,

    @SerializedName("weight")
    val weight: kotlin.Double? = null,

    @SerializedName("category")
    val category: kotlin.String? = null,

    @SerializedName("rating")
    val rating: kotlin.Double? = null

)

