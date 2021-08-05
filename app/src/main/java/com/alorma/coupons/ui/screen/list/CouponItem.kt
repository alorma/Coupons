package com.alorma.coupons.ui.screen.list

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.text
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.alorma.coupons.domain.id.CouponId
import com.alorma.coupons.ui.theme.CouponsTheme

@ExperimentalAnimationApi
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CouponItem(
    coupon: CouponItemViewModel,
    modifier: Modifier = Modifier,
    onChangeExpandStatus: (CouponItemViewModel, Boolean) -> Unit = { _, _ -> },
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        onClick = {
            if (!coupon.expanded) {
                onChangeExpandStatus(coupon, true)
            }
        },
        elevation = if (coupon.expanded) {
            4.dp
        } else {
            1.dp
        }
    ) {
        if (coupon.expanded) {
            CouponItemExpanded(
                coupon = coupon,
                onChangeStatus = { onChangeExpandStatus(coupon, false) }
            )
        } else {
            CouponItemCollapsed(
                coupon = coupon,
                onChangeStatus = { onChangeExpandStatus(coupon, true) }
            )
        }
    }
}

@Composable
internal fun CouponItemExpanded(
    coupon: CouponItemViewModel,
    onChangeStatus: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
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
            Spacer(modifier = Modifier.height(12.dp))
            CouponDates(
                modifier = Modifier.fillMaxWidth(),
                coupon = coupon,
            )
        }

        CouponChangeExpandStatus(
            modifier = Modifier.Companion.align(alignment = Alignment.TopEnd),
            icon = Icons.Default.KeyboardArrowUp,
            onClick = onChangeStatus,
        )
    }
}

@Composable
internal fun CouponDates(
    modifier: Modifier = Modifier,
    coupon: CouponItemViewModel,
) {
    CouponDate(
        modifier = modifier,
        date = coupon.startDate,
        imageVector = Icons.Default.CalendarToday,
        contentDescription = "Start date",
    )
    Spacer(modifier = Modifier.height(8.dp))
    CouponDate(
        modifier = modifier,
        date = coupon.expireDate,
        imageVector = Icons.Default.CalendarToday,
        contentDescription = "Expire date",
    )
}

@Composable
internal fun CouponDate(
    modifier: Modifier = Modifier,
    date: String,
    imageVector: ImageVector,
    contentDescription: String,
) {
    Row(
        modifier = modifier.semantics(mergeDescendants = true) {
            text = AnnotatedString("$contentDescription $date")
        },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.disabled) {
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = imageVector,
                contentDescription = null,
            )
        }
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = date)
    }
}

@Composable
internal fun CouponItemCollapsed(
    coupon: CouponItemViewModel,
    onChangeStatus: () -> Unit,
) {
    Box {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            CouponDate(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 2.dp),
                date = coupon.expireDate,
                imageVector = Icons.Default.CalendarToday,
                contentDescription = "Expire date",
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                CouponDiscount(
                    value = coupon.value,
                    type = coupon.type,
                    style = LocalTextStyle.current.copy(),
                )
                Spacer(modifier = Modifier.width(8.dp))
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    CouponMessage(modifier = Modifier.weight(1f), text = coupon.message)
                }
            }
        }

        CouponChangeExpandStatus(
            modifier = Modifier.Companion.align(alignment = Alignment.TopEnd),
            icon = Icons.Default.KeyboardArrowDown,
            onClick = onChangeStatus,
        )
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

@Composable
internal fun CouponChangeExpandStatus(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    onClick: () -> Unit,
) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        Icon(imageVector = icon, contentDescription = "Collapse")
    }
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

@OptIn(ExperimentalAnimationApi::class)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_NO)
@Composable
fun CouponItemPreviewLight(
    @PreviewParameter(provider = CouponsPreviewProvider::class) coupon: CouponItemViewModel,
) {
    CouponsTheme(darkTheme = false) {
        CouponItem(
            coupon = coupon,
        )
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun CouponItemPreviewDark(
    @PreviewParameter(provider = CouponsPreviewProvider::class) coupon: CouponItemViewModel,
) {
    CouponsTheme(darkTheme = true) {
        CouponItem(
            coupon = coupon,
        )
    }
}