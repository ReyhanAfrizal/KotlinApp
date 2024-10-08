package com.reyhan.helloworld

import android.content.Intent
import android.view.animation.AnimationUtils
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)

        val txtUsername = findViewById<EditText>(R.id.inputUsername)
        val txtPassword = findViewById<EditText>(R.id.inputPassword)
        val buttonMove = findViewById<Button>(R.id.buttonToNext)
        val buttonRotate = findViewById<Button>(R.id.rotate)

        val imageLogo = findViewById<ImageView>(R.id.logo)

        val animateLogo = AnimationUtils.loadAnimation(this, R.anim.logorotation)
        imageLogo.startAnimation(animateLogo)


        buttonMove.setOnClickListener {

            val animateLogo = AnimationUtils.loadAnimation(this, R.anim.logorotation)
            imageLogo.startAnimation(animateLogo)

            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("username",txtUsername.text.toString())

            startActivity(intent)

            Toast.makeText(
                this,
                "Hello User:${txtUsername.text} Pass:${txtPassword.text}",
                Toast.LENGTH_LONG
            ).show()
        }

        buttonRotate.setOnClickListener {
            val animateLogo = AnimationUtils.loadAnimation(this, R.anim.logorotation)
            imageLogo.startAnimation(animateLogo)
        }

        val goRegister=findViewById<Button>(R.id.btnRegis)
        goRegister.setOnClickListener {
            val goRegisterPage =Intent(this, Register::class.java)
            startActivity(goRegisterPage)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onStart() {
        super.onStart()
        Toast.makeText(this, "Selamat Datang", Toast.LENGTH_LONG).show()

    }

    override fun onResume() {
        super.onResume()

    }
}