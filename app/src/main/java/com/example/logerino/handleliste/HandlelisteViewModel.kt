package com.example.logerino.handleliste

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class HandlelisteViewModel : ViewModel() {
    val state = mutableStateOf(Handleliste())
    val db = Firebase.firestore

    private fun getData(){
        viewModelScope.launch {
            state.value = getDataOnInitialize()
        }
    }

    init {
        getData()
    }


    val uiState = mutableStateOf("")


    fun addDataToFireStore(title: String?, varer: String?)
    {
        uiState.value = "Lagt til!"

        val handleliste = Handleliste(
            title,
            varer
        )

        db.collection("basket")
            .add(handleliste)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }
    }
    fun getDataFromFireStore()
    {
        db.collection("basket")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }

    fun getDocumentFromFireStore(title: String)
    {
        val docRef = db.collection("ting").document(title)

        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                } else {
                    Log.d(TAG, "No such document")
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
    }

    data class Ting(
        val navn: String = ""
    )

    suspend fun getDocumentFromTESTERFireStore(title: String): Ting
    {
        var vareTemp = Ting()

        try{
            db.collection("ting").get().addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }.await().map {
                val result = it.toObject(Ting::class.java)
                vareTemp = result

            }
        }catch (e: FirebaseFirestoreException){
            Log.d("Feil", "getDataOnInitialize: $e")
        }

        return vareTemp

    }

    suspend fun getDataOnInitialize(): Handleliste {
        var handlelisteTemp = Handleliste()

        try{
            db.collection("basket").get().addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }.await().map {
                val result = it.toObject(Handleliste::class.java)
                handlelisteTemp= result

            }
        }catch (e: FirebaseFirestoreException){
            Log.d("Feil", "getDataOnInitialize: $e")
        }

        return handlelisteTemp

    }

    fun getMultipleDocumentsFromFireStore()
    {
        db.collection("basket")
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d(TAG, "${document.id} => ${document.data}")

                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents: ", exception)
            }
    }
}

data class Handleliste(
    val title: String? = null,
    val varer: String? = null
)
