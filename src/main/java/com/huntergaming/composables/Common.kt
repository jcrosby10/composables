package com.huntergaming.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Slider
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
private fun HunterGamingFieldRowPreview() {
    val text = remember { mutableStateOf(TextFieldValue(text =  "test")) }
    HunterGamingFieldRow(
        fieldNameString = R.string.test,
        hintString = R.string.test,
        onValueChanged = {},
        textState = text
    )
}

@Preview(showBackground = true)
@Composable
private fun HunterGamingHorizontalRadioButtonPreview() {
    HunterGamingHorizontalRadioButton(
        texts = listOf("A", "B"),
        selectedIndex = 0,
        onSelect = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun HunterGamingHorizontalSliderPreview() {
    HunterGamingHorizontalSlider(
        initialValue = .8f,
        onValueChange = {  },
    )
}

@Preview(showBackground = true)
@Composable
private fun HunterGamingHorizontalImageRadioButtonPreview() {
    HunterGamingHorizontalImageRadioButton(
        images = listOf(R.drawable.card_back_red),
        contentDescriptions = listOf("test content description"),
        selectedIndex = 0,
        onSelect = {}
    )
}

@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
private fun HunterGamingTabsPreview() {
    val tabIcons = remember {
        mutableStateOf(
            listOf(
                R.drawable.card_back_red
            )
        )
    }

    val tabTitles = remember {
        mutableStateOf(
            listOf(
                R.string.test
            )
        )
    }

    HunterGamingTabs(
        tabIcons = tabIcons.value,
        tabTitles = tabTitles.value,
        pagerState = rememberPagerState(
            pageCount = tabIcons.value.size,
            initialOffscreenLimit = 2,
            infiniteLoop = true,
            initialPage = 0,
        ),
        {}
    )
}

@Composable
fun HunterGamingFieldRow(
    fieldNameString: Int,
    hintString: Int,
    hideText: Boolean = false,
    isError: Boolean = false,
    onValueChanged: (TextFieldValue) -> Unit,
    textState: MutableState<TextFieldValue>
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(dimensionResource(id = R.dimen.padding_small))) {

        Text(
            text = stringResource(id = fieldNameString),
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .padding(end = dimensionResource(id = R.dimen.padding_small), top = dimensionResource(id = R.dimen.padding_large))
                .defaultMinSize(minWidth = 70.dp),
            textAlign = TextAlign.Center
        )

        TextField(
            value = textState.value,
            onValueChange = onValueChanged,
            colors = TextFieldDefaults.textFieldColors(
                textColor = MaterialTheme.colors.onPrimary,
                backgroundColor = MaterialTheme.colors.background,
                errorIndicatorColor = MaterialTheme.colors.error
            ),

            modifier = Modifier
                .fillMaxWidth()
                .padding(start = dimensionResource(id = R.dimen.padding_small)),

            placeholder = {
                Text(
                    text = stringResource(id = hintString),
                    style = MaterialTheme.typography.body2
                )
            },

            visualTransformation = if (hideText) PasswordVisualTransformation() else VisualTransformation.None,
            isError = isError
        )
    }
}

@Composable
fun HunterGamingHorizontalRadioButton(
    modifier: Modifier = Modifier,
    texts: List<String>,
    selectedIndex: Int,
    onSelect: (() -> Unit)
) {
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(texts[selectedIndex] ) }

    Row(
        modifier = modifier
    ) {
        texts.forEach { text ->
            Row(
                Modifier
                    .padding(
                        start = dimensionResource(R.dimen.padding_large)
                    )
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                        }
                    )
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = {
                        onOptionSelected(text)
                        onSelect()
                    }
                )

                HunterGamingBodyText(
                    text = text
                )
            }
        }
    }
}

@Composable
fun HunterGamingHorizontalImageRadioButton(
    modifier: Modifier = Modifier,
    images: List<Int>,
    contentDescriptions: List<String>,
    selectedIndex: Int,
    onSelect: (() -> Unit)
) {
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(images[selectedIndex] ) }

    Row(
        modifier = modifier
    ) {
        images.forEachIndexed { index, value ->
            Row(
                modifier= Modifier
                    .padding(
                        start = dimensionResource(R.dimen.padding_large)
                    )
                    .selectable(
                        selected = (value == selectedOption),
                        onClick = {
                            onOptionSelected(value)
                        }
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (value == selectedOption),
                    onClick = {
                        onOptionSelected(value)
                        onSelect()
                    }
                )

                Image(
                    painter = painterResource(value),
                    modifier = Modifier.fillMaxHeight(.4f),
                    contentDescription = contentDescriptions[index]
                )
            }
        }
    }
}

@Composable
fun HunterGamingHorizontalSlider(
    modifier: Modifier = Modifier,
    onValueChange: (Float) -> Unit,
    initialValue: Float
) {
    val sliderPosition = remember { mutableStateOf(initialValue) }

    ConstraintLayout(
        modifier = modifier
    ) {
        val (textConstraint, sliderConstraint) = createRefs()
        HunterGamingBodyText(
            modifier = Modifier.constrainAs(textConstraint) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(sliderConstraint.top)
            },
            text = "${(sliderPosition.value * 100f).toInt()}%"
        )

        Slider(
            modifier = Modifier
                .constrainAs(sliderConstraint) {
                    top.linkTo(textConstraint.bottom)
                },
            value = sliderPosition.value,
            onValueChange = {
                sliderPosition.value = it
                onValueChange(it)
            }
        )
    }
}

@ExperimentalPagerApi
@Composable
fun HunterGamingTabs(
    tabIcons: List<Int>,
    tabTitles: List<Int>,
    pagerState: PagerState,
    vararg tabScreens: @Composable () -> Unit
) {
    val tabIndex = pagerState.currentPage

    HunterGamingColumn(
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
                    text = {
                        HunterGamingBodyText(
                            text = tabTitles[index]
                        )
                    },
                    icon = {
                        Icon(
                            modifier = Modifier
                                .requiredWidth(
                                    width = dimensionResource(
                                        id = R.dimen.tab_icon_width
                                    )
                                )
                                .requiredHeight(
                                    height = dimensionResource(
                                        id = R.dimen.tab_icon_height
                                    )
                                ),
                            painter = painterResource(
                                id = icon
                            ),
                            contentDescription = ""
                        )
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