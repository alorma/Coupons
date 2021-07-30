package com.alorma.coupons.ui.screen.list

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.alorma.coupons.domain.id.CouponId
import com.alorma.coupons.ui.theme.CouponsTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CouponItem(
    coupon: CouponItemViewModel,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            Text(text = coupon.title)
        }
    }
}

class CouponsPreviewProvider : CollectionPreviewParameterProvider<CouponItemViewModel>(
    listOf(
        CouponItemViewModel(
            id = CouponId("0"),
            title = "Por compras superiores a 15€",
            startDate = "22/07/2021",
            expireDate = "20/08/2021",
            value = "3",
            type = CouponType.DISCOUNT,
        ),
        CouponItemViewModel(
            id = CouponId("1"),
            title = "Por compras superiores a 15€",
            startDate = "22/07/2021",
            expireDate = "20/08/2021",
            value = "3",
            type = CouponType.DISCOUNT,
        ),
    )
)

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun CouponItemPreviewLight(
    @PreviewParameter(provider = CouponsPreviewProvider::class) coupon: CouponItemViewModel,
) {
    CouponsTheme(darkTheme = false) {
        CouponItem(
            coupon = coupon,
            onClick = {},
        )
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun CouponItemPreviewDark(
    @PreviewParameter(provider = CouponsPreviewProvider::class) coupon: CouponItemViewModel,
) {
    CouponsTheme(darkTheme = true) {
        CouponItem(
            coupon = coupon,
            onClick = {},
        )
    }
}