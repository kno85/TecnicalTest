package com.kno85.tecnicaltest.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Results(
        @SerializedName("results")
         var items:List<Chararcter>?
):Parcelable