package com.myapplicationdev.android.p04_revisionnotes;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class SecondActivity extends AppCompatActivity {

	ListView lv;
	ArrayAdapter<Note> aa;
	ArrayList<Note> note;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//TODO implement the Custom ListView
		setContentView(R.layout.activity_second);

		lv = findViewById(R.id.lv);

		Intent i = getIntent();

		// get the String array named "info" we passed in
		ArrayList<String> data = i.getStringArrayListExtra("data");
		ArrayList<String> star = i.getStringArrayListExtra("star");

		//ArrayList<String> data = db.getNoteContent();
		//ArrayList<String> star = db.getStarContent();

		System.out.println(data);
		System.out.println(star);

		note = new ArrayList<Note>();

		for (int a = 0; a < data.size(); a++) {
			String one = data.get(a);
			int two = Integer.valueOf(star.get(a));
			note.add(new Note(a, one, two));
		}

		DBHelper db = new DBHelper(SecondActivity.this);
		note = db.getAllNotes();
		System.out.println(db.getAllNotes());
		db.close();

		aa = new RevisionNotesArrayAdapter(SecondActivity.this, R.layout.row, note);
		lv.setAdapter(aa);
		//System.out.println(db.getAllNotes());
		aa.notifyDataSetChanged();

	}
}
