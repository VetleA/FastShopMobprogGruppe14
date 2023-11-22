package com.example.logerino.data

import com.example.logerino.models.Handleliste

sealed class DataState {
    class Success(val data: MutableList<Handleliste>) : DataState()
    class Failure(val message: String) : DataState()
    object Loading : DataState()
    object Empty : DataState()
}