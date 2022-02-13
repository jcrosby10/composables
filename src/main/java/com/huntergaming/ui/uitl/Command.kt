package com.huntergaming.ui.uitl

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

private val _navigationRoute = MutableStateFlow("")
val navigationRoute: StateFlow<String> = _navigationRoute

sealed class AuthHandler {
    class CreateAccount(val name: String, val email: String, val password: String): AuthHandler()
    class Login(val email: String, val password: String): AuthHandler()
    object Logout: AuthHandler()
    object SendVerificationEmail: AuthHandler()
}

internal sealed class AuthState: UseCase {

    class CreateAccountUseCase(val name: String, val email: String, val password: String): AuthState() {
        override fun execute() {
            authIntermediary.value = AuthHandler.CreateAccount(name, email, password)
        }
    }

    object SendVerificationUseCase: AuthState() {
        override fun execute() {
            authIntermediary.value = AuthHandler.SendVerificationEmail
        }
    }

    class LoginUseCase(val email: String, val password: String): AuthState() {
        override fun execute() {
            authIntermediary.value = AuthHandler.Login(email, password)
        }
    }

    object LogOutUseCase: AuthState() {
        override fun execute() {
            authIntermediary.value = AuthHandler.Logout
        }
    }
}

internal class NavigateUseCase(val route: String): UseCase {
    override fun execute() { _navigationRoute.value = route }
}

internal interface UseCase {
    fun execute()
}