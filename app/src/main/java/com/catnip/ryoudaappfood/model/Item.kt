package com.catnip.ryuodaappfood.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(
    val name: String,
    val image: Int,
    val price: Int,
    val desc: String,
    val location: String

): Parcelable