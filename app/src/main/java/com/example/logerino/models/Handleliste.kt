package com.example.logerino.models

import com.google.firebase.Timestamp


data class Handleliste (
    val userId:String = "",
    val title:String = "",
    val description:String = "",
    val timestamp: Timestamp = Timestamp.now(),
    val documentId:String = "")