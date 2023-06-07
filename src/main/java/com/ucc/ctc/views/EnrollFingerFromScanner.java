package com.ucc.ctc.views;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import com.ucc.ctc.R;

import java.util.ArrayList;
import java.util.List;

public class EnrollFingerFromScanner extends AppCompatActivity {

	private SearchableSpinner searchableSpinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView( R.layout.tutorial_enroll_finger_from_scanner);

		searchableSpinner = findViewById(R.id.searchable_spinner);

		// Create a list of items
		List<String> items = new ArrayList<>();
		items.add("Item 1");
		items.add("Item 2");
		items.add("Item 3");
		items.add("Item 4");
		items.add("Item 5");

		// Create an ArrayAdapter with the item list
		ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// Set the adapter to the SearchableSpinner
		searchableSpinner.setAdapter(adapter);

		// Select an item programmatically
		int selectedIndex = 2; // Index of the item you want to select
		searchableSpinner.setSelection(selectedIndex);
	}
}
