package com.example.calculator_final

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_constraints.*
import net.objecthunter.exp4j.ExpressionBuilder

class DesignActivity : AppCompatActivity(), Communicator {

    companion object {
        const val TAG: String = "TAG"
    }

    var isFragmentOneDisplay: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraints)

        /**
         * create first fragment keyboard
         */
        val fragmentTrans: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTrans.add(R.id.frameKeyboard, FragmentKeyboardFirst())
        fragmentTrans.commit()
        isFragmentOneDisplay = true
    }

    /**
     * get data returned from fragment
     */
    override fun respondData(key: String, value: String) {
        /**
         * if key = number -> value is appended to expression, isCanClear = true
         * if key = operator -> value is appended to expression, isCanClear = false
         * if key = delete -> delete last character on expression
         * if key = clear -> clear screen
         * if key = calculating -> calculating expression
         * if key = changeKeyboard -> change fragment
         */
        if (key.equals("number")) {
            appendOnExpression(value, true)
        } else if (key.equals("operator")) {
            appendOnExpression(value, false)
        } else if (key.equals("delete")) {
            if (tvExpression.text.isNotEmpty()) {
                tvExpression.text =
                    tvExpression.text.toString().substring(0, tvExpression.text.length - 1)
            }
        } else if (key.equals("clear")) {
            tvResult.text = ""
            tvExpression.text = ""
        } else if (key.equals("calculating")) {
            calculatingExpression()
        } else if (key.equals("changeKeyboard")) {
            changeKeyboard()
        }
    }

    /**
     * add character to expression
     */
    private fun appendOnExpression(value: String, isCanClear: Boolean) {

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

    /**
     * calculating expression
     */
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

    /**
     * call by btnChange on Fragment
     * change between two fragment
     */
    fun changeKeyboard() {
        var fragment: Fragment? = null

        if (isFragmentOneDisplay) {
            fragment = FragmentKeyboardSecond()
            isFragmentOneDisplay = false
        } else {
            fragment = FragmentKeyboardFirst()
            isFragmentOneDisplay = true
        }

        val fragmentTrans: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTrans.replace(R.id.frameKeyboard, fragment)
        fragmentTrans.commit()
    }
}