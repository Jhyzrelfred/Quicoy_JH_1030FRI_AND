package com.quicoy.listview;

import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

        private ArrayList<Item> itemList;
        private ItemAdapter adapter;
        private ListView listView;
        private EditText editText;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            listView = findViewById(R.id.listView);
            editText = findViewById(R.id.editText);
            Button addButton = findViewById(R.id.addButton);

            itemList = new ArrayList<>();
            adapter = new ItemAdapter(this, itemList);
            listView.setAdapter(adapter);

            addButton.setOnClickListener(v -> {
                String text = editText.getText().toString();
                if (!text.isEmpty()) {
                    itemList.add(new Item(text, false, R.drawable.ic_launcher_foreground)); // Dummy image
                    adapter.notifyDataSetChanged();
                    editText.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Please enter text", Toast.LENGTH_SHORT).show();
                }
            });


            listView.setOnItemClickListener((parent, view, position, id) -> showEditDeleteDialog(position));
        }

        private void showEditDeleteDialog(int position) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            dialogBuilder.setTitle("Edit or Delete");
            dialogBuilder.setMessage("Do you want to edit or delete this item?");
            dialogBuilder.setPositiveButton("Edit", (dialog, which) -> editItem(position));
            dialogBuilder.setNegativeButton("Delete", (dialog, which) ->deleteItem(position)   );
            dialogBuilder.show();
        }



        private void editItem(int position) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
            dialogBuilder.setTitle("Edit Item");

            final EditText input = new EditText(this);
            input.setText(itemList.get(position).getText());
            dialogBuilder.setView(input);

            dialogBuilder.setPositiveButton("Save", (dialog, which) -> {
                itemList.get(position).setText(input.getText().toString());
                adapter.notifyDataSetChanged();
            });
            dialogBuilder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

            dialogBuilder.show();
        }

    private void deleteItem(int position) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Are you sure you want to delete this item?");
        dialogBuilder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

        dialogBuilder.setPositiveButton("Delete", (dialog, which) -> {
            itemList.remove(position);
            adapter.notifyDataSetChanged();
        });

        dialogBuilder.show();
    }


}