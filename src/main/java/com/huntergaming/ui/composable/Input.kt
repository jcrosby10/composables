package com.huntergaming.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.huntergaming.ui.R

// composables

@Composable
fun HunterGamingFieldRow(
    modifier: Modifier = Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    fieldNameString: Int,
    hintString: Int,
    isPassword: Boolean = false,
    isError: MutableState<Boolean> = mutableStateOf(false),
    onValueChanged: (TextFieldValue) -> Unit,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    label: (@Composable () -> Unit)? = null,
    textState: MutableState<TextFieldValue>
) {

    Box(modifier = Modifier
        .padding(dimensionResource(id = R.dimen.padding_small)),
        contentAlignment = Alignment.CenterEnd
    ) {

        val transformationState = remember { mutableStateOf(isPassword) }

        Column(
            modifier = modifier,
            horizontalAlignment = horizontalAlignment
        ) {

            HunterGamingSmallCaptionText(
                text = fieldNameString,
                modifier = Modifier
                    .padding(bottom = dimensionResource(id = R.dimen.padding_small))
            )

            TextField(
                value = textState.value,
                label = label,

                onValueChange = {
                    textState.value = it
                    onValueChanged(it)
                },

                modifier = Modifier
                    .requiredWidth(250.dp)
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
        }

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
    onSelect: ((Int) -> Unit)
) {

    val (selectedOption, onOptionSelected) = remember { mutableStateOf(images[selectedIndex] ) }

    Row(
        modifier = modifier
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
                        onSelect(selectedOption)
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

@Composable
fun HunterGamingSettingsRow(
    modifier: Modifier = Modifier,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Center,
    settingName: Int,
    slider: MutableState<Float>,
    checkbox: MutableState<Boolean>,
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

        Checkbox(

            checked = checkbox.value,

            onCheckedChange = {
                checkbox.value = !checkbox.value
                onCheckChange(checkbox.value)
            }
        )

        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.spacer_size)))

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
    selectedIndex: Int,
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
            selectedIndex = selectedIndex
        )
    }
}

// previews

@Preview(showBackground = true)
@Composable
private fun RadioButtonRowPreview() {

    HunterGamingRadioButtonRow(
        rowText = R.string.test,
        radioButtonNames = listOf(stringResource(id = R.string.test)),
        onSelect = {},
        selectedIndex = 0
    )
}

@Preview(showBackground = true)
@Composable
private fun SettingsRowPreview() {

    HunterGamingSettingsRow(
        settingName = R.string.test,
        onSliderChange = {},
        onCheckChange = {},
        slider = remember { mutableStateOf(.8f) },
        checkbox = remember { mutableStateOf(true) }
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

@Preview(showBackground = true)
@Composable
private fun HunterGamingHorizontalSliderPreview() {

    HunterGamingHorizontalSlider(
        initialValue = .8f,
        onValueChange = {  },
    )
}