package com.huntergaming.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.huntergaming.ui.R

// composables

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

// previews

@Preview(showBackground = true)
@Composable
private fun BackgroundImagePreview() {
    HunterGamingBackgroundImage(image = R.drawable.ic_lock_open_24)
}