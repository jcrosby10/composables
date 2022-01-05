package com.huntergaming.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import com.huntergaming.ui.R

@Composable
fun HunterGamingButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isEnabled: Boolean = true,
    text: Int
) {
    Button(
        enabled = isEnabled,
        onClick = onClick,
        modifier = modifier
    ){
        Text(
            text = stringResource(id = text),
            style = MaterialTheme.typography.button
        )
    }
}

@Composable
fun HunterGamingBodyText(
    text: Int,
    modifier: Modifier = Modifier
) {
    HunterGamingBodyText(
        text = stringResource(text),
        modifier = modifier
    )
}

@Composable
fun HunterGamingSmallCaptionText(
    text: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = text),
        style = MaterialTheme.typography.caption,

        modifier = modifier.padding(
            start = dimensionResource(R.dimen.padding_small),
            end = dimensionResource(R.dimen.padding_small)
        ),

        textAlign = TextAlign.Center
    )
}

@Composable
fun HunterGamingBodyText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.body1,

        modifier = modifier.padding(
            start = dimensionResource(R.dimen.padding_small),
            end = dimensionResource(R.dimen.padding_small)
        ),

        textAlign = TextAlign.Center
    )
}

@Composable
fun HunterGamingHeaderText(
    text: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = text),
        style = MaterialTheme.typography.h1,

        modifier = modifier.padding(
            start = dimensionResource(R.dimen.padding_small),
            end = dimensionResource(R.dimen.padding_small)
        ),

        textAlign = TextAlign.Center
    )
}

@Composable
fun HunterGamingTitleText(
    text: Int,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(id = text),
        style = MaterialTheme.typography.h4,

        modifier = modifier.padding(
            start = dimensionResource(R.dimen.padding_small),
            end = dimensionResource(R.dimen.padding_small)
        ),

        textAlign = TextAlign.Center
    )
}

@Composable
fun HunterGamingAlertDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {  },
    onConfirm: () -> Unit,
    onDismiss: (() -> Unit)? = null,
    title: Int,
    text: String,
    dismissOnBackPress: Boolean = true,
    dismissOnClickOutside: Boolean = true,
    state: MutableState<Boolean>
) {
    if (state.value) {
        AlertDialog(
            modifier = modifier,
            properties = DialogProperties(
                dismissOnBackPress = dismissOnBackPress,
                dismissOnClickOutside = dismissOnClickOutside,
                securePolicy = SecureFlagPolicy.Inherit,
            ),
            onDismissRequest = onDismissRequest,
            confirmButton = {
                HunterGamingButton(
                    onClick = {
                        state.value = false
                        onConfirm()
                    },
                    text = R.string.button_yes
                )
            },
            dismissButton = {
                if (onDismiss != null) {
                    HunterGamingButton(
                        onClick = {
                            state.value = false
                            onDismiss()
                        },
                        text = R.string.button_no
                    )
                }
            },
            title = {
                HunterGamingTitleText(
                    text = title
                )
            },
            text = {
                HunterGamingBodyText(
                    text = text
                )
            }
        )
    }
}

@Composable
fun HunterGamingAlertDialog(
    modifier: Modifier = Modifier,
    onDismissRequest: () -> Unit = {  },
    onConfirm: () -> Unit,
    onDismiss: (() -> Unit)? = null,
    title: Int,
    text: Int,
    dismissOnBackPress: Boolean = true,
    dismissOnClickOutside: Boolean = true,
    state: MutableState<Boolean>
) {
    HunterGamingAlertDialog(
        modifier = modifier,
        onConfirm = onConfirm,
        onDismiss = onDismiss,
        onDismissRequest = onDismissRequest,
        dismissOnBackPress = dismissOnBackPress,
        dismissOnClickOutside = dismissOnClickOutside,
        title = title,
        text = stringResource(id = text),
        state = state
    )
}

// PREVIEWS

@Composable
fun HunterGamingColumn(
    modifier: Modifier = Modifier,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = horizontalAlignment,
        verticalArrangement = Arrangement.Center,
        content = content
    )
}

@Composable
fun HunterGamingRow(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalAlignment: Alignment.Vertical = Alignment.CenterVertically,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = verticalAlignment,
        horizontalArrangement = horizontalArrangement,
        content = content
    )
}

@Composable
fun HunterGamingBackgroundImage(image: Int) {
    Image(
        modifier = Modifier
            .fillMaxSize(),
        painter = painterResource(id = image),
        contentDescription = stringResource(id = R.string.content_description_not_needed),
        contentScale = ContentScale.FillBounds
    )
}

// PREVIEWS

@Preview(showBackground = true)
@Composable
private fun HunterGamingButtonPreview() {
    HunterGamingButton(onClick = {  }, text = R.string.test)
}

@Preview(showBackground = true)
@Composable
private fun HunterGamingBodyTextPreview() {
    HunterGamingBodyText(text = R.string.test)
}

@Preview(showBackground = true)
@Composable
private fun HunterGamingHeaderTextPreview() {
    HunterGamingHeaderText(text = R.string.test)
}

@Preview(showBackground = true)
@Composable
private fun HunterGamingTitleTextPreview() {
    HunterGamingTitleText(text = R.string.test)
}

@Preview(showBackground = true)
@Composable
private fun HunterGamingAlertDialogPreview() {
    HunterGamingAlertDialog(
        onConfirm = {},
        onDismiss = {},
        title = R.string.test,
        text = R.string.test,
        state = remember { mutableStateOf(false) }
    )
}

@Preview(showBackground = true)
@Composable
private fun HunterGamingColumnPreview() {
    HunterGamingColumn {
        HunterGamingBodyText(text = R.string.test)
        HunterGamingBodyText(text = R.string.test)
        HunterGamingBodyText(text = R.string.test)
    }
}

@Preview(showBackground = true)
@Composable
private fun HunterGamingRowPreview() {
    HunterGamingRow {
        HunterGamingBodyText(text = R.string.test)
        HunterGamingBodyText(text = R.string.test)
        HunterGamingBodyText(text = R.string.test)
    }
}

@Preview(showBackground = true)
@Composable
private fun HunterGamingSmallBodyTextPreview() {
    HunterGamingSmallCaptionText(text = R.string.test)
}

@Preview(showBackground = true)
@Composable
private fun HunterGamingBackgroundImagePreview() {
    HunterGamingBackgroundImage(image = R.drawable.card_back_red)
}