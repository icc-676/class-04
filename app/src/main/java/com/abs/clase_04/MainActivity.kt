package com.abs.clase_04

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.abs.clase_04.HistoryActivity.Companion.OPERATIONS
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var operations: MutableList<String> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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

    fun onHistoryButtonPressed(view: View) {
        operations.add(operationTextView.text.toString())
        pressClearCalculatorButton(view)
        val intent = Intent(view.context, HistoryActivity::class.java)
        intent.putStringArrayListExtra(OPERATIONS, ArrayList(operations))
        view.context.startActivity(intent)
    }
}
