package com.reyhan.helloworld.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.reyhan.helloworld.R
import androidx.recyclerview.widget.RecyclerView
import com.reyhan.helloworld.model.Absensi

class AbsensiAdapter(val absensiList: List<Absensi>) : RecyclerView.Adapter<AbsensiAdapter.AbsensiViewHolder>() {


    inner class AbsensiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val txtNamaItem = itemView.findViewById<TextView>(R.id.txtNamaItem)
        val txtLokasiItem = itemView.findViewById<TextView>(R.id.txtLokasiItem)
        val txtJenisAbsenItem = itemView.findViewById<TextView>(R.id.txtJenisAbsenItem)
        val txtTanggalItem = itemView.findViewById<TextView>(R.id.txtTanggalItem)
        val txtShiftKerjaItem = itemView.findViewById<TextView>(R.id.txtShiftKerjaItem)
        val imgSelfieList = itemView.findViewById<ImageView>(R.id.imgSelfieList)


        fun bind(absensi: Absensi){
            txtNamaItem.text= absensi.nama
            txtLokasiItem.text = absensi.alamat
            txtJenisAbsenItem.text = absensi.jenisAbsen
            txtTanggalItem.text = absensi.waktu
            txtShiftKerjaItem.text = absensi.shift
            imgSelfieList.setImageBitmap(absensi.foto)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AbsensiViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_absensi, parent, false)
        return AbsensiViewHolder(view)
    }

    override fun getItemCount(): Int {
        return absensiList.size
    }

    override fun onBindViewHolder(holder: AbsensiViewHolder, position: Int) {
        val absensi = absensiList[position]
        holder.bind(absensi)

    }
}