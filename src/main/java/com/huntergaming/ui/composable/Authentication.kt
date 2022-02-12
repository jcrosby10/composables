package com.huntergaming.ui.composable

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Login
import androidx.compose.material.icons.twotone.Add
import androidx.compose.material.icons.twotone.Login
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.huntergaming.ui.R
import com.huntergaming.ui.uitl.AuthState
import com.huntergaming.ui.uitl.isValidEmail
import com.huntergaming.ui.uitl.isValidField
import com.huntergaming.ui.uitl.isValidPassword
import kotlinx.coroutines.launch

// composables

@Composable
fun Authentication(
    background: Int
) {

    val showProgressIndicator = remember { mutableStateOf(false) }

    val createAccount = remember { mutableStateOf(false) }

    Box(modifier = Modifier
        .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        val email = remember { mutableStateOf(TextFieldValue(text =  "")) }
        val isEmailError = remember { mutableStateOf(true) }
        val password = remember { mutableStateOf(TextFieldValue(text =  "")) }
        val isPasswordError = remember { mutableStateOf(true) }

        HunterGamingBackgroundImage(image = background)

        if (createAccount.value) {
            CreateAccount(
                email = email,
                password = password,
                isEmailError = isEmailError,
                isPasswordError = isPasswordError
            )
        }
        else Login(
            email = email,
            password = password,
            isEmailError = isEmailError,
            isPasswordError = isPasswordError,
            createAccount = createAccount
        )

        if (showProgressIndicator.value) CircularProgressIndicator()
    }
}

// private composables

@Composable
private fun Login(
    isPasswordError: MutableState<Boolean>,
    password: MutableState<TextFieldValue>,
    isEmailError: MutableState<Boolean>,
    email: MutableState<TextFieldValue>,
    createAccount: MutableState<Boolean>
) {

    Column(
        modifier = Modifier
            .width(dimensionResource(id = R.dimen.auth_width))
            .height(dimensionResource(id = R.dimen.auth_height))
            .padding(all = dimensionResource(id = R.dimen.padding_medium)),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HunterGamingFieldRow(
            fieldNameString = R.string.email_input,
            hintString = R.string.email_input,
            onValueChanged = { isEmailError.value = isValidEmail(it.text) != true },
            textState = email,
            isError = isEmailError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        )

        HunterGamingFieldRow(
            fieldNameString = R.string.password_input,
            hintString = R.string.password_input,
            onValueChanged = { isPasswordError.value = isValidPassword(it.text) != true },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            textState = password,
            isPassword = true,
            isError = isPasswordError
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_size)))

        Row {
            val coroutineScope = rememberCoroutineScope()
            HunterGamingButton(
                modifier = Modifier
                    .padding(all = dimensionResource(id = R.dimen.padding_large)),

                onClick = {
                    coroutineScope.launch {
                        AuthState.LoginUseCase(email = email.value.text, password = password.value.text).execute()
                    }
                },

                icon = if (isSystemInDarkTheme()) Icons.TwoTone.Login else Icons.Outlined.Login,
                isEnabled = !isEmailError.value && !isPasswordError.value,
                contentDescription = R.string.content_description_login
            )

            HunterGamingButton(
                modifier = Modifier
                    .padding(all = dimensionResource(id = R.dimen.padding_large)),

                onClick = { createAccount.value = true },

                icon = if (isSystemInDarkTheme()) Icons.TwoTone.Add else Icons.Outlined.Add,
                contentDescription = R.string.content_description_create_account
            )
        }
    }
}

@Composable
private fun CreateAccount(
    isPasswordError: MutableState<Boolean>,
    password: MutableState<TextFieldValue>,
    isEmailError: MutableState<Boolean>,
    email: MutableState<TextFieldValue>
) {

    val name = remember { mutableStateOf(TextFieldValue(text =  "")) }
    val isNameError = remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .width(dimensionResource(id = R.dimen.auth_width))
            .height(dimensionResource(id = R.dimen.auth_height))
            .padding(all = dimensionResource(id = R.dimen.padding_medium)),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        HunterGamingFieldRow(
            fieldNameString = R.string.name_input,
            hintString = R.string.name_input,
            onValueChanged = { isNameError.value = isValidField(it.text) != true },
            textState = name,
            isError = isNameError,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Words
            )
        )

        HunterGamingFieldRow(
            fieldNameString = R.string.email_input,
            hintString = R.string.email_input,
            onValueChanged = { isEmailError.value = isValidEmail(it.text) != true },
            textState = email,
            isError = isEmailError,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        HunterGamingFieldRow(
            fieldNameString = R.string.password_input,
            hintString = R.string.password_input,
            onValueChanged = { isPasswordError.value = isValidPassword(it.text) != true },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            textState = password,
            isPassword = true,
            isError = isPasswordError
        )
        HunterGamingSmallCaptionText(text = R.string.create_account_password_rules)

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_size)))

        val coroutineScope = rememberCoroutineScope()
        HunterGamingButton(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(all = dimensionResource(id = R.dimen.padding_large)),

            onClick = {
                coroutineScope.launch {
                    AuthState.CreateAccountUseCase(name = name.value.text, email = email.value.text, password = password.value.text).execute()
                }
            },

            icon = if (isSystemInDarkTheme()) Icons.TwoTone.Add else Icons.Outlined.Add,
            isEnabled = !isNameError.value && !isEmailError.value && !isPasswordError.value,
            contentDescription = R.string.content_description_create_account
        )
    }
}

// previews

@Preview(showBackground = true, widthDp = 1280, heightDp = 720)
@Composable
private fun DefaultPreview() {
    Authentication(background = 1)
}

@Preview(showBackground = true, widthDp = 1920, heightDp = 1080)
@Composable
private fun DefaultPreview2() {
    Authentication(background = 1)
}

@Preview(showBackground = true, widthDp = 800, heightDp = 480)
@Composable
private fun DefaultPreview3() {
    Authentication(background = 1)
}

@Preview(showBackground = true, widthDp = 854, heightDp = 480)
@Composable
private fun DefaultPreview4() {
    Authentication(background = 1)
}