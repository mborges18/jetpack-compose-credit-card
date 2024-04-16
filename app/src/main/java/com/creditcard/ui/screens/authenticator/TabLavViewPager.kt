package com.creditcard.ui.screens.authenticator

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.creditcard.ui.theme.Blue40
import kotlinx.coroutines.launch

fun ContentDrawScope.drawWithLayer(block: ContentDrawScope.() -> Unit) {
    with(drawContext.canvas.nativeCanvas) {
        val checkPoint = saveLayer(null, null)
        block()
        restoreToCount(checkPoint)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabLavViewPager(
    modifier: Modifier = Modifier,
    items: List<String>,
    contentView: @Composable (Int) -> Unit,
) {

    var selectedTab by remember { mutableStateOf(0) }
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { items.size })
    val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }

    Scaffold(
        topBar = {
            BoxWithConstraints(
                modifier
                    .padding(8.dp)
                    .height(56.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Blue40)
                    .padding(8.dp)
            ) {
                if (items.isNotEmpty()) {

                    val maxWidth = this.maxWidth
                    val tabWidth = maxWidth / items.size

                    val indicatorOffset by animateDpAsState(
                        targetValue = tabWidth * selectedTab,
                        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing),
                        label = "indicator offset"
                    )

                    // This is for shadow layer matching white background
                    Box(
                        modifier = Modifier
                            .offset(x = indicatorOffset)
                            .shadow(4.dp, RoundedCornerShape(8.dp))
                            .width(tabWidth)
                            .fillMaxHeight()
                    )

                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .drawWithContent {
                            // This is for setting black text while drawing on white background
                            val padding = 8.dp.toPx()
                            drawRoundRect(
                                topLeft = Offset(x = indicatorOffset.toPx() + padding, padding),
                                size = Size(size.width / 2 - padding * 2, size.height - padding * 2),
                                color = Color.Black,
                                cornerRadius = CornerRadius(x = 8.dp.toPx(), y = 8.dp.toPx()),
                            )
                            drawWithLayer {
                                drawContent()
                                // This is white top rounded rectangle
                                drawRoundRect(
                                    topLeft = Offset(x = indicatorOffset.toPx(), 0f),
                                    size = Size(size.width / 2, size.height),
                                    color = Color.White,
                                    cornerRadius = CornerRadius(x = 8.dp.toPx(), y = 8.dp.toPx()),
                                    blendMode = BlendMode.SrcOut
                                )
                            }
                        }
                    ) {
                        items.forEachIndexed { index, text ->
                            val isSelected = selectedTab == index
                            Box(
                                modifier = Modifier
                                    .width(tabWidth)
                                    .fillMaxHeight()
                                    .clickable(
                                        interactionSource = remember {
                                            MutableInteractionSource()
                                        },
                                        indication = null,
                                        onClick = {
                                            selectedTab = index
                                            scope.launch {
                                                pagerState.animateScrollToPage(index)
                                            }
                                        }
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = text,
                                    fontSize = 14.sp,
                                    color = if(isSelected) Color.Black else Color.White
                                )
                            }
                        }
                    }
                }
            }
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HorizontalPager(
                    state = pagerState
                ) {
                    contentView.invoke(selectedTabIndex.value)
                }
            }
        }
    )
}
