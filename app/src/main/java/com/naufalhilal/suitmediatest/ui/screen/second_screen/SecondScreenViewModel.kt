package com.naufalhilal.suitmediatest.ui.screen.second_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naufalhilal.suitmediatest.data.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SecondScreenViewModel(private val repository: UserRepository): ViewModel() {
    private val _userName: MutableStateFlow<String> = MutableStateFlow("")
    val userName: StateFlow<String>
        get() =_userName
    fun getUser(){
        viewModelScope.launch {
            repository.getUser()
                .collect{user->
                    _userName.value = "${user.firstName} ${user.lastName}"
                }
        }
    }
}