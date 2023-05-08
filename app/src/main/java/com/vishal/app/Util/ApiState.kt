package com.vishal.app.Util

import com.vishal.app.Model.Joke

sealed class ApiState{
    object Loading : ApiState()
    class Failure(val msg:Throwable) : ApiState()
    class Success(val data: List<Joke>) : ApiState()
    object Empty : ApiState()
}
