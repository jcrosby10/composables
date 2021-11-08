package com.huntergaming.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy

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
        confirmButton = {
            HunterGamingButton(
                onClick = {},
                text = R.string.test
            )
        },
        dismissButton = {
            HunterGamingButton(
                onClick = {},
                text = R.string.test
            )
        },
        title = { HunterGamingTitleText(text = R.string.test) },
        text = { HunterGamingBodyText(text = R.string.test) }
    )
}

@Preview(showBackground = true)
@Composable
private fun HunterGamingCircularProgressIndicatorPreview() {
    HunterGamingCircularProgressIndicator()
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

        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.onPrimary,
            disabledBackgroundColor = MaterialTheme.colors.error,
            disabledContentColor = MaterialTheme.colors.onError
        ),

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
    confirmButton: @Composable () -> Unit,
    dismissButton: @Composable (() -> Unit)? = null,
    title: @Composable (() -> Unit),
    text: @Composable (() -> Unit),
    dismissOnBackPress: Boolean = true,
    dismissOnClickOutside: Boolean = true
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        confirmButton = confirmButton,
        dismissButton = dismissButton,
        title = title,
        text = text,
        backgroundColor = MaterialTheme.colors.primaryVariant,
        properties = DialogProperties(
            dismissOnBackPress = dismissOnBackPress,
            dismissOnClickOutside = dismissOnClickOutside,
            securePolicy = SecureFlagPolicy.Inherit,
        ),
        modifier = modifier
    )
}

@Composable
fun HunterGamingCircularProgressIndicator(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        color = MaterialTheme.colors.secondary,
        modifier = modifier
    )
}

@Composable
fun HunterGamingColumn(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center,
        content = content
    )
}

@Composable
fun HunterGamingRow(
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = horizontalArrangement,
        content = content
    )
}