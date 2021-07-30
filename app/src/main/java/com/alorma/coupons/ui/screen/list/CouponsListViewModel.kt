package com.alorma.coupons.ui.screen.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alorma.coupons.domain.id.CouponId
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CouponsListViewModel : ViewModel() {

    private var currentPage: Int = 0
    private var loadedCoupons: List<CouponItemViewModel> = emptyList()

    private val _isRefreshing: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> = _isRefreshing

    private val _content: MutableStateFlow<List<CouponItemViewModel>> =
        MutableStateFlow(emptyList())
    val content: StateFlow<List<CouponItemViewModel>> = _content

    init {
        refresh()
    }

    private suspend fun loadItems() {
        delay(300)
        _content.value = loadedCoupons
    }

    fun refresh() {
        currentPage = 0
        loadedCoupons = listOf(
            CouponItemViewModel(
                id = CouponId("0"),
                message = "Por compras superiores a 15€",
                startDate = "22/07/2021",
                expireDate = "20/08/2021",
                value = "3",
                type = CouponType.DISCOUNT,
                expanded = false,
            ),
            CouponItemViewModel(
                id = CouponId("1"),
                message = "Por compras superiores a 15€",
                startDate = "22/07/2021",
                expireDate = "20/08/2021",
                value = "3",
                type = CouponType.PERCENTAGE,
                expanded = false,
            ),
            CouponItemViewModel(
                id = CouponId("2"),
                message = "Por compras superiores a 15€",
                startDate = "22/07/2021",
                expireDate = "20/08/2021",
                value = "3",
                type = CouponType.DISCOUNT,
                expanded = true,
            ),
            CouponItemViewModel(
                id = CouponId("3"),
                message = "Por compras superiores a 15€",
                startDate = "22/07/2021",
                expireDate = "20/08/2021",
                value = "3",
                type = CouponType.PERCENTAGE,
                expanded = true,
            ),
        )
        viewModelScope.launch {
            _isRefreshing.value = true
            loadItems()
            _isRefreshing.value = false
        }
    }

    suspend fun changeStatus(coupon: CouponItemViewModel, newStatus: Boolean) {
        loadedCoupons = loadedCoupons.map { item ->
            if (item.id != coupon.id) {
                item
            } else {
                coupon.copy(expanded = newStatus)
            }
        }
        _content.value = loadedCoupons
    }
}