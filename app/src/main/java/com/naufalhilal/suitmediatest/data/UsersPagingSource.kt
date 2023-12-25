package com.naufalhilal.suitmediatest.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.naufalhilal.suitmediatest.data.response.DataItem
import com.naufalhilal.suitmediatest.data.retrofit.ApiService

class UsersPagingSource(private val apiService: ApiService):PagingSource<Int,DataItem>() {
    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }
    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        return state.anchorPosition?.let { anchorPosition->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1)?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DataItem> {
        return try {
            val page = params.key?: INITIAL_PAGE_INDEX
            val responseData=apiService.getUsers(page,params.loadSize).data

            LoadResult.Page(
                data =responseData,
                prevKey = if (page==1) null else page-1,
                nextKey =if (responseData.isEmpty()) null else page + 1
            )
        }catch (e:Exception){
            return LoadResult.Error(e)
        }
    }

}