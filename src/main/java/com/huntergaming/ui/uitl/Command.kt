package com.huntergaming.ui.uitl

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

private val pNavigationRoute = MutableStateFlow("")
val navigationRoute: StateFlow<String> = pNavigationRoute

sealed class AuthHandler {
    class CreateAccount(val name: String, val email: String, val password: String): AuthHandler()
    class Login(val email: String, val password: String): AuthHandler()
    object Logout: AuthHandler()
    object SendVerificationEmail: AuthHandler()
}

sealed class AuthState: UseCase {

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

class NavigateUseCase(val route: String): UseCase {
    override fun execute() { pNavigationRoute.value = route }
}

interface UseCase {
    fun execute()
}