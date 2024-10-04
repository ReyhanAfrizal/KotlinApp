package com.reyhan.helloworld

import android.util.Log

class FinanceCompany {
    val allNasabah= ArrayList<Nasabah>()

    fun addNasabah (nasabah: Nasabah){
        allNasabah.add(nasabah)
    }

    fun getAllNasabahInfo(){
        for(nasabahx in allNasabah){
            Log.d("Nama Nasabah / Total Pinjaman",nasabahx.getNamaNasabah() + nasabahx.totalPinjaman())
        }
    }
}