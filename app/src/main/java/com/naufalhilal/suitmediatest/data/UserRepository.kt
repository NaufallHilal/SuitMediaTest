package com.naufalhilal.suitmediatest.data

import com.naufalhilal.suitmediatest.data.pref.UserModel
import com.naufalhilal.suitmediatest.data.pref.UserPreference
import kotlinx.coroutines.flow.Flow

class UserRepository private constructor(private val userPreference: UserPreference,) {
    suspend fun saveUser(user:UserModel):Boolean{
        if (userPreference.saveUser(user)){
            return true
        }
        return true
    }

    fun getUser(): Flow<UserModel>{
        return userPreference.getUser()
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(
            userPreference: UserPreference,
        ): UserRepository =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userPreference = userPreference)
            }.also { instance = it }
    }
}