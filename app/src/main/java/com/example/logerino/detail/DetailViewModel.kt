package com.example.logerino.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.logerino.models.Handleliste
import com.example.logerino.repository.StorageRepository
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseUser

class DetailViewModel(
    private val repository: StorageRepository
) : ViewModel() {
    //Endre til private
    var detailUiState by mutableStateOf(DetailUiState())
        private set

    private val hasUser:Boolean
        get() = repository.hasUser()

    private val user:FirebaseUser?
        get() = repository.user()

    fun onTitleChange(title: String){
        detailUiState = detailUiState.copy(title = title)
    }

    fun onHandlelisteChange(handleliste: String){
        detailUiState = detailUiState.copy(handleliste = handleliste)
    }

    fun addHandleliste(){
        if (hasUser){
            repository.addHandleliste(userId = user!!.uid,
                title = detailUiState.title,
                description = detailUiState.handleliste,
                timestamp = Timestamp.now()
                ) {
                detailUiState = detailUiState.copy(handlelisteAddedStatus = it)

            }
        }
    }

    fun setEditFields(handleliste: Handleliste){
        detailUiState = detailUiState.copy(
            title = handleliste.title,
            handleliste = handleliste.description,
            )
    }

    fun getHandleliste(handlelisteId: String) {
        repository.getHandleliste(handlelisteId = handlelisteId, onError = {}){
            detailUiState = detailUiState.copy(selectedHandleliste = it)
            detailUiState.selectedHandleliste?.let { it1 -> setEditFields(it1) }
        }
    }

    fun updateHandleliste(
        handlelisteId: String
    ){
        repository.updateHandleliste(
            title = detailUiState.title,
            handleliste = detailUiState.handleliste,
            handlelisteId = handlelisteId
        ){
            detailUiState = detailUiState.copy(updatehandlelisteStatus = it)
        }
    }

    fun resetHandlelisteAddedStatus(){
        detailUiState = detailUiState.copy(updatehandlelisteStatus = false)
    }

    fun resetState(){
        detailUiState = DetailUiState()
    }




}

data class DetailUiState(
    val title:String = "",
    val handleliste:String = "",
    val handlelisteAddedStatus:Boolean = false,
    val updatehandlelisteStatus:Boolean = false,
    val selectedHandleliste: Handleliste? = null
)