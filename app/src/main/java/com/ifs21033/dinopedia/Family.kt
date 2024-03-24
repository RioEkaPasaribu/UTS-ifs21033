package com.ifs21033.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class Family (
        val name: String,
        val icon: Int,
        val desc: String,
        val descrip: String,
        val period: String,
        val char: String,
        val habitat: String,
        val perilaku: String,
        val classi: String,
        val indexAwal: Int,
        val indexAkhir: Int,
) : Parcelable

