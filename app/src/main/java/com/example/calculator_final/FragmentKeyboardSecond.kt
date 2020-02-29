package com.example.calculator_final

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_keyboard_2.*

class FragmentKeyboardSecond : Fragment() {

    var communicator: Communicator? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        communicator = context as Communicator
        return inflater.inflate(R.layout.fragment_keyboard_2, container, false)
    }

    override fun onResume() {
        super.onResume()

        // Operator
        btnSin.setOnClickListener { communicator?.respondData("operator", "sin(") }
        btnCos.setOnClickListener { communicator?.respondData("operator", "cos(") }
        btnTan.setOnClickListener { communicator?.respondData("operator", "tan(") }
        btnCot.setOnClickListener { communicator?.respondData("operator", "cot(") }

        // Clear
        btnAC.setOnClickListener { communicator?.respondData("clear", "clear") }

        // Change Keyboard
        btnChangeKeyboard.setOnClickListener { communicator?.respondData("changeKeyboard", "changeKeyboard") }
    }
}