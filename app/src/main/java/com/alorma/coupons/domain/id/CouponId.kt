package com.alorma.coupons.domain.id

@JvmInline
value class CouponId(val value: String) {
    init {
        require(value.isNotEmpty())
    }
}

fun CouponId.toKey() = "CouponId=$value"