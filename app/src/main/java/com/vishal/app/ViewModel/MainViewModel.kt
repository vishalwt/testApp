package com.vishal.app.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vishal.app.Model.Joke
import com.vishal.app.Network.BasicApiService
import com.vishal.app.Util.ApiState
import com.vishal.app.db.JokeDAO
import com.vishal.app.di.viewModelModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

class MainViewModel
constructor(private val basicApiService: BasicApiService, private val jokeDao: JokeDAO) : ViewModel() {

    val postStateFlow:MutableStateFlow<ApiState>
    = MutableStateFlow(ApiState.Empty)

     var _postStateFlow = postStateFlow
    val jokeList = MutableLiveData<List<Joke>>()
    fun getJokeDataEveryMinut() = viewModelScope.launch(Dispatchers.IO) {
        postStateFlow.value = ApiState.Loading
        while(isActive) {
            getPostt()
                .catch { e->
                    postStateFlow.value=ApiState.Failure(e)
                }.collect { data->
                    viewModelScope.launch(Dispatchers.IO) {
                        jokeDao.saveJoke(data)
                        postStateFlow.value=ApiState.Success(jokeDao.getAllJokes())
                        jokeList.postValue(jokeDao.getAllJokes())
                    }

                }

            delay(60000L)
        }
    }

    private fun getPostt(): Flow<Joke> = flow {
        emit(basicApiService.getPost())
    }.flowOn(Dispatchers.IO)
}