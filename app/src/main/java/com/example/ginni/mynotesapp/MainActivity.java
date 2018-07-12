package com.example.ginni.mynotesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView list;
    ArrayList<Notes> notesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();
        list = findViewById(R.id.listView);
        //notesList = Notes.notes;
        notesList = Notes.readFromFile(getApplicationContext());
        displayNotes();
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Notes note = notesList.get(i);
                Intent intent = new Intent(MainActivity.this,NoteActivity.class);
                intent.putExtra("title",note.getTitle());
                intent.putExtra("note",note.getNote());
                intent.putExtra("index",i);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.addItem:
                addNote();
                break;
        }
        return  true;
    }

    public void displayNotes() {
        if(notesList != null) {
            NoteAdapter noteAdapter = new NoteAdapter(getApplicationContext(),notesList);
            list.setAdapter(noteAdapter);
        } else {
            Toast.makeText(getApplicationContext(),"No Saved Notes Available",Toast.LENGTH_SHORT).show();
        }
    }

    public void addNote() {
        Intent intent = new Intent(MainActivity.this,NoteActivity.class);
        startActivity(intent);
    }

}
