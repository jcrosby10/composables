package com.huntergaming.ui.composable

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.material.AlertDialog
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cancel
import androidx.compose.material.icons.outlined.Done
import androidx.compose.material.icons.twotone.Cancel
import androidx.compose.material.icons.twotone.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import com.huntergaming.ui.R

// composables

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

            HunterGamingBackgroundImage(image = backgroundImage)

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

// previews

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