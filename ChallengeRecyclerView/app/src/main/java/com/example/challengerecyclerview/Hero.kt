package com.example.challengerecyclerview

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hero(
    var name : String,
    var date_birth_death : String,
    var desc : String,
    var asal : String,
    var pic : Int
): Parcelable
