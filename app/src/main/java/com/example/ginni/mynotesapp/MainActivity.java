package com.example.ginni.mynotesapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();

        list = findViewById(R.id.listView);

        if(isReadStoragePermissionGranted() && isWriteStoragePermissionGranted()) {
            db = new Database(getApplicationContext());
            notesList = db.getNotes();
            displayNotes();
        }
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
        if(notesList == null || notesList.size()==0) {
            Toast.makeText(getApplicationContext(),"No Saved Notes Available",Toast.LENGTH_SHORT).show();
        } else {
            NoteAdapter noteAdapter = new NoteAdapter(getApplicationContext(),notesList);
            list.setAdapter(noteAdapter);
        }
    }

    public void addNote() {
        Intent intent = new Intent(MainActivity.this,NoteActivity.class);
        startActivity(intent);
    }



    public  boolean isReadStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("permission","Permission is granted1");
                return true;
            } else {

                Log.v("permission","Permission is revoked1");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 3);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("permission","Permission is granted1");
            return true;
        }
    }

    public  boolean isWriteStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("permission","Permission is granted2");
                return true;
            } else {

                Log.v("permission","Permission is revoked2");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("permission","Permission is granted2");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0]== PackageManager.PERMISSION_GRANTED){
            Log.v("permission","Permission: "+permissions[0]+ "was "+grantResults[0]);
            //resume tasks needing this permission

        }
    }

}
