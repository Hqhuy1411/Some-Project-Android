package com.example.helloworld

import android.R.attr
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView.OnItemLongClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    private var txCount :TextView ? = null
    private var nhap :TextView ? =null
    private var btnAdd :FloatingActionButton ? = null
    //private var btnMin :FloatingActionButton ? = null
    private var X :Int ?=null
    private var lvCount :ListView ? = null
    private  var array = ArrayList<Int>()
    private lateinit var arrayAdapter : ArrayAdapter<Int>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.txCount = findViewById(R.id.txt_view)
        this.btnAdd = findViewById(R.id.btn_add)
        this.nhap = findViewById(R.id.textView2)
       // this.btnMin = findViewById(R.id.btn_min)
        this.lvCount = findViewById(R.id.lv_count)

        arrayAdapter = ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,array)
        lvCount?.adapter = arrayAdapter
        btnAdd?.setOnClickListener(View.OnClickListener {
            var count = txCount?.text.toString().toInt();
            txCount?.text = "" + ++count;
            array.add(count)
            arrayAdapter.notifyDataSetChanged()

        })
//        btnMin?.setOnClickListener(View.OnClickListener {
//            var count = txCount?.text.toString().toInt();
//            txCount?.text="" + --count;
//        })

        lvCount?.setOnItemLongClickListener(OnItemLongClickListener { arg0, arg1, pos, id -> // TODO Auto-generated method stub
            array.remove(pos + 1)
            arrayAdapter.notifyDataSetChanged()
            true
        })

        lvCount?.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("number" , array.get(position).toString())
            X = position
            startActivityForResult  (intent,1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode === 1) {
            if (resultCode === RESULT_OK) {
                val returnString = data!!.getStringExtra("send_number")
                if (returnString != null) {
                    array.set(X!!.toInt(),returnString.toInt())
                    arrayAdapter.notifyDataSetChanged()

                }
            }
            if (resultCode === RESULT_CANCELED) {
                nhap?.setText("Nothing selected")
            }
        }
    }

}


