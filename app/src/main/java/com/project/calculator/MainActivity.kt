package com.project.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var lastDigit=false;
    var dotPresent=false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onDigit(view : View){
        tvInput.append((view as Button).text);
        lastDigit=true;
    }

    fun onClear(view : View){
        tvInput.text="";
        lastDigit=false;
        dotPresent=false;
    }

    fun onDecimalPoint(view : View){
        if(lastDigit && !dotPresent){
            lastDigit=false;
            dotPresent=true;
            tvInput.append(".")
        }else{
            Toast.makeText(this,"Invalid Dot", Toast.LENGTH_LONG).show();
        }
    }

    fun onEqual(view:View){
        if(lastDigit){
            var tvValue=tvInput.text.toString();
            var prefix=""
            try{
                if(tvValue.startsWith("-")){
                    prefix="-"
                    tvValue=tvValue.substring(1)
                }
                if(tvValue.contains("+")){
                    val splitValue=tvValue.split("+")
                    var firstTerm:String;
                    if(prefix.isEmpty()){
                        firstTerm=splitValue[0];
                    }else{
                        firstTerm=prefix + splitValue[0]
                    }
                    val secondTerm=splitValue[1]
                    tvInput.text=formatResult((firstTerm.toDouble() + secondTerm.toDouble()).toString())


                }
                else if(tvValue.contains("*")){
                    val splitValue=tvValue.split("*")
                    var firstTerm:String;
                    if(prefix.isEmpty()){
                        firstTerm=splitValue[0];
                    }else{
                        firstTerm=prefix + splitValue[0]
                    }
                    val secondTerm=splitValue[1]
                    tvInput.text=formatResult((firstTerm.toDouble() * secondTerm.toDouble()).toString())
                }
                else if(tvValue.contains("/")){
                    val splitValue=tvValue.split("/")
                    var firstTerm:String;
                    if(prefix.isEmpty()){
                        firstTerm=splitValue[0];
                    }else{
                        firstTerm=prefix + splitValue[0]
                    }
                    val secondTerm=splitValue[1]
                    tvInput.text=formatResult((firstTerm.toDouble() / secondTerm.toDouble()).toString())
                }
//                minus
                else if(tvValue.contains("-")){
                    val splitValue=tvValue.split("-")
                    var firstTerm:String;
                    if(prefix.isEmpty()){
                        firstTerm=splitValue[0];
                    }else{
                        firstTerm=prefix + splitValue[0]
                    }
                    val secondTerm=splitValue[1]
                    tvInput.text=formatResult((firstTerm.toDouble() - secondTerm.toDouble()).toString())
                }
            }catch (e: ArithmeticException){
                tvInput.setText("ERROR");
            }
        }
    }

    fun formatResult(result:String): String{
        return if(result.contains(".0")){
            result.substring(0,result.length-2)
        }else{
            result
        }
    }

    fun onOperator(view:View){
        val texto=(view as Button).text
        if(lastDigit && !isOperatorAdded(tvInput.text.toString())){
            tvInput.append(texto)
            lastDigit=false;
            dotPresent=false;
        }else{
            Toast.makeText(this, "Only 1 operation at a time is possible", Toast.LENGTH_LONG).show()
        }
    }

    fun isOperatorAdded(value: String) : Boolean{
        return if(value.startsWith("-")){
                       value.contains("/")
                    || value.contains("+")
                    || value.contains("*")
        }else{
                       value.contains("/")
                    || value.contains("+")
                    || value.contains("*")
                    || value.contains("-")
        }
    }
}
