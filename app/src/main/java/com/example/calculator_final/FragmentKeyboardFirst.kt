package com.example.calculator_final

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_keyboard_1.*

class FragmentKeyboardFirst : Fragment() {

    var communicator: Communicator? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        communicator = context as Communicator
        return inflater.inflate(R.layout.fragment_keyboard_1, container, false)
    }

    override fun onResume() {
        super.onResume()

        // Number
        btnNumZero.setOnClickListener { communicator?.respondData("number", "0") }
        btnNumOne.setOnClickListener { communicator?.respondData("number", "1") }
        btnNumTwo.setOnClickListener { communicator?.respondData("number", "2") }
        btnNumThree.setOnClickListener { communicator?.respondData("number", "3") }
        btnNumFour.setOnClickListener { communicator?.respondData("number", "4") }
        btnNumFive.setOnClickListener { communicator?.respondData("number", "5") }
        btnNumSix.setOnClickListener { communicator?.respondData("number", "6") }
        btnNumSeven.setOnClickListener { communicator?.respondData("number", "7") }
        btnNumEight.setOnClickListener { communicator?.respondData("number", "8") }
        btnNumNight.setOnClickListener { communicator?.respondData("number", "9") }

        // Operator
        btnPlus.setOnClickListener { communicator?.respondData("operator", "+") }
        btnMinus.setOnClickListener { communicator?.respondData("operator", "-") }
        btnMulti.setOnClickListener { communicator?.respondData("operator", "*") }
        btnDivide.setOnClickListener { communicator?.respondData("operator", "/") }
        btnMod.setOnClickListener { communicator?.respondData("operator", "%") }
        btnDot.setOnClickListener { communicator?.respondData("operator", ".") }

        // Delete
        btnDel.setOnClickListener { communicator?.respondData("delete", "delete") }

        // Clear
        btnAC.setOnClickListener { communicator?.respondData("clear", "clear") }

        // Calculating
        btnEquals.setOnClickListener { communicator?.respondData("calculating", "calculating") }

        // Change Keyboard
        btnChangeKeyboard.setOnClickListener { communicator?.respondData("changeKeyboard", "changeKeyboard") }
    }

}