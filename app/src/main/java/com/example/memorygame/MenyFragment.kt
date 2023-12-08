package com.example.memorygame

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class MenyFragment() : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_meny, container, false)
        val button = view.findViewById<Button>(R.id.rulesButton)
        button.setOnClickListener {
            val intent = Intent(getActivity(), GameLogicActivity::class.java)
            startActivity(intent)
        }
        val nameButton = view.findViewById<Button>(R.id.nameButton)
        nameButton.setOnClickListener {
            val newFragment = PlayerInformatioFragment()
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.container, newFragment)
                addToBackStack(null)
                commit()
            }


        }
        return view

    }

}