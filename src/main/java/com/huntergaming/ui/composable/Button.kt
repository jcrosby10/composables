package com.huntergaming.ui.composable

import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.huntergaming.ui.R

// composables

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

// previews

@Preview(showBackground = true)
@Composable
private fun TextButtonPreview() {
    HunterGamingTextButton(onClick = { /*TODO*/ }, text = R.string.test)
}

@Preview(showBackground = true)
@Composable
private fun ButtonPreview() {
    HunterGamingButton(onClick = {  }, icon = Icons.TwoTone.Done, contentDescription = R.string.content_description_not_needed)
}