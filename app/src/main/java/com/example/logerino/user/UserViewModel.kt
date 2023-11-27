package com.example.logerino.user

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


class UserViewModel : ViewModel(){
    val state = mutableStateOf(User())
    val db = FirebaseFirestore.getInstance()

    // Henter data for bruker ved oppstart av programmet
    private fun hentData(){
        viewModelScope.launch {
            state.value = getDataFromFirebase()
        }
    }

    init {
        hentData()
    }


    suspend fun getDataFromFirebase():User{
        var about = User()

        try {
            db.collection("about").get().await().map {
                val result = it.toObject(User::class.java)
                about = result

            }
        }catch (e: FirebaseFirestoreException){
            Log.d("Feil:", "$e")

        }

        return about
    }

    //Oppdaterer informasjon om brukeren
    fun updateUserInfoFromFireStore(adresse: String?, fornavn: String?, etternavn: String?)
    {
        val userRef = db.collection("about").document("M1emKphn8fK5QUcwXJFO")

        userRef.update("adresse", adresse)
        userRef.update("fornavn", fornavn)
        userRef.update("etternavn", etternavn)

    }



}

