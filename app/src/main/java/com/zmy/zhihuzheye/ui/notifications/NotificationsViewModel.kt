package com.zmy.zhihuzheye.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotificationsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "无聊随便写的一个kotlinDemo"
    }
    val text: LiveData<String> = _text
}