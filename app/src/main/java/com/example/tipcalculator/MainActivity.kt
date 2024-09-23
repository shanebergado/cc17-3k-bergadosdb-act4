package com.example.tipcalculator


import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var billAmountEditText: EditText
    private lateinit var tipOptionsGroup: RadioGroup
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var roundUpSwitch: Switch
    private lateinit var calculateButton: Button
    private lateinit var resultTextView: TextView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        billAmountEditText = findViewById(R.id.bill_amount_edit_text)
        tipOptionsGroup = findViewById(R.id.tip_options_group)
        roundUpSwitch = findViewById(R.id.round_up_switch)
        calculateButton = findViewById(R.id.calculate_button)
        resultTextView = findViewById(R.id.result_text_view)

        calculateButton.setOnClickListener { calculateTip() }
    }

    @SuppressLint("DefaultLocale")
    private fun calculateTip() {
        val billAmount = billAmountEditText.text.toString().toDoubleOrNull()

        if (billAmount == null || billAmount == 0.0) {
            resultTextView.text = ""
            Toast.makeText(this, "Please enter a valid bill amount", Toast.LENGTH_SHORT).show()
            return
        }

        val tipPercentage = when (tipOptionsGroup.checkedRadioButtonId) {
            R.id.amazing_option -> 0.20
            R.id.good_option -> 0.18
            R.id.ok_option -> 0.15
            else -> 0.0
        }

        var tipAmount = billAmount * tipPercentage

        if (roundUpSwitch.isChecked) {
            tipAmount = ceil(tipAmount)
        }

        val totalAmount = billAmount + tipAmount

        resultTextView.text = String.format("Tip Amount: $%.2f\nTotal Amount: $%.2f", tipAmount, totalAmount)
    }
}