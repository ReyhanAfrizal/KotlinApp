package com.reyhan.helloworld

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.style.URLSpan
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val nasabahIndividu = NasabahIndividu("Reyhan", 10000000.0, 12)
        val nasabahCorporate: NasabahCorporate =
            NasabahCorporate("PT. Maju Kena Mundur Kena", 100000000.0, 36)

        nasabahIndividu.infoPinjaman()
        nasabahIndividu.simulasiCicilan()

        nasabahCorporate.infoPinjaman()
        nasabahCorporate.simulasiCicilan()

        val financeCompany = FinanceCompany()

        financeCompany.addNasabah(nasabahIndividu)
        financeCompany.addNasabah(nasabahCorporate)
        financeCompany.getAllNasabahInfo()

        val user = intent.getStringExtra("username")

        val textView =findViewById<TextView>(R.id.txtHello)
        textView.text= "Hello, $user !"

        val goWeb = findViewById<Button>(R.id.btnWebBCAF)

        goWeb.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://bcafinance.co.id"))
            startActivity(intent)
        }

        val profile = findViewById<ImageView>(R.id.person)
        profile.setOnClickListener {
            val takePerson = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePerson,99)
        }

        val goAbsensi = findViewById<CardView>(R.id.cardAbsen)
        goAbsensi.setOnClickListener {
            val goAbsensiPage=Intent(this, MenuAbsensi::class.java)
            goAbsensiPage.putExtra("username",user)
            startActivity(goAbsensiPage)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}