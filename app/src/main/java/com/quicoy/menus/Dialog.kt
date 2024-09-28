package com.quicoy.menus

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Dialog : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): AlertDialog {
        return AlertDialog.Builder(requireContext())
            .setTitle("EXIT OR FIRST PAGE")
            .setMessage("Choose only One.")
            .setPositiveButton("Go to First") { _, _ ->
                // Navigate to the first fragment
                (activity as MainActivity).loadFragment(FirstFragment())
            }
            .setNegativeButton("Go to Exit") { _, _ ->
                // Exit the app
                (activity as MainActivity).finish()
            }
            .create()
    }
}