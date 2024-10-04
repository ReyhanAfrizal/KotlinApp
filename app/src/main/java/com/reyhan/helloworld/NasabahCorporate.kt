package com.reyhan.helloworld

import android.util.Log

class NasabahCorporate(val nama: String, val jumlahPinjaman: Double, val tenor: Int) :
    Nasabah(nama, jumlahPinjaman), Pinjaman {
    private val bunga: Double = 0.03 //bunga 3%
    override fun totalPinjaman(): Double {
        return jumlahPinjaman + hitungBunga()
    }

    override fun hitungBunga(): Double {
        return jumlahPinjaman * bunga
    }

    override fun simulasiCicilan() {
        val cicilanPerBulan: Double = totalPinjaman() / tenor
        var infoSimulasi: String = ""
        var bulan: Int = 1

        while (bulan < tenor) {
            infoSimulasi=infoSimulasi.plus("Cicilan Ke-").plus(bulan).plus(" adalah Rp.").plus(cicilanPerBulan)
                .plus(" ")
            bulan++
        }
        Log.d("CicilanSimulasi", infoSimulasi)
    }

    override fun infoPinjaman() {
        val info: String = "Perusahaan : ".plus(getNamaNasabah())
        Log.d("Info",info)
    }


}