package com.alorma.coupons

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.alorma.coupons.domain.id.CouponId
import com.alorma.coupons.ui.screen.list.CouponItem
import com.alorma.coupons.ui.screen.list.CouponItemViewModel
import com.alorma.coupons.ui.screen.list.CouponType
import com.alorma.coupons.ui.theme.CouponsTheme
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalAnimationApi::class)
class CouponTicketTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun collapsedCouponTicket() {
        composeTestRule.setContent {
            CouponsTheme {
                CouponItem(coupon = COUPON_PERCENTAGE_COLLAPSED)
            }
        }

        composeTestRule.onNodeWithContentDescription("Start date $START_DATE").assertIsDisplayed()
        composeTestRule.onNodeWithText(PERCENTAGE_DISCOUNT).assertIsDisplayed()
        composeTestRule.onNodeWithText(START_DATE).assertIsDisplayed()
        composeTestRule.onNodeWithText(EXPIRE_DATE).assertDoesNotExist()
    }

    companion object {
        private const val PERCENTAGE_DISCOUNT = "3%"
        private const val START_DATE = "22/07/2021"
        private const val EXPIRE_DATE = "20/08/2021"

        private val COUPON_PERCENTAGE_COLLAPSED = CouponItemViewModel(
            id = CouponId("1"),
            message = "Por compras superiores a 15€",
            startDate = START_DATE,
            expireDate = EXPIRE_DATE,
            value = "3",
            type = CouponType.PERCENTAGE,
            expanded = true,
        )
    }

}