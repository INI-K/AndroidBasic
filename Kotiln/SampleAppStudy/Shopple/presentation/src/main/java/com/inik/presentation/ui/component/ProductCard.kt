package com.inik.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.inik.domain.model.Category
import com.inik.domain.model.Price
import com.inik.domain.model.Product
import com.inik.domain.model.SalesStatus
import com.inik.domain.model.Shop
import com.inik.presentation.R
import com.inik.presentation.ui.theme.Purple80


@Composable
fun ProductCard(product: Product, onClick: (Product) -> Unit?) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(intrinsicSize = IntrinsicSize.Max)
            .padding(10.dp)
            .shadow(elevation = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start
        ) {
            Image(
                painter = painterResource(id = R.drawable.product_image),
                "description",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )
            Text(
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                text = product.shop.shopName
            )
            Text(
                fontSize = 14.sp,
                text = product.productName
            )
            Price(product)
        }
    }
}

@Composable
private fun Price(product: Product) {
    when (product.price.salesStatus) {
        SalesStatus.ON_SALE -> {
            Text(
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                text = "${product.price.originPrice}원"
            )
        }

        SalesStatus.ON_DISCOUNT -> {
            Text(
                fontSize = 14.sp,
                text = "${product.price.originPrice}원",
                style = TextStyle(textDecoration = TextDecoration.LineThrough)
            )
            Row {
                Text(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Purple80,
                    text = "${product.price.finalPrice}원"
                )
            }
        }

        SalesStatus.SOLD_OUT -> {
            Text(
                fontSize = 18.sp,
                color = Color(0xFF666666),
                text = "판매종료"
            )
        }

        SalesStatus.ON_DISCOUNT -> TODO()
    }
}

@Preview
@Composable
private fun PreviewProductCard() {
    ProductCard(
        product = Product(
            productId = "1",
            productName = "상품이름",
            imagUrl = "",
            price = Price(
                originPrice = 30000,
                finalPrice = 30000,
                salesStatus = SalesStatus.ON_SALE,
            ),
            category = Category.Top,
            shop = Shop(
                shopId = "1",
                shopName = "샵 이름",
                imagUrl = "",
            ),
            isNew = false,
            isFreeShipping = false
        )
    ) {
    }
}
@Preview
@Composable
private fun PreviewProductCardDiscount() {
    ProductCard(
        product = Product(
            productId = "1",
            productName = "상품이름",
            imagUrl = "",
            price = Price(
                originPrice = 30000,
                finalPrice = 20000,
                salesStatus = SalesStatus.ON_DISCOUNT,
            ),
            category = Category.Top,
            shop = Shop(
                shopId = "1",
                shopName = "샵 이름",
                imagUrl = "",
            ),
            isNew = false,
            isFreeShipping = false
        )
    ) {
    }
}

@Preview
@Composable
private fun PreviewProductCardSoldOut() {
    ProductCard(
        product = Product(
            productId = "1",
            productName = "상품이름",
            imagUrl = "",
            price = Price(
                originPrice = 30000,
                finalPrice = 30000,
                salesStatus = SalesStatus.SOLD_OUT,
            ),
            category = Category.Top,
            shop = Shop(
                shopId = "1",
                shopName = "샵 이름",
                imagUrl = "",
            ),
            isNew = false,
            isFreeShipping = false
        )
    ) {
    }
}