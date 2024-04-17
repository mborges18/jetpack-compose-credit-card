package com.creditcard.ui.screens.authenticator

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabViewPager(
    modifier: Modifier = Modifier,
    titles: List<String>,
    contentView: @Composable (Int) -> Unit,
) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { titles.size })
    val selectedTabIndex = remember { derivedStateOf { pagerState.currentPage } }

    Scaffold(
        topBar = {
            TabRow(
                containerColor = MaterialTheme.colorScheme.primary,
                selectedTabIndex = selectedTabIndex.value,
                modifier = modifier
                    .padding(horizontal = 10.dp, vertical = 10.dp)
                    .height(60.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10))
                    .shadow(elevation = 20.dp, shape = RoundedCornerShape(10))
                    .border(BorderStroke(0.5.dp, Color.LightGray))
                    .size(50.dp),
                divider = {},
                indicator = {},
            ) {
                titles.forEachIndexed { index, title ->
                    val isSelected = selectedTabIndex.value == index
                    Tab(
                        text = { Text(
                            text = title,
                            color = if(isSelected) { Color.White } else { Color.Black }
                        ) },
                        modifier = if(isSelected) {
                            modifier.background(Color.Transparent).clip(RoundedCornerShape(10)).fillMaxWidth()
                        } else {
                            modifier
                                .background(Color.White)
                                .clip(RoundedCornerShape(10))
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .height(60.dp)
                               },
                        selected = selectedTabIndex.value == index,
                        selectedContentColor = MaterialTheme.colorScheme.primary,
                        unselectedContentColor = Color.White,
                        onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        }
                    )
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