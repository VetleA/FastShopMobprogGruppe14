package com.example.logerino.repository

import com.example.logerino.models.Handleliste
import com.google.firebase.Timestamp
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow


const val HANDLELISTE_COLLECTION_REF = "handleliste"

class StorageRepository(){

    fun user() = Firebase.auth.currentUser
    fun hasUser():Boolean = Firebase.auth.currentUser != null

    fun getUserID():String = Firebase.auth.currentUser?.uid.orEmpty()

    private val handlelisteRef:CollectionReference = Firebase
        .firestore.collection(HANDLELISTE_COLLECTION_REF)

    fun getUserHandleliste(
        userId:String
    ):Flow<Resources<List<Handleliste>>> = callbackFlow {
        var snapShotStateListener:ListenerRegistration? = null

        try {
            snapShotStateListener = handlelisteRef
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .whereEqualTo("userId", userId).addSnapshotListener{
                    snapshot, e ->
                    val response = if (snapshot != null){
                        val handleliste = snapshot.toObjects(Handleliste::class.java)
                        Resources.Success(data = handleliste)
                    }else{
                        Resources.Error(throwable = e?.cause)
                    }
                    trySend(response)

                }

        }catch (e:Exception){
            trySend(Resources.Error(e?.cause))
            e.printStackTrace()
        }

        awaitClose{
            snapShotStateListener?.remove()
        }
    }

    fun getHandleliste(
        handlelisteId:String,
        onError:(Throwable?) -> Unit,
        onSuccess:(Handleliste?) -> Unit,
    ) {
        handlelisteRef.document(handlelisteId).get().addOnSuccessListener {
            onSuccess.invoke(it?.toObject(Handleliste::class.java))
        }
            .addOnFailureListener{result ->
                onError.invoke(result.cause)
            }
    }
    fun addHandleliste(
        userId:String = "",
        title:String = "",
        description:String = "",
        timestamp: Timestamp = Timestamp.now(),
        onComplete: (Boolean) -> Unit
    ) {
        val documentId = handlelisteRef.document().id
        val handleliste = Handleliste(userId, title, description,
            timestamp, documentId = documentId)
        handlelisteRef.document(documentId).set(handleliste).addOnCompleteListener{
            result -> onComplete.invoke(result.isSuccessful)
        }
    }

    fun signOut() = Firebase.auth.signOut()

    fun updateHandleliste(title: String,
                          handleliste: String,
                          handlelisteId: String,
                          onResult:(Boolean) -> Unit) {
        val updateData = hashMapOf<String, Any>(
            "title" to title,
            "handleliste" to handleliste,)

        handlelisteRef.document(handlelisteId).update(updateData).addOnCompleteListener{
            onResult(it.isSuccessful)
    }
}

    fun deleteHandleliste(handlelisteId: String, onComplete: (Boolean) -> Unit){
        handlelisteRef.document(handlelisteId).delete().addOnCompleteListener {
            onComplete.invoke(it.isSuccessful)

    }
    }


    sealed class Resources<T>(
    val  data: T? = null,
    val throwable: Throwable? = null,
    )
{
    class Loading<T>:Resources<T>()
    class Success<T>(data: T?):Resources<T>(data= data)
    class Error<T>(throwable: Throwable?):Resources<T>(throwable = throwable)
}
}