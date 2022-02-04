package com.huntergaming.ui.composable

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.huntergaming.ui.R

// composables

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

// previews

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