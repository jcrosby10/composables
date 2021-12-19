package com.huntergaming.ui.uitl

import androidx.lifecycle.MutableLiveData
import javax.inject.Inject

class CommunicationAdapter @Inject constructor() {
    val error: MutableLiveData<String> = MutableLiveData()
}