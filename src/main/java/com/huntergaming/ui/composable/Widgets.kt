package com.huntergaming.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.twotone.Cancel
import androidx.compose.material.icons.twotone.Done
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import com.huntergaming.ui.R

// COMPOSABLES

@Composable
fun HunterGamingButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isEnabled: Boolean = true,
    icon: ImageVector,
    contentDescription: Int
) {

    Button(
        enabled = isEnabled,
        onClick = onClick,
        modifier = modifier
    ) {

        Icon(imageVector = icon, contentDescription = stringResource(id = contentDescription))
    }
}

@Composable
fun HunterGamingTextButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    isEnabled: Boolean = true,
    text: Int
) {

    Button(
        enabled = isEnabled,
        onClick = onClick,
        modifier = modifier
    ) {

        HunterGamingBodyText(text = text)
    }
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
    backgroundImage: Int,
    state: MutableState<Boolean>
) {

    if (state.value) {
        Box(
            contentAlignment = Alignment.Center
        ) {

            Image(
                painter = painterResource(id = backgroundImage),
                contentDescription = stringResource(id = R.string.content_description_not_needed),
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(dimensionResource(id = R.dimen.dialog_image_width))
                    .height(dimensionResource(id = R.dimen.dialog_image_height))
            )

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

                        icon = if (isSystemInDarkTheme()) Icons.TwoTone.Done else Icons.Outlined.Done,
                        contentDescription = R.string.content_description_accept
                    )
                },

                dismissButton = {
                    if (onDismiss != null) {
                        HunterGamingButton(

                            onClick = {
                                state.value = false
                                onDismiss()
                            },

                            icon = if (isSystemInDarkTheme()) Icons.TwoTone.Cancel else Icons.Outlined.Cancel,
                            contentDescription = R.string.content_description_cancel
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
                },

                backgroundColor = Color.Transparent
            )
        }
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
    backgroundImage: Int,
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
        backgroundImage = backgroundImage,
        state = state
    )
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
private fun TextButtonPreview() {
    HunterGamingTextButton(onClick = { /*TODO*/ }, text = R.string.test)
}

@Preview(showBackground = true)
@Composable
private fun BackgroundImagePreview() {
    HunterGamingBackgroundImage(image = R.drawable.ic_lock_open_24)
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
private fun HunterGamingSmallBodyTextPreview() {
    HunterGamingSmallCaptionText(text = R.string.test)
}

@Preview(showBackground = true)
@Composable
private fun ButtonPreview() {
    HunterGamingButton(onClick = {  }, icon = Icons.TwoTone.Done, contentDescription = R.string.content_description_not_needed)
}

@Preview(showBackground = true)
@Composable
private fun AlertDialogPreview() {
    HunterGamingAlertDialog(
        onConfirm = {},
        onDismiss = {},
        title = R.string.test,
        text = R.string.test,
        state = remember { mutableStateOf(true) },
        backgroundImage = R.drawable.ic_lock_open_24
    )
}
