package com.abs.clase_04

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import com.abs.clase_04.HistoryActivity.Companion.OPERATIONS
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        var NUMERICTEXTVIEW = ""
    }

    val TAG = "State of change"

    private var operations: MutableList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG, "onCreate")
        operationTextView.text = NUMERICTEXTVIEW
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG,"onStart")
    }

    override fun onRestart() {
        super.onRestart()
        NUMERICTEXTVIEW = ""
        operationTextView.text = NUMERICTEXTVIEW
        Log.i(TAG,"OnRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG,"OnResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG,"OnPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG,"OnStop")
        NUMERICTEXTVIEW = operationTextView.text.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG,"OnDestroy")
    }



    fun pressCalculatorButton(view: View) {
        val button: Button = findViewById(view.id)
        operationTextView.text = operationTextView.text.toString() + button.text.toString()
    }

    fun pressClearCalculatorButton(view: View) {
        operationTextView.text = ""
    }

    fun pressEraseLastCharButton(view: View) {
        operationTextView.text = operationTextView.text.toString().substring(0,operationTextView.length() - 1)
    }

    fun equalButtonPressed(view: View) {
        makeOperation()
    }

    private fun makeOperation() {
        val opertation = operationTextView.text
        val numbers = opertation.split(Regex("[+\\-/*]"))// Expresiones regulares se utiliza \\ para expresiones especiales
        val operator = opertation.filter { !it.isDigit() }.toString()
        if (numbers.size == 2) {
            val result = when (operator) {
                "+" -> (numbers.first().toInt() + numbers.last().toInt()).toString()
                "-" -> (numbers.first().toInt() - numbers.last().toInt()).toString()
                "/" -> (numbers.first().toInt() / numbers.last().toDouble()).toString() // Para redondear
                "*" -> (numbers.first().toInt() * numbers.last().toInt()).toString()
                else -> ""
            }
            operations.add(operationTextView.text.toString())
            operationTextView.text = result
        }
    }

    fun onHistoryButtonPressed(view: View) {
        makeOperation()
        val intent = Intent(view.context, HistoryActivity::class.java)
        intent.putStringArrayListExtra(OPERATIONS, ArrayList(operations))
        view.context.startActivity(intent)
    }
}
