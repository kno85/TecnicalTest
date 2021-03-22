package com.kno85.tecnicaltest.network.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Chararcter(
    @SerializedName("id")
    var id:Int?,
    @SerializedName("name")
    var name:String?,
    @SerializedName("description")
    var description:String?,
    @SerializedName("thumbnail")
    var thumbnail:Thumbnail?
):Parcelable




