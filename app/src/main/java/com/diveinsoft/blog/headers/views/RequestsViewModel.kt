package com.diveinsoft.blog.headers.views

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diveinsoft.blog.headers.apis.CustomerControllerApi
import com.diveinsoft.blog.headers.apis.OrderControllerApi
import com.diveinsoft.blog.headers.apis.ProductControllerApi
import com.diveinsoft.blog.headers.apis.PromotionControllerApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ApiDisplayBlock(
    var displayString: String,
    var dateHeaderValue: String = "",
    var cacheControlValue: String = ""
) {

}

/**
 * ViewModel for our API results screen.
 * This is where the ugly stuff lives, like data retrieval, etc
 * This API is publicly available via Google App Engine
 * To see the Swagger UI docs, go to ${urlBase}/swagger-ui/index.html
 */
@HiltViewModel
class RequestsViewModel @Inject constructor (
    private var productService: ProductControllerApi,
    private var orderService: OrderControllerApi,
    private var customerService: CustomerControllerApi,
    private var promotionService: PromotionControllerApi
): ViewModel() {
    private val _uiState = MutableStateFlow(listOf(
        ApiDisplayBlock("Orders Data"),
        ApiDisplayBlock("Customer Data"),
        ApiDisplayBlock("Promotions Data")
    ))
    private val _productState = MutableStateFlow(ApiDisplayBlock(""))
    val productDisplay: StateFlow<ApiDisplayBlock> = _productState.asStateFlow()
    private val _orderState = MutableStateFlow(ApiDisplayBlock(""))
    val orderDisplay: StateFlow<ApiDisplayBlock> = _orderState.asStateFlow()
    private val _customerState = MutableStateFlow(ApiDisplayBlock(""))
    val customerDisplay: StateFlow<ApiDisplayBlock> = _customerState.asStateFlow()
    private val _promotionState = MutableStateFlow(ApiDisplayBlock(""))
    val promotionDisplay: StateFlow<ApiDisplayBlock> = _promotionState.asStateFlow()
    // Operations

    /**
     * Method to retrieve products using fixed (fake) product ID numbers
     */
    fun retrieveProductData() {
        viewModelScope.launch(Dispatchers.IO) {
            val resp = productService.getProductById(2)
                .execute()
            if (resp.isSuccessful) {
                val dt = resp.raw().header("Date", "") ?: ""
                val cc = resp.raw().header("Cache-Control", "-") ?: "-"
                val displayStr = resp.body()?.description ?: ""
                _productState.update { currentProduct ->
                    currentProduct.copy(
                        displayString = displayStr,
                        dateHeaderValue = dt,
                        cacheControlValue = cc
                    )
                }
            }
        }
    }

    /**
     * Retrieve orders data for a fixed (fake) customerId
     */
    fun retrieveOrderData() {
        viewModelScope.launch(Dispatchers.IO) {
            val resp = orderService.getOrdersByCustomer(994)
                .execute()
            if (resp.isSuccessful) {
                val dt = resp.raw().header("Date", "") ?: ""
                val cc = resp.raw().header("Cache-Control", "-") ?: "-"
                val displayStr = resp.body()?.get(0)?.id.toString() ?: ""
                _orderState.update { currentOrder ->
                    currentOrder.copy(
                        displayString = displayStr,
                        dateHeaderValue = dt,
                        cacheControlValue = cc
                    )
                }
            }
        }
    }

    /**
     * Retrieve customer data for a fixed (fake) customerId
     */
    fun retrieveCustomerData() {
        viewModelScope.launch(Dispatchers.IO) {
            val resp = customerService.getCustomerById(994)
                .execute()
            if (resp.isSuccessful) {
                val dt = resp.raw().header("Date", "") ?: ""
                val cc = resp.raw().header("Cache-Control", "-") ?: "-"
                val displayStr = resp.body()?.name ?: ""
                _customerState.update { currentCustomer ->
                    currentCustomer.copy(
                        displayString = displayStr,
                        dateHeaderValue = dt,
                        cacheControlValue = cc
                    )
                }
            }
        }
    }

    /**
     * Retrieve promotions data associated with a fixed (fake) productId
     */
    fun retrievePromotionData() {
        viewModelScope.launch(Dispatchers.IO) {
            val resp = promotionService.getCurrentPromotions("1")
                .execute()
            if (resp.isSuccessful) {
                val dt = resp.raw().header("Date", "") ?: ""
                val cc = resp.raw().header("Cache-Control", "-") ?: "-"
                val displayStr = resp.body()?.get(0)?.title ?: ""
                _promotionState.update { currentPromo ->
                    currentPromo.copy(
                        displayString = displayStr,
                        dateHeaderValue = dt,
                        cacheControlValue = cc
                    )
                }
            }
        }
    }
}