package com.alorma.coupons.ui.screen.list

data class CouponItemViewModel(
    val title: String,
    val startDate: String,
    val expireDate: String,
    val value: String,
    val type: CouponType,
)

enum class CouponType {
    DISCOUNT,
    PERCENTAGE,
}
