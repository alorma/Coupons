package com.alorma.coupons.ui.screen.list

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
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
            if (coupon.expanded) {
                CouponItemExpanded(coupon)
            } else {
                CouponItemCollapsed(coupon)
            }
        }
    }
}

@Composable
internal fun CouponItemExpanded(coupon: CouponItemViewModel) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CouponDiscount(
            value = coupon.value,
            type = coupon.type,
            style = MaterialTheme.typography.h1.copy(fontWeight = FontWeight.ExtraBold),
        )
        CouponMessage(
            text = coupon.message,
            style = MaterialTheme.typography.h5,
        )
    }
}

@Composable
internal fun CouponItemCollapsed(coupon: CouponItemViewModel) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CouponDiscount(value = coupon.value, type = coupon.type)
        Spacer(modifier = Modifier.width(8.dp))
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            CouponMessage(modifier = Modifier.weight(1f), text = coupon.message)
        }
    }
}

@Composable
internal fun CouponDiscount(
    modifier: Modifier = Modifier,
    value: String,
    type: CouponType,
    style: TextStyle = LocalTextStyle.current,
) {
    val text = when (type) {
        CouponType.DISCOUNT -> "-$value€"
        CouponType.PERCENTAGE -> "$value%"
    }
    Text(
        modifier = modifier,
        text = text,
        style = style,
    )
}

@Composable
internal fun CouponMessage(
    modifier: Modifier = Modifier,
    text: String,
    style: TextStyle = LocalTextStyle.current,
) {
    Text(
        modifier = modifier,
        text = text,
        style = style,
    )
}

class CouponsPreviewProvider : CollectionPreviewParameterProvider<CouponItemViewModel>(
    listOf(
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
            id = CouponId("0"),
            message = "Por compras superiores a 15€",
            startDate = "22/07/2021",
            expireDate = "20/08/2021",
            value = "3",
            type = CouponType.DISCOUNT,
            expanded = true,
        ),
        CouponItemViewModel(
            id = CouponId("1"),
            message = "Por compras superiores a 15€",
            startDate = "22/07/2021",
            expireDate = "20/08/2021",
            value = "3",
            type = CouponType.PERCENTAGE,
            expanded = true,
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