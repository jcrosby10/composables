package com.huntergaming.ui.uitl

import androidx.lifecycle.MutableLiveData
import java.util.Queue
import javax.inject.Inject

class CommunicationAdapter @Inject constructor() {
    val message: MutableLiveData<Queue<String>> = MutableLiveData()
}