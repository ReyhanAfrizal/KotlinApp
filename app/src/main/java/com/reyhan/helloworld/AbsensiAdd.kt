package com.reyhan.helloworld

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.icu.util.Calendar
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.reyhan.helloworld.model.Absensi
import java.text.SimpleDateFormat
import java.util.Locale

class AbsensiAdd : AppCompatActivity() {

    lateinit var bmpSelfie : Bitmap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_absensi_add)

        val user = intent.getStringExtra("username")
        var txtNamaAbsen = findViewById<EditText>(R.id.namaAbsen)
        txtNamaAbsen.setText(user)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initComponent()
    }

    fun initComponent(){
        var txtNamaAbsen = findViewById<EditText>(R.id.namaAbsen)
        var txtAlamat= findViewById<EditText>(R.id.alamatAbsen)
        var txtJenisAbsen = findViewById<RadioGroup>(R.id.rbAbsen)
        var txtJamAbsen = findViewById<EditText>(R.id.jamAbsen)
        var txtShift =findViewById<Spinner>(R.id.listShift)
        var imgSelfie = findViewById<ImageView>(R.id.fotoSelfie)
        val buttonSelf = findViewById<Button>(R.id.btnTake)
        val buttonSubmitAbsen =findViewById<Button>(R.id.btnSubmitAbsen)
        val calendar = Calendar.getInstance()

        val items = mutableListOf("Shift 1 Pagi", "Shift 2 Siang", "Shift 3 Malam")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        txtShift.adapter = adapter
        adapter.notifyDataSetChanged()

        buttonSubmitAbsen.setOnClickListener {
            val selectedAbsen = txtJenisAbsen.checkedRadioButtonId
            val jenisAbsensi = if (selectedAbsen != -1) findViewById<RadioButton>(selectedAbsen).text.toString() else ""

            val selectedShift = txtShift.selectedItem.toString()

            val namaAbsen = txtNamaAbsen.text.toString()
            val alamatAbsen = txtAlamat.text.toString()
            val waktuAbsen = txtJamAbsen.text.toString()

            val absensi = Absensi(
                nama = namaAbsen,
                alamat = alamatAbsen,
                jenisAbsen = jenisAbsensi,
                shift = selectedShift,
                waktu = waktuAbsen,
                foto = bmpSelfie ,
            )

            Toast.makeText(
                this,
                "Data Saved!\nNama: ${absensi.nama}\nAlamat: ${absensi.alamat}\nJenis Absen: ${absensi.jenisAbsen}\nShift: ${absensi.shift}\nWaktu: ${absensi.waktu}",
                Toast.LENGTH_LONG
            ).show()

            setResult(101, Intent().putExtra("absensi", absensi))

            finish()
        }


        txtJamAbsen.setOnClickListener{
            DatePickerDialog(this, { _, year, month, dayOfMonth ->

                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                TimePickerDialog(this, { _, hourOfDay, minute ->

                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    calendar.set(Calendar.MINUTE, minute)

                    val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
                    txtJamAbsen.setText(dateFormat.format(calendar.time))

                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()

            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        buttonSelf.setOnClickListener {
            val takeSelfieImg = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takeSelfieImg,99)
        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==99){
            val imgSetSelfie = findViewById<ImageView>(R.id.fotoSelfie)
            imgSetSelfie.setImageBitmap(data?.extras?.get("data") as Bitmap)
            bmpSelfie = data?.extras?.get("data") as Bitmap
        }

    }
}