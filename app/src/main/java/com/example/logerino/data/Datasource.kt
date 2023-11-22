package com.example.logerino.data

import com.example.logerino.R
import com.example.logerino.models.ListeHandle

class Datasource() {
    fun loadHandlelister(): List<ListeHandle> {
        return listOf<ListeHandle>(
            ListeHandle(R.string.handleliste1),
            ListeHandle(R.string.handleliste2),
            ListeHandle(R.string.handleliste3),
            ListeHandle(R.string.handleliste4))
    }
}