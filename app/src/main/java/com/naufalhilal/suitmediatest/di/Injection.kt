package com.naufalhilal.suitmediatest.di

import android.content.Context
import com.naufalhilal.suitmediatest.data.UserRepository
import com.naufalhilal.suitmediatest.data.pref.UserPreference
import com.naufalhilal.suitmediatest.data.pref.dataStore
import com.naufalhilal.suitmediatest.data.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context):UserRepository{
        val pref = UserPreference.getInstance(context.dataStore)
        return UserRepository.getInstance(pref)
    }
}