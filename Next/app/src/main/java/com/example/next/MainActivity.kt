package com.example.next

import android.os.Bundle
import android.view.View
import android.widget.Button

import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {
//    private var num_a : EditText ? = null
//    private var num_b : EditText ? = null
//    private var cong : Button ? = null
//    private var tru : Button ? = null
//    private var nhan : Button ? = null
//    private var chia : Button ? = null
//    private var lvHistory : ListView? = null
//    private  var array = ArrayList<Int>()
//    private lateinit var arrayAdapter : ArrayAdapter<Int>

    private  var rsText : TextView ?=null;
    private  var ifText : TextView ?=null;
    private var a : String="";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.ifText = findViewById(R.id.info_text);
        this.rsText = findViewById(R.id.rs_text);
//        this.num_a =findViewById(R.id.number_a)
//        this.num_b =findViewById(R.id.number_b)
//        this.cong = findViewById(R.id.cong)
//        this.tru = findViewById(R.id.tru)
//        this.nhan = findViewById(R.id.nhan)
//        this.chia = findViewById(R.id.chia)
//        this.lvHistory = findViewById(R.id.lv_history)
//        arrayAdapter = ArrayAdapter(this,android.R.layout.simple_dropdown_item_1line,array)
//        lvHistory?.adapter = arrayAdapter
//        this.cong?.setOnClickListener(this)
//        this.tru?.setOnClickListener(this)
//        this.nhan?.setOnClickListener(this)
//        this.chia?.setOnClickListener(this)


    }

    fun ButtonClick(p0: View?){
        var button = p0 as Button;
        var z =button.text.toString();
        when (z) {
            "C" -> {
                if(this.a.length >=1){
                    a = a.substring(0,a.length-1)
                    this.ifText?.text = a;
                }
                this.rsText?.text="";
            }
            "+/-" ->{
                if(a.last() =='+'){
                    a.replace('+','-');
                }
                this.ifText?.text = a;
            }
            "AC" -> {
                this.a = "";
                this.ifText?.text="";
                this.rsText?.text="";
            }
            "=" ->{
                this.rsText?.text = evaluate(a).toString();
            }
            else ->{
                this.a = this.a + z;
                this.ifText?.text = a;
            }
        }
    }

    fun evaluate(expression: String): Int {
        val tokens = expression.toCharArray()

        // Stack for numbers: 'values'
        val values: Stack<Int> = Stack<Int>()

        // Stack for Operators: 'ops'
        val ops: Stack<Char> = Stack<Char>()
        var i = 0
        while (i < tokens.size) {


            // Current token is a
            // whitespace, skip it
            if (tokens[i] == ' ') {
                i++
                continue
            }

            // Current token is a number,
            // push it to stack for numbers
            if (tokens[i] in '0'..'9'
            ) {
                val sbuf = StringBuffer()

                // There may be more than one
                // digits in number
                while (i < tokens.size && tokens[i] >= '0' && tokens[i] <= '9') sbuf.append(tokens[i++])
                values.push(sbuf.toString().toInt())

                // right now the i points to
                // the character next to the digit,
                // since the for loop also increases
                // the i, we would skip one
                //  token position; we need to
                // decrease the value of i by 1 to
                // correct the offset.
                i--
            } else if (tokens[i] == '(') ops.push(tokens[i]) else if (tokens[i] == ')') {
                while (ops.peek() !== '(') values.push(
                    applyOp(
                        ops.pop(),
                        values.pop(),
                        values.pop()
                    )
                )
                ops.pop()
            } else if (tokens[i] == '+' || tokens[i] == '-' || tokens[i] == '*' || tokens[i] == '/') {
                // While top of 'ops' has same
                // or greater precedence to current
                // token, which is an operator.
                // Apply operator on top of 'ops'
                // to top two elements in values stack
                while (!ops.empty() &&
                    hasPrecedence(
                        tokens[i],
                        ops.peek()
                    )
                ) values.push(
                    applyOp(
                        ops.pop(),
                        values.pop(),
                        values.pop()
                    )
                )

                // Push current token to 'ops'.
                ops.push(tokens[i])
            }
            i++
        }

        // Entire expression has been
        // parsed at this point, apply remaining
        // ops to remaining values
        while (!ops.empty()) values.push(
            applyOp(
                ops.pop(),
                values.pop(),
                values.pop()
            )
        )

        // Top of 'values' contains
        // result, return it
        return values.pop()
    }

    // Returns true if 'op2' has higher
    // or same precedence as 'op1',
    // otherwise returns false.
    fun hasPrecedence(
        op1: Char, op2: Char
    ): Boolean {
        if (op2 == '(' || op2 == ')') return false
        return if ((op1 == '*' || op1 == '/') &&
            (op2 == '+' || op2 == '-')
        ) false else true
    }

    // A utility method to apply an
    // operator 'op' on operands 'a'
    // and 'b'. Return the result.
    fun applyOp(
        op: Char,
        b: Int, a: Int
    ): Int {
        when (op) {
            '+' -> return a + b
            '-' -> return a - b
            '*' -> return a * b
            '/' -> {
                if (b == 0) throw UnsupportedOperationException(
                    "Cannot divide by zero"
                )
                return a / b
            }
        }
        return 0
    }


//    override fun onClick(p0: View?) {
//        val b = p0 as Button
//        var z = this.num_a?.text.toString().toInt()
//        var y = this.num_b?.text.toString().toInt()
//        var x:Int =0
//        when (b.text) {
//            "+" -> x = y+z
//            "-" -> x = y-z
//            "*" -> x = y*z
//            "/" -> x = y/z
//            else -> { // Note the block
//                print("x is neither 1 nor 2")
//            }
//        }
//        array.add(x)
//        arrayAdapter.notifyDataSetChanged()
//
//    }

}








