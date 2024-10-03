package com.example.tipcalculator

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat // Import SwitchCompat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get references to UI elements
        val costOfServiceEditText = findViewById<EditText>(R.id.costOfServiceEditText)
        val tipOptions = findViewById<RadioGroup>(R.id.tipOptions)
        val roundUpSwitch = findViewById<SwitchCompat>(R.id.roundUpSwitch) // Use SwitchCompat
        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val tipResultTextView = findViewById<TextView>(R.id.tipResult)

        // Set up the onClickListener for the calculate button
        calculateButton.setOnClickListener {
            // Get the cost of service
            val costString = costOfServiceEditText.text.toString()
            val cost = costString.toDoubleOrNull()

            // If cost is null or zero, reset the result and return
            if (cost == null || cost == 0.0) {
                tipResultTextView.text = getString(R.string.tip_amount, 0.0)
                return@setOnClickListener
            }

            // Get the selected tip percentage
            val tipPercentage = when (tipOptions.checkedRadioButtonId) {
                R.id.amazingOption -> 0.20
                R.id.goodOption -> 0.18
                R.id.okOption -> 0.15
                else -> 0.0
            }

            // Calculate the tip
            var tip = cost * tipPercentage

            // Round up the tip if the switch is on
            if (roundUpSwitch.isChecked) {
                tip = ceil(tip)
            }

            // Display the calculated tip
            tipResultTextView.text = getString(R.string.tip_amount, tip)
        }
    }
}
