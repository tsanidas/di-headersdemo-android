package com.diveinsoft.blog.headers.views


import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.diveinsoft.blog.headers.ui.theme.CacheHeadersDemoTheme

/**
 * A list of APIs with a button to invoke them.
 * After the data is retrieved, we display some sample data,
 * the "Date" response header value, and the "Cache-Control" response header
 * value.
 */
@Composable
fun RequestsView(vm: RequestsViewModel = viewModel()) {
    val productDisplayState by vm.productDisplay.collectAsState()
    val orderDisplayState by vm.orderDisplay.collectAsState()
    val customerDisplayState by vm.customerDisplay.collectAsState()
    val promoDisplayState by vm.promotionDisplay.collectAsState()
    val useV2APIState by vm.useV2APIFlag.collectAsState()

    Column(verticalArrangement = Arrangement.Top) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = rowModifier()
        ) {
            Column(modifier = Modifier.weight(0.36F, true)) {
                Text(text = "Use V2 API",
                    fontWeight = FontWeight.Bold)
            }
            Switch(
                checked = useV2APIState,
                onCheckedChange = {
                    vm.toggleV2API(it)
                },
            )
        }
        ApiListItem(
            headline = "Product Data",
            description = productDisplayState.displayString,
            date = productDisplayState.dateHeaderValue,
            cacheControl = productDisplayState.cacheControlValue,
            retrieveAction = {
                vm.retrieveProductData()
            }
        )
        ApiListItem(
            headline = "Order Data",
            description = orderDisplayState.displayString,
            date = orderDisplayState.dateHeaderValue,
            cacheControl = orderDisplayState.cacheControlValue,
            retrieveAction = {
                vm.retrieveOrderData()
            }
        )
        ApiListItem(
            headline = "Customer Data",
            description = customerDisplayState.displayString,
            date = customerDisplayState.dateHeaderValue,
            cacheControl = customerDisplayState.cacheControlValue,
            retrieveAction = {
                vm.retrieveCustomerData()
            }
        )
        ApiListItem(
            headline = "Promotion Data",
            description = promoDisplayState.displayString,
            date = promoDisplayState.dateHeaderValue,
            cacheControl = promoDisplayState.cacheControlValue,
            retrieveAction = {
                vm.retrievePromotionData()
            }
        )
    }
}

@Composable
fun ApiListItem(
    headline: String = "",
    description: String = "",
    date: String = "",
    cacheControl: String = "",
    retrieveAction: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = rowModifier()
    ) {
        Column(modifier = Modifier.weight(0.36F, true)) {
            Text(text = headline,
                fontWeight = FontWeight.Bold
            )
        }
        Column(modifier = Modifier.weight(0.65F, true)) {
                Text(
                    description,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    date,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    cacheControl,
                    fontWeight = FontWeight.Medium
                )
        }
        Column(Modifier.width(32.dp), horizontalAlignment = Alignment.End) {
            IconButton(onClick = {
                retrieveAction()
            }) {
                Icon(Icons.Filled.Refresh, contentDescription = "Refresh")
            }
        }
    }
}

@Composable
private fun rowModifier(): Modifier {
    return Modifier
        .fillMaxWidth(1F)
        .padding(2.dp)
        .border(1.dp, MaterialTheme.colorScheme.outline, shape = RoundedCornerShape(8.dp))
        .padding(8.dp)
}

@Preview(
    name = "Regular Mode",
    showBackground = true,
    showSystemUi = true
)
@Composable
fun RequestsViewPreview() {
    CacheHeadersDemoTheme {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = rowModifier()
            ) {
                Column(modifier = Modifier.weight(0.36F, true)) {
                    Text(text = "Use V2 API",
                        fontWeight = FontWeight.Bold)
                }
                //Spacer(modifier = Modifier.width(40.dp))
                Switch(
                    checked = false,
                    onCheckedChange = {
                    },
                )
            }
            ApiListItem(
                headline = "Product Data",
                description = "ProV1 Golf Balls",
                date = "Tuesday 18:33:33",
                cacheControl = "max-age=60",
                retrieveAction = {
                    Log.d("Preview", "Product data tapped")
                }
            )
            ApiListItem(
                headline = "Order Data",
                description = "1001",
                date = "Tuesday 18:33:33",
                cacheControl = "nocache",
                retrieveAction = {}
            )
        }
    }
}