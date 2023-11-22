package com.example.logerino.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logerino.data.Resource
import com.example.logerino.models.Handleliste
import com.example.logerino.repository.StorageRepository
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: StorageRepository
):ViewModel() {

    var homeUiState by mutableStateOf(HomeUiState())

    val user = repository.user()
    val hasUser:Boolean
        get() = repository.hasUser()
    private val userId:String
        get() = repository.getUserID()

    fun loadHandleliste(){
        if (hasUser){
            if (userId.isNotBlank()){
                getUserHandleliste(userId)
            }

        }else{
            homeUiState = homeUiState.copy(handlelisteListe = StorageRepository.Resources.Error(
            throwable = Throwable("Brukeren er ikke logget inn")))
        }
    }

    fun deleteHandleliste(handleListeId:String) = repository.deleteHandleliste(handleListeId){
        homeUiState = homeUiState.copy(handlelisteDeletedStatus = it)
    }

    private fun getUserHandleliste(userId:String) = viewModelScope.launch {
        repository.getUserHandleliste(userId).collect{
            homeUiState = homeUiState.copy(handlelisteListe = it)
        }
    }

    fun signOut() = repository.signOut()


}

data class HomeUiState(
    val handlelisteListe:StorageRepository.Resources<List<Handleliste>> = StorageRepository.Resources.Loading(),
    val handlelisteDeletedStatus: Boolean = false
)