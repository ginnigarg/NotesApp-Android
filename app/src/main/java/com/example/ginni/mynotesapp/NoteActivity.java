package com.example.ginni.mynotesapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class NoteActivity extends AppCompatActivity {

    EditText title,content;
    Notes notes = new Notes();
    Intent getIntent;
    String titleText,noteText;
    int index;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        title = findViewById(R.id.title);
        content = findViewById(R.id.content);
        getIntent = getIntent();
        titleText = getIntent.getStringExtra("title");
        noteText = getIntent.getStringExtra("note");
        index = getIntent.getIntExtra("index",-1);

        title.setText(titleText);
        content.setText(noteText);

        db = new Database(getApplicationContext());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            switch (item.getItemId()){
                case R.id.saveItem:
                    if(index >= 0)
                        editNote();
                    else
                        addNote();
                    break;

                case R.id.deleteItem:
                    openDialog();
                    break;
            }

        return  true;
    }

    private void editNote() {
        if(title.getText().toString().contentEquals("") || content.getText().toString().contentEquals("")) {
            Toast.makeText(getApplicationContext(),"All fields Necessary!!",Toast.LENGTH_SHORT).show();
        } else {
            notes.addNote(title.getText().toString(),content.getText().toString());
            //Notes.notes.set(index,notes);
            //Notes.setAt(getApplicationContext(),index,notes);
            db.editNote(notes);
            Toast.makeText(getApplicationContext(),"Note Successfully Edited",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(NoteActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    private void addNote() {
        if(title.getText().toString().contentEquals("") || content.getText().toString().contentEquals("")) {
            Toast.makeText(getApplicationContext(), "All fields Necessary!!", Toast.LENGTH_SHORT).show();
        } else {
            //notes.addNote(title.getText().toString(), content.getText().toString());
            //Notes.notes.add(notes);
            boolean check = db.addNote(title.getText().toString(),content.getText().toString());
            //Notes.add(getApplicationContext(),notes);
            if(check) {
                Toast.makeText(getApplicationContext(), "Note Successfully Saved", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NoteActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Note with same title already exists", Toast.LENGTH_SHORT).show();
                title.setText("");
                content.setText("");
            }

        }
    }


    private void openDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_box, null);
        dialogBuilder.setView(dialogView);

        dialogBuilder.setTitle("Delete Note\n");
        dialogBuilder.setMessage("Are You sure you want to delete this note?");
        dialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteNote();
                Toast.makeText(getApplicationContext(),"Note Deleted",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NoteActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        dialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"Note not Deleted",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(NoteActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        AlertDialog box = dialogBuilder.create();
        box.show();
    }

    private void deleteNote() {
        //Notes.notes.remove(index);
        //Notes.deleteAt(getApplicationContext(),index);
        db.deleteNote(title.getText().toString());
    }

}
