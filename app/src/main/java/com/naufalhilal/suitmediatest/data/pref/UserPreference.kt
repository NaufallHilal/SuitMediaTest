package com.naufalhilal.suitmediatest.data.pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user")
class UserPreference private constructor(private val dataStore: DataStore<Preferences>){
    suspend fun saveUser(user: UserModel):Boolean{
        dataStore.edit {preference->
            preference[FIRSTNAME_KEY]=user.firstName
            preference[LASTNAME_KEY]=user.lastName
            preference[EMAIL_KEY]=user.email
            preference[AVATAR_KEY]=user.avater
        }
        return true
    }
    fun getUser(): Flow<UserModel> {
         return dataStore.data.map {preference->
            UserModel(
                preference[FIRSTNAME_KEY]?: "",
                preference[LASTNAME_KEY]?: "",
                preference[EMAIL_KEY]?: "",
                preference[AVATAR_KEY]?:"",
            )
        }
    }
    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null

        private val EMAIL_KEY = stringPreferencesKey("email")
        private val FIRSTNAME_KEY = stringPreferencesKey("firstName")
        private val LASTNAME_KEY = stringPreferencesKey("lastName")
        private val AVATAR_KEY = stringPreferencesKey("avatae")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}