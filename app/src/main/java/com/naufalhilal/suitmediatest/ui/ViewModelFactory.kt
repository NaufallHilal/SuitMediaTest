package com.naufalhilal.suitmediatest.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.naufalhilal.suitmediatest.data.UserRepository
import com.naufalhilal.suitmediatest.di.Injection
import com.naufalhilal.suitmediatest.ui.screen.second_screen.SecondScreenViewModel
import com.naufalhilal.suitmediatest.ui.screen.third_screen.ThirdScreenViewModel

class ViewModelFactory(private val repository: UserRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ThirdScreenViewModel::class.java) -> {
                ThirdScreenViewModel(repository) as T
            }
            modelClass.isAssignableFrom(SecondScreenViewModel::class.java) -> {
                SecondScreenViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(Injection.provideRepository(context))
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}