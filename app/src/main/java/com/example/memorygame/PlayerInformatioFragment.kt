package com.example.memorygame

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class PlayerInformatioFragment() : Fragment() {


    lateinit var nameEdit: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_playerinformation, container, false)


        nameEdit = view.findViewById<EditText>(R.id.editNameView)

        val button = view.findViewById<Button>(R.id.startGameButton)
        button.setOnClickListener {
            val intent = Intent(getActivity(), GameLogicActivity::class.java)
            intent.putExtra("playerName", nameEdit.text.toString())
            startActivity(intent)
        }
        return view
    }

    override fun onResume() {
        super.onResume()

        // Rensa din EditText här
        nameEdit.text.clear()
    }
}
