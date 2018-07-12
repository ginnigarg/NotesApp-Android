package com.example.ginni.mynotesapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Database {
    SQLiteDatabase database;
    Context context;
    private Date date = new Date();
    private DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");

    Database(Context context) {
        this.context = context;
        database = context.openOrCreateDatabase("noteDatabase",Context.MODE_PRIVATE,null);
        try {
            database.execSQL("CREATE TABLE IF NOT EXISTS NOTES (title VARCHAR primary key,content VARCHAR,time VARCHAR)");
        } catch (Exception e) {
            throw e;
        }

    }

    public ArrayList<Notes> getNotes() {
        Cursor c = database.rawQuery("SELECT * FROM  NOTES",null);
        ArrayList<Notes> notes = new ArrayList<>();
        if(c.moveToFirst()) {
            do {
                Notes note = new Notes();
                int index = c.getColumnIndex("title");
                note.setTitle(c.getString(index));
                index = c.getColumnIndex("content");
                note.setNote(c.getString(index));
                index = c.getColumnIndex("time");
                note.setTime(c.getString(index));
                notes.add(note);
            } while (c.moveToNext());
        }
        return  notes;
    }

    public boolean addNote(String title,String content) {
        try {
            String time = dateFormat.format(date);
            Log.e("TITLE",title);
            Log.e("CONTENT",content);
            Log.e("TIME",time);
            database.execSQL("INSERT INTO NOTES VALUES('" +title+ "', '" +content+ "', '" +time+ "')");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void deleteNote(String title) {
        try{
            database.execSQL("DELETE FROM NOTES WHERE title = '"+title+"'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editNote(Notes note) {
        try {
            database.execSQL("UPDATE NOTES SET content = '"+note.getNote()+"' WHERE title = '"+note.getTitle()+"'");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
