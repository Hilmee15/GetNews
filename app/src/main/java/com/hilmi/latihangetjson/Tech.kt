package com.hilmi.latihangetjson

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Tech(
    var title : String = "",
    var thumb : String = "",
    var author : String = "",
    var tag : String = "",
    var time : String = "",
    var desc : String = "",
    var key : String = ""
) : Parcelable