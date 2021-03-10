package com.stark.nextadventure.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.stark.nextadventure.R

class Login : Fragment() {

    private val navControl by lazy{
        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.button).apply {
            this.setOnClickListener {
               val direction = LoginDirections.actionLoginToDashboard()
                navControl.navigate(direction)
            }
        }

        view.findViewById<Button>(R.id.button_signup).apply {
            this.setOnClickListener {
                val directions = LoginDirections.actionLoginToSignUp()
                navControl.navigate(directions)
            }
        }
    }
}