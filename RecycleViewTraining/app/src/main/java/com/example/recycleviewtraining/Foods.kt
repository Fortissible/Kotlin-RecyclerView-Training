package com.example.recycleviewtraining

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// dataclass untuk menampung item class dari foods
@Parcelize
data class Foods(
    var name: String,
    var price: String,
    var pic: Int
): Parcelable
