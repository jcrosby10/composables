package com.huntergaming.ui.uitl

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavHostController
import com.huntergaming.ui.R
import kotlinx.coroutines.flow.MutableStateFlow

val loggedInStatus: MutableLiveData<LoginState> = MutableLiveData(LoginState.LoggedOut)
val createAccountState: MutableLiveData<CreateAccountState> = MutableLiveData(CreateAccountState.Initial)

val authIntermediary: MutableStateFlow<AuthHandler?> = MutableStateFlow(null)

class HunterGamingObserver(
    private val dialogState: MutableState<Boolean>,
    private val dialogTitle: MutableState<Int>,
    private val dialogMessage: MutableState<String>,
    private val showProgressIndicator: MutableState<Boolean>,
    private val owner: LifecycleOwner,
    private val communicationAdapter: CommunicationAdapter,
    private val context: Context,
    private val navController: NavHostController,
    private val mainMenuRoute: String
) {

    // functions

    fun startErrorObserver(owner:LifecycleOwner) {
        communicationAdapter.message.observe(owner) {
            val message = it.remove()

            dialogState.value = true
            dialogTitle.value = R.string.dialog_error_title
            dialogMessage.value = message
        }
    }

    fun startLoggedInStateObserver() {
        loggedInStatus.observe(owner) {
            when (it) {
                LoginState.EmailNotVerified -> {
                    dialogTitle.value = R.string.dialog_title_success
                    dialogMessage.value = context.getString(R.string.create_account_verification_sent)
                    showProgressIndicator.value = false
                }

                LoginState.InProgress -> showProgressIndicator.value = true
                LoginState.LogoutInProgress -> showProgressIndicator.value = true

                LoginState.LoggedIn -> {
                    showProgressIndicator.value = false
                    navController.navigate(mainMenuRoute) {
                        popUpTo(mainMenuRoute) { inclusive = false }
                        launchSingleTop = true
                    }
                }

                LoginState.LoggedOut -> showProgressIndicator.value = false
            }
        }
    }

    fun startCreateAccountStateObserver() {
        createAccountState.observe(owner) {
            when (it) {
                CreateAccountState.AccountCreated -> AuthState.SendVerificationUseCase.execute()


                CreateAccountState.InProgress -> showProgressIndicator.value = true
                CreateAccountState.Initial -> {}


                is CreateAccountState.VerificationSent -> {
                    dialogTitle.value = R.string.dialog_title_success
                    dialogMessage.value = it.message
                    dialogState.value = true

                    showProgressIndicator.value = false
                    AuthState.LogOutUseCase.execute()
                }
            }
        }
    }
}

//classes

sealed class DataRequestState {
    object InProgress: DataRequestState()
    data class Success<T>(val data: T?): DataRequestState()
}

sealed class CreateAccountState {
    object Initial: CreateAccountState()
    object InProgress: CreateAccountState()
    object AccountCreated: CreateAccountState()
    class VerificationSent(val message: String): CreateAccountState()
}

sealed class LoginState {
    object EmailNotVerified: LoginState()
    object InProgress: LoginState()
    object LogoutInProgress: LoginState()
    object LoggedIn: LoginState()
    object LoggedOut: LoginState()
}