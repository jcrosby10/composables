package com.huntergaming.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Slider
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material.icons.outlined.SportsEsports
import androidx.compose.material.icons.outlined.VolumeUp
import androidx.compose.material.icons.twotone.Security
import androidx.compose.material.icons.twotone.SportsEsports
import androidx.compose.material.icons.twotone.VolumeUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
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
import com.huntergaming.ui.R
import kotlinx.coroutines.launch

// COMPOSABLES

@Composable
fun HunterGamingFieldRow(
    fieldNameString: Int,
    hintString: Int,
    isPassword: Boolean = false,
    isError: MutableState<Boolean> = mutableStateOf(false),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    onValueChanged: (TextFieldValue) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    label: (@Composable () -> Unit)? = null,
    textState: MutableState<TextFieldValue>
) {

    Row(modifier = Modifier
        .padding(dimensionResource(id = R.dimen.padding_small)),
        horizontalArrangement = horizontalArrangement
    ) {

        val transformationState = remember { mutableStateOf(isPassword) }

        Text(
            text = stringResource(id = fieldNameString),
            style = MaterialTheme.typography.body2,
            modifier = Modifier
                .padding(end = dimensionResource(id = R.dimen.padding_small), top = dimensionResource(id = R.dimen.padding_large))
                .defaultMinSize(minWidth = 70.dp),
            textAlign = TextAlign.Center
        )

        TextField(
            value = textState.value,
            label = label,

            onValueChange = {
                textState.value = it
                onValueChanged(it)
            },

            modifier = Modifier
                .padding(start = dimensionResource(id = R.dimen.padding_small)),

            placeholder = {
                Text(
                    text = stringResource(id = hintString),
                    style = MaterialTheme.typography.body2
                )
            },

            keyboardOptions = keyboardOptions,
            visualTransformation = if (transformationState.value) PasswordVisualTransformation() else VisualTransformation.None,
            isError = isError.value
        )

        if (isPassword) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(
                        id = if (transformationState.value) R.drawable.ic_lock_24 else R.drawable.ic_lock_open_24),
                    contentDescription = stringResource(id = R.string.content_description_show_hide_password)
                )
                Button(
                    onClick = { transformationState.value = !transformationState.value },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Transparent)
                ){}
            }
        }
    }
}

@Composable
fun HunterGamingHorizontalRadioButton(
    modifier: Modifier = Modifier,
    texts: List<String>,
    selectedIndex: Int,
    onSelect: ((String) -> Unit)
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
                        onSelect(selectedOption)
                    }
                )

                HunterGamingBodyText(text = text)
            }
        }
    }
}

@Composable
fun HunterGamingHorizontalImageRadioButton(
    modifier: Modifier = Modifier,
    images: List<Int>,
    imageWidth: Int,
    imageHeight: Int,
    contentDescriptions: List<String>,
    selectedIndex: Int,
    onSelect: (() -> Unit)
) {

    val (selectedOption, onOptionSelected) = remember { mutableStateOf(images[selectedIndex] ) }

    Row(
        modifier = modifier
//            .fillMaxWidth()
    ) {
        images.forEachIndexed { index, imageId ->

            Row(
                modifier= Modifier
                    .padding(
                        start = dimensionResource(R.dimen.padding_large)
                    )
                    .selectable(
                        selected = (imageId == selectedOption),
                        onClick = {
                            onOptionSelected(imageId)
                        }
                    ),

                verticalAlignment = Alignment.CenterVertically
            ) {

                RadioButton(
                    selected = (imageId == selectedOption),
                    onClick = {
                        onOptionSelected(imageId)
                        onSelect()
                    }
                )

                Image(
                    painter = painterResource(imageId),

                    modifier = Modifier
                        .requiredWidth(
                            width = dimensionResource(imageWidth)
                        )
                        .requiredHeight(
                            height = dimensionResource(imageHeight)
                        ),

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

@Composable
fun HunterGamingSettingsRow(
    modifier: Modifier = Modifier,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    settingName: Int,
    onCheckChange: (Boolean) -> Unit,
    onSliderChange: (Float) -> Unit
) {

    Row(
        modifier = modifier,
        verticalAlignment = verticalAlignment,
        horizontalArrangement = horizontalArrangement
    ) {

        HunterGamingTitleText(text = settingName)

        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.spacer_size)))

        val checked = remember { mutableStateOf(true) }
        Checkbox(

            checked = checked.value,

            onCheckedChange = {
                checked.value = !checked.value
                onCheckChange(checked.value)
            }
        )

        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.spacer_size)))

        val slider = remember { mutableStateOf(.8f) }
        HunterGamingHorizontalSlider(

            modifier = Modifier
                .requiredWidth(dimensionResource(R.dimen.width_300))
                .padding(
                    start = dimensionResource(R.dimen.padding_medium),
                    end = dimensionResource(R.dimen.padding_medium)
                ),

            initialValue = slider.value,

            onValueChange = {
                slider.value = it
                onSliderChange(slider.value)
            },
        )
    }
}

@Composable
fun HunterGamingRadioButtonRow(
    modifier: Modifier = Modifier,
    rowText: Int,
    radioButtonNames: List<String>,
    onSelect: ((String) -> Unit)
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {

        HunterGamingTitleText(text = rowText)

        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.spacer_size)))

        HunterGamingHorizontalRadioButton(
            texts = radioButtonNames,
            onSelect = { onSelect(it) },
            selectedIndex = 0
        )
    }
}

// PREVIEWS

@Preview(showBackground = true)
@Composable
private fun RadioButtonRowPreview() {

    HunterGamingRadioButtonRow(
        rowText = R.string.test,
        radioButtonNames = listOf(stringResource(id = R.string.test)),
        onSelect = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun HunterGamingFieldRowPreview() {

    val text = remember { mutableStateOf(TextFieldValue(text =  "test")) }
    HunterGamingFieldRow(
        fieldNameString = R.string.test,
        hintString = R.string.test,
        isPassword = true,
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
        imageWidth = R.dimen.image_radio_width,
        imageHeight = R.dimen.image_radio_height,
        contentDescriptions = listOf("test content description"),
        selectedIndex = 0,
        onSelect = {}
    )
}

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

@Preview(showBackground = true)
@Composable
private fun SettingsRowPreview() {

    HunterGamingSettingsRow(
        settingName = R.string.test,
        onSliderChange = {},
        onCheckChange = {}
    )
}