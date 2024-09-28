package com.example.bottomnavbar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.quicoy.navigation.R

class Calculator : Fragment() {

    private lateinit var number1: EditText
    private lateinit var number2: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnSubtract: Button
    private lateinit var btnMultiply: Button
    private lateinit var btnDivide: Button
    private lateinit var result: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_calculator, container, false)

        // Initialize UI elements
        number1 = view.findViewById(R.id.number1)
        number2 = view.findViewById(R.id.number2)
        btnAdd = view.findViewById(R.id.btnAdd)
        btnSubtract = view.findViewById(R.id.btnSubtract)
        btnMultiply = view.findViewById(R.id.btnMultiply)
        btnDivide = view.findViewById(R.id.btnDivide)
        result = view.findViewById(R.id.result)

        // Set up button click listeners
        btnAdd.setOnClickListener {
            val num1 = number1.text.toString().toDoubleOrNull()
            val num2 = number2.text.toString().toDoubleOrNull()
            if (num1 != null && num2 != null) {
                val sum = num1 + num2
                result.text = "Result: %.2f".format(sum)
            } else {
                result.text = "Please enter valid numbers"
            }
        }

        btnSubtract.setOnClickListener {
            val num1 = number1.text.toString().toDoubleOrNull()
            val num2 = number2.text.toString().toDoubleOrNull()
            if (num1 != null && num2 != null) {
                val difference = num1 - num2
                result.text = "Result: %.2f".format(difference)
            } else {
                result.text = "Please enter valid numbers"
            }
        }

        btnMultiply.setOnClickListener {
            val num1 = number1.text.toString().toDoubleOrNull()
            val num2 = number2.text.toString().toDoubleOrNull()
            if (num1 != null && num2 != null) {
                val product = num1 * num2
                result.text = "Result: %.2f".format(product)
            } else {
                result.text = "Please enter valid numbers"
            }
        }

        btnDivide.setOnClickListener {
            val num1 = number1.text.toString().toDoubleOrNull()
            val num2 = number2.text.toString().toDoubleOrNull()
            if (num1 != null && num2 != null) {
                if (num2 != 0.0) {
                    val quotient = num1 / num2
                    result.text = "Result: %.2f".format(quotient)
                } else {
                    result.text = "Cannot divide by zero"
                }
            } else {
                result.text = "Please enter valid numbers"
            }
        }

        return view
    }
}
