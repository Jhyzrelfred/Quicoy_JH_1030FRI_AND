package com.quicoy.navigation;

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class TabList : Fragment() {

    private lateinit var itemList: ArrayList<Item>
    private lateinit var adapter: ItemAdapter
    private lateinit var listView: ListView
    private lateinit var editText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tab_list, container, false)

        listView = view.findViewById(R.id.listView)
        editText = view.findViewById(R.id.editText)
        val addButton: Button = view.findViewById(R.id.addButton)

        itemList = ArrayList()
        adapter = ItemAdapter(requireContext(), itemList) // Use requireContext() here
        listView.adapter = adapter

        addButton.setOnClickListener {
            val text = editText.text.toString()
            if (text.isNotEmpty()) {
                itemList.add(Item(text, false, R.drawable.ic_launcher_foreground)) // Dummy image
                adapter.notifyDataSetChanged()
                editText.text.clear()
            } else {
                Toast.makeText(requireContext(), "Please enter text", Toast.LENGTH_SHORT).show() // Use requireContext()
            }
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            showEditDeleteDialog(position)
        }

        return view
    }

    private fun showEditDeleteDialog(position: Int) {
        val dialogBuilder = AlertDialog.Builder(requireContext()) // Use requireContext() here
        dialogBuilder.setTitle("Edit or Delete")
        dialogBuilder.setMessage("Do you want to edit or delete this item?")
        dialogBuilder.setPositiveButton("Edit") { _, _ -> editItem(position) }
        dialogBuilder.setNegativeButton("Delete") { _, _ -> deleteItem(position) }
        dialogBuilder.show()
    }

    private fun editItem(position: Int) {
        val dialogBuilder = AlertDialog.Builder(requireContext()) // Use requireContext() here
        dialogBuilder.setTitle("Edit Item")

        val input = EditText(requireContext()) // Use requireContext() here
        input.setText(itemList[position].text)
        dialogBuilder.setView(input)

        dialogBuilder.setPositiveButton("Save") { _, _ ->
            itemList[position].text = input.text.toString()
            adapter.notifyDataSetChanged()
        }
        dialogBuilder.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }

        dialogBuilder.show()
    }

    private fun deleteItem(position: Int) {
        val dialogBuilder = AlertDialog.Builder(requireContext()) // Use requireContext() here
        dialogBuilder.setTitle("Are you sure you want to delete this item?")
        dialogBuilder.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }

        dialogBuilder.setPositiveButton("Delete") { _, _ ->
            itemList.removeAt(position)
            adapter.notifyDataSetChanged()
        }

        dialogBuilder.show()
    }
}
