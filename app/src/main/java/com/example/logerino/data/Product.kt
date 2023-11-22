package com.example.logerino.data

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

data class Product(
    var id: String? = null,
    var name: String? = null,
    var price: Int? = null,
    @ServerTimestamp
    var date: Date? = null
)