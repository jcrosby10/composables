package com.huntergaming.ui.composable

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.SportsEsports
import androidx.compose.material.icons.outlined.VolumeUp
import androidx.compose.material.icons.twotone.Security
import androidx.compose.material.icons.twotone.SportsEsports
import androidx.compose.material.icons.twotone.VolumeUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.huntergaming.ui.R
import kotlinx.coroutines.launch

// composables

@ExperimentalPagerApi
@Composable
fun HunterGamingTabs(
    tabIcons: List<ImageVector>,
    contentDescriptions: List<Int>,
    pagerState: PagerState,
    vararg tabScreens: @Composable () -> Unit
) {

    val tabIndex = pagerState.currentPage

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TabRow(
            selectedTabIndex = tabIndex
        ) {

            val coroutineScope = rememberCoroutineScope()
            tabIcons.forEachIndexed { index, icon ->

                Tab(
                    selected = tabIndex == index,

                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },

                    icon = {
                        Icon(imageVector = icon, contentDescription = stringResource(id = contentDescriptions[index]))
                    }
                )
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { index ->
            tabScreens[index]()
        }
    }
}

// previews

@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
private fun HunterGamingTabsPreview() {

    HunterGamingTabs(
        tabIcons = listOf(
            if (isSystemInDarkTheme()) Icons.TwoTone.VolumeUp else Icons.Outlined.VolumeUp,
            if (isSystemInDarkTheme()) Icons.TwoTone.SportsEsports else Icons.Outlined.SportsEsports,
            if (isSystemInDarkTheme()) Icons.TwoTone.Security else Icons.Outlined.Security
        ),

        contentDescriptions = listOf(
            R.string.test,
            R.string.test,
            R.string.test
        ),

        pagerState = rememberPagerState(
            pageCount = 3,
            initialOffscreenLimit = 2,
            infiniteLoop = true,
            initialPage = 0,
        ),
        {  },  {  }, {  }
    )
}