package com.example.helloworld

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText

class DetailActivity : AppCompatActivity() {
    private var btnSubmit : Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val editText = findViewById<EditText>(R.id.receive_tv)
        btnSubmit = findViewById(R.id.sm_bt)

        val ss:String = intent.getStringExtra("number").toString()

        editText.setText(ss)

        btnSubmit?.setOnClickListener(View.OnClickListener {
            var intent: Intent? = Intent()
            intent?.putExtra("send_number" , editText.text.toString())
            setResult(RESULT_OK, intent);
            finish();
        })
    }
}