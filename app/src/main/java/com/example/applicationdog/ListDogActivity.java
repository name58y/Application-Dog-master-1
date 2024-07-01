//หน้าสำหรับ'ist หมา
package com.example.applicationdog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ListDogActivity extends AppCompatActivity {

    private static final int REQUEST_RESULT_ACTIVITY = 1; // Unique request code

    private ArrayList<String> dogList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_dog);

        ListView listViewDogs = findViewById(R.id.listViewDogs);
        Button btnDog = findViewById(R.id.btnDog);
        Button btnHome = findViewById(R.id.btnhome);
        Button btnCamera = findViewById(R.id.btnCamera);

        // Initialize the dog list and adapter
        dogList = new ArrayList<>();
        dogList.add("Golden Retriever");
        dogList.add("Siberian Husky");
        dogList.add("Beagle");
        dogList.add("Thai Bangkaew");
        dogList.add("Chihuahua");

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dogList);
        listViewDogs.setAdapter(adapter);

        // Set button click listener for "Add Dog" button

        // Set button click listener for "Home" button
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity();
            }
        });

        // Set button click listener for "Camera" button
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToResultActivity();
            }
        });

        // Handle item click in ListView to remove "New Dog" item or go to DetailActivity
        listViewDogs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedDog = dogList.get(position);
                if (selectedDog.equals("New Dog")) {
                    removeNewDogItem(position);
                } else {
                    goToDetailActivity(selectedDog);
                }
            }
        });
    }


    private void goToMainActivity() {
        Intent intent = new Intent(ListDogActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void goToResultActivity() {
        // Consider passing data (optional) to ResultActivity
        Intent intent = new Intent(ListDogActivity.this, ResultActivity.class);
        startActivityForResult(intent, REQUEST_RESULT_ACTIVITY); // Use startActivityForResult for result handling
    }

    private void goToDetailActivity(String dogName) {
        Intent intent = new Intent(ListDogActivity.this, DetailActivity.class);
        intent.putExtra("dogName", dogName);
        startActivity(intent);
    }

    private void removeNewDogItem(int position) {
        if (dogList.get(position).startsWith("New Dog")) {
            dogList.remove(position);
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "Cannot remove existing dog", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_RESULT_ACTIVITY) {
            if (resultCode == RESULT_OK) {
                // Handle successful result from ResultActivity (if data is sent)
                if (data != null) {
                }
            }
        }
    }
}
