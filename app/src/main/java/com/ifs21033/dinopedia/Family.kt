package com.ifs21033.dinopedia

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize

data class Family (
        var name: String,
        var icon: Int,
        var desc: String,
        var descrip: String,
        var period: String,
        var char: String,
        var habitat: String,
        var perilaku: String,
        var classi: String,
    ) : Parcelable
