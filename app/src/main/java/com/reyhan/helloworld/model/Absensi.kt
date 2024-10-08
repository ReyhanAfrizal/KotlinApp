package com.reyhan.helloworld.model

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize

data class Absensi(
    var nama: String,
    var alamat: String,
    var jenisAbsen: String,
    var shift: String,
    var waktu: String,
    var foto: Bitmap
): Parcelable