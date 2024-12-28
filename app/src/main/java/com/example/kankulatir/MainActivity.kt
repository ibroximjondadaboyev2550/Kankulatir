package com.example.kankulatir


import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvDisplay: TextView
    private lateinit var tvOperation: TextView
    private var firstNumber: Double = 0.0
    private var secondNumber: Double = 0.0
    private var operator: String? = null
    private var isNewOperation: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvDisplay = findViewById(R.id.tvDisplay)
        tvOperation = findViewById(R.id.tvOperation)

        val buttons = listOf(
            findViewById<Button>(R.id.btn0),
            findViewById<Button>(R.id.btn1),
            findViewById<Button>(R.id.btn2),
            findViewById<Button>(R.id.btn3),
            findViewById<Button>(R.id.btn4),
            findViewById<Button>(R.id.btn5),
            findViewById<Button>(R.id.btn6),
            findViewById<Button>(R.id.btn7),
            findViewById<Button>(R.id.btn8),
            findViewById<Button>(R.id.btn9)
        )

        for (button in buttons) {
            button.setOnClickListener { appendNumber(button.text.toString()) }
        }

        findViewById<Button>(R.id.btnAdd).setOnClickListener { setOperator("+") }
        findViewById<Button>(R.id.btnSubtract).setOnClickListener { setOperator("-") }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { setOperator("*") }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { setOperator("/") }

        findViewById<Button>(R.id.btnEqual).setOnClickListener { calculateResult() }

        findViewById<Button>(R.id.btnClear).setOnClickListener { clearAll() }
    }

    private fun appendNumber(number: String) {
        if (isNewOperation) {
            tvDisplay.text = number
            isNewOperation = false
        } else {
            tvDisplay.text = tvDisplay.text.toString() + number
        }

    }

    private fun setOperator(op: String) {
        firstNumber = tvDisplay.text.toString().toDoubleOrNull() ?: 0.0
        operator = op
        tvOperation.text = "$firstNumber $operator"
        isNewOperation = true
    }

    private fun calculateResult() {
        secondNumber = tvDisplay.text.toString().toDoubleOrNull() ?: 0.0
        val result = when (operator) {
            "+" -> firstNumber + secondNumber
            "-" -> firstNumber - secondNumber
            "*" -> firstNumber * secondNumber
            "/" -> if (secondNumber != 0.0) firstNumber / secondNumber else Double.NaN
            else -> 0.0
        }
        tvOperation.text = ""
        tvDisplay.text = if (result % 1.0 == 0.0) result.toInt().toString() else result.toString()
        isNewOperation = true
    }


    private fun clearAll() {
        tvDisplay.text = "0"
        tvOperation.text = ""
        firstNumber = 0.0
        secondNumber = 0.0
        operator = null
        isNewOperation = true
    }
}
