package com.naufalhilal.suitmediatest.ui.screen.third_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.naufalhilal.suitmediatest.data.UserRepository
import com.naufalhilal.suitmediatest.data.UsersPagingSource
import com.naufalhilal.suitmediatest.data.pref.UserModel
import com.naufalhilal.suitmediatest.data.response.DataItem
import com.naufalhilal.suitmediatest.data.retrofit.ApiConfig
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume


class ThirdScreenViewModel(private val repository: UserRepository):ViewModel() {
    val pagingItem: Flow<PagingData<DataItem>> = Pager(
            config = PagingConfig(pageSize = 5),
            pagingSourceFactory ={ UsersPagingSource(apiService = ApiConfig.getApiService()) }
        ).flow

    suspend fun saveUser(user:UserModel):Boolean{
        return suspendCancellableCoroutine { continuation->
            viewModelScope.launch {
                val success =repository.saveUser(user)
                continuation.resume(success)
            }
        }
    }
}