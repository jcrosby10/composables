package com.huntergaming.ui.uitl

import android.util.Patterns

fun isValidEmail(email: String): Boolean = email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
fun isValidPassword(password: String): Boolean = password.isNotEmpty() && password.length >= 10 && password.contains(Regex("(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\\W)"))
fun isValidField(value: String): Boolean = value.isNotEmpty()