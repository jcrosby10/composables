package com.huntergaming.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

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
        .padding(dimensionResource(id = R.dimen.edge_padding_5dp))) {

        Text(
            text = stringResource(id = fieldNameString),
            style = MaterialTheme.typography.body1,
            modifier = Modifier
                .padding(end = dimensionResource(id = R.dimen.edge_padding_5dp), top = dimensionResource(id = R.dimen.edge_padding_15dp))
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
                .padding(start = dimensionResource(id = R.dimen.edge_padding_5dp)),

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