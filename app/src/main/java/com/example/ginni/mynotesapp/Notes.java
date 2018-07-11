package com.example.ginni.mynotesapp;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Notes implements Serializable{


    Context context;
    public static ArrayList<Notes> notes = new ArrayList<Notes>();
    public static String fileName = "Notes.txt";
    String title,time,note;
    Date date = new Date();
    DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");

    Notes() {

    }

    Notes(Context context) {
        this.context = context;
    }


    public void addNote(String title,String note) {
        this.title = title;
        this.note = note;
        time = dateFormat.format(date);
    }

    public String getTime() {
        return time;
    }

    public String getTitle() {
        return title;
    }

    public String  getNote() {
        return note;
    }

    public void saveToFile(Context context) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static Notes readFromFile(Context context) {
        Notes notes = null;
        try {
            FileInputStream fileInputStream = context.openFileInput(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            notes = (Notes) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return notes;
    }

}
