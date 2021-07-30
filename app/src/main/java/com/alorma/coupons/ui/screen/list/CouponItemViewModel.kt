package com.alorma.coupons.ui.screen.list

import com.alorma.coupons.domain.id.CouponId

data class CouponItemViewModel(
    val id: CouponId,
    val message: String,
    val startDate: String,
    val expireDate: String,
    val value: String,
    val type: CouponType,
    val expanded: Boolean,
)

enum class CouponType {
    DISCOUNT,
    PERCENTAGE,
}
