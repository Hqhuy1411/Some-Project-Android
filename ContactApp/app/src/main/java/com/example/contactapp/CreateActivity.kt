package com.example.contactapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import java.io.Serializable

class CreateActivity : AppCompatActivity() {
    private var btnSubmit : Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
        val lnText = findViewById<EditText>(R.id.ev_lname)
        val flText = findViewById<EditText>(R.id.ev_fname)
        val phoneText = findViewById<EditText>(R.id.ev_phone)
        val emailText = findViewById<EditText>(R.id.ev_email)

        btnSubmit = findViewById(R.id.btn_save)
        btnSubmit?.setOnClickListener(View.OnClickListener {
            var intent: Intent? = Intent()
            val user = Contact(flText.text.toString(),lnText.text.toString(),phoneText.text.toString(),emailText.text.toString())
            intent?.putExtra("send_number" , user )
            setResult(RESULT_OK, intent);
            finish();
        })
    }
}
