package com.abs.clase_04

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.history_activity.*

class HistoryActivity : AppCompatActivity() {

    companion object {
        var OPERATIONS = "OPERATIONS"
    }

    val TAG = "HISTORIC State"

    private var operations: ArrayList<String> = ArrayList()
    var historyList = ArrayList<HistoricItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.history_activity)
        recycler_view.adapter = HistoricAdapter(historyList)
        recycler_view.layoutManager = LinearLayoutManager(this)
        operations = intent.getStringArrayListExtra(OPERATIONS)!!

        operations.forEach {
            val numbers = it.split(Regex("[+\\-/*]"))
            val operator = it.filter { !it.isDigit() }
            val result = when (operator) {
                "+" -> (numbers.first().toInt() + numbers.last().toInt()).toString()
                "-" -> (numbers.first().toInt() - numbers.last().toInt()).toString()
                "*" -> (numbers.first().toInt() * numbers.last().toInt()).toString()
                "/" -> (numbers.first().toInt() / numbers.last().toDouble()).toString()
                else -> ""
            }
            historyList.add(HistoricItem(operation = it,result = result))
            recycler_view.adapter?.notifyItemInserted(historyList.size - 1)
        }
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG,"OnStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG,"OnDestroy")
    }

}