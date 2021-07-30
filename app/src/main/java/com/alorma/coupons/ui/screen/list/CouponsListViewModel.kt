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
        loadedCoupons = List(2) { item ->
            CouponItemViewModel(
                id = CouponId("$item"),
                title = "Por compras superiores a 15€",
                startDate = "22/07/2021",
                expireDate = "20/08/2021",
                value = "3€",
                type = CouponType.DISCOUNT,
            )
        }
        viewModelScope.launch {
            _isRefreshing.value = true
            loadItems()
            _isRefreshing.value = false
        }
    }

    companion object {
        private const val ITEMS_PER_PAGE = 10
    }

}