package com.example.calculator_final

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG: String = "TAG"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setOnClick()
    }

    fun setOnClick() {
        // Numbers
        btnNumZero.setOnClickListener { appendOnExpression("0", true) }
        btnNumOne.setOnClickListener { appendOnExpression("1", true) }
        btnNumTwo.setOnClickListener { appendOnExpression("2", true) }
        btnNumThree.setOnClickListener { appendOnExpression("3", true) }
        btnNumFour.setOnClickListener { appendOnExpression("4", true) }
        btnNumFive.setOnClickListener { appendOnExpression("5", true) }
        btnNumSix.setOnClickListener { appendOnExpression("6", true) }
        btnNumSeven.setOnClickListener { appendOnExpression("7", true) }
        btnNumEight.setOnClickListener { appendOnExpression("8", true) }
        btnNumNight.setOnClickListener { appendOnExpression("9", true) }

        // Operators
        btnPlus.setOnClickListener { appendOnExpression("+", false) }
        btnMinus.setOnClickListener { appendOnExpression("-", false) }
        btnMulti.setOnClickListener { appendOnExpression("*", false) }
        btnDivide.setOnClickListener { appendOnExpression("/", false) }
        btnMod.setOnClickListener { appendOnExpression("%", false) }
        btnDot.setOnClickListener { appendOnExpression(".", false) }

        // Delete
        btnDel.setOnClickListener {
            if (tvExpression.text.isNotEmpty()) {
                tvExpression.text =
                        tvExpression.text.toString().substring(0, tvExpression.text.length - 1)
            }
        }

        // Clear
        btnAC.setOnClickListener {
            clearScreen()
        }

        // Calculating
        btnEquals.setOnClickListener { calculatingExpression() }
    }

    fun appendOnExpression(value: String, isCanClear: Boolean) {

        if (tvResult.text.isNotEmpty()) tvExpression.text = ""

        if (isCanClear) {
            tvResult.text = ""
            tvExpression.append(value)
        } else {
            tvExpression.append(tvResult.text)
            tvExpression.append(value)
            tvResult.text = ""
        }
    }

    fun calculatingExpression() {
        try {
            // build expression
            val expression = ExpressionBuilder(tvExpression.text.toString()).build()
            val result = expression.evaluate()
            val longResult = result.toLong()
            if (result == longResult.toDouble()) tvResult.text = longResult.toString()
            else tvResult.text = result.toString()
        } catch (e: Exception) {
            Log.d(TAG, "exceptionMessage: " + e.message)
        }
    }

    fun clearScreen() {
        tvResult.text = ""
        tvExpression.text = ""
    }

    fun saveLastResult() {
        var sharePref: SharedPreferences = getPreferences(Context.MODE_PRIVATE)
        var editor: SharedPreferences.Editor = sharePref.edit()
        editor.putString(getString(R.string.preferences_file_key), tvResult.text.toString())
        editor.commit()
    }

    fun checkLastResult() {
        var lastResult: String? =
                getPreferences(Context.MODE_PRIVATE).getString(
                        getString(R.string.preferences_file_key),
                        "0"
                )

        if (lastResult != null) {
            tvResult.text = lastResult
            Log.d(TAG, lastResult)
        }
    }
}
