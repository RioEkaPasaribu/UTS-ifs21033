package com.ifs21033.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Dino(
    val name: String,
    val icon: Int,
    val descs: String,
    val char: String,
    val kel: String,
    val habitat: String,
    val makanan: String,
    val length: String,
    val tall: String,
    val weight: String,
    val weak: String,
    val familyName: String,
) : Parcelable {
}