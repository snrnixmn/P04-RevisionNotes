package com.myapplicationdev.android.p04_revisionnotes;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsertNote, btnShowList;
    EditText editTextNote;
    RadioGroup radioGroupstars;
    ArrayList<Note> notes;
    ArrayAdapter<RevisionNotesArrayAdapter> aa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsertNote = findViewById(R.id.buttonInsertNote);
        btnShowList = findViewById(R.id.buttonShowList);
        editTextNote = findViewById(R.id.editTextNote);

        btnInsertNote.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);

                // Get RadioGroup object
                RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroupStars);

                // get id of selected radio button in radiogroup
                int selectedButtonId = rg.getCheckedRadioButtonId();

                // get radio button object from id we had gotten above
                RadioButton rb = (RadioButton) findViewById(selectedButtonId);

                int selected = Integer.valueOf(rb.getText().toString());

                Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_LONG).show();

                // Insert a task
                db.insertNote(editTextNote.getText().toString(), selected);
                db.close();
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                DBHelper db = new DBHelper(MainActivity.this);

                // Insert a task
                ArrayList<String> data = db.getNoteContent();
                ArrayList<String> newdata = db.getNoteContent();

                ArrayList<String> star = db.getStarContent();

                notes = db.getAllNotes();

                System.out.println(data.size());

                // create an intent to start another activity called
                // DemoActivities2 (which we would create later)
               Intent i = new Intent(MainActivity.this, SecondActivity.class);

                //pass the string array holding the name and age into new activity
               i.putExtra("data", data);
               i.putExtra("star", star);

                // start new activity
               startActivity(i);

                notes.clear();

                db.close();

            }
        });
    }
}
