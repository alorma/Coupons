package com.alorma.coupons.ui.screen.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alorma.coupons.domain.id.toKey
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun CouponsListScreen(
    CouponsListViewModel: CouponsListViewModel = viewModel(),
) {
    val coupons by CouponsListViewModel.content.collectAsState()
    val isRefreshing by CouponsListViewModel.isRefreshing.collectAsState()

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
        onRefresh = { CouponsListViewModel.refresh() },
        clipIndicatorToPadding = false,
    ) {
        if (coupons.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(text = "No items")
            }
        } else {
            LazyColumn(
                contentPadding = PaddingValues(top = 8.dp, bottom = 80.dp),
            ) {
                items(
                    items = coupons,
                    key = { coupon -> coupon.id.toKey() },
                ) { coupon ->
                    CouponItem(
                        coupon = coupon,
                        modifier = Modifier
                            .padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
                        onClick = {},
                    )
                }
            }
        }
    }

}

