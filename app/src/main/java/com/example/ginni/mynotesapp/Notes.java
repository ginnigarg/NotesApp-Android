package com.example.ginni.mynotesapp;

import android.content.Context;
import android.util.Log;

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
import java.util.List;

public class Notes implements Serializable{


    //Context context;
//    public static ArrayList<Notes> notes = new ArrayList<Notes>();
//    private static String fileName = "notes.txt";
//    static File file = new File(fileName);
    private String title,time,note;
//    private Date date = new Date();
//    private DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh:mm:ss");

    Notes() {
    }

    public void addNote(String title,String note) {
        this.title = title;
        this.note = note;
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

//    private static void saveToFile(Context context,ArrayList<Notes> notes) {
//        try {
//
//            FileOutputStream fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
//            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
//            objectOutputStream.writeObject(notes);
//            objectOutputStream.close();
//            fileOutputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setNote(String note) {
        this.note = note;
    }

//    public static ArrayList<Notes> readFromFile(Context context) {
//        ArrayList<Notes> notesArrayList = null;
//        try {
//            //file.createNewFile();
//            //FileInputStream fileInputStream = new FileInputStream(file);
//            FileInputStream fileInputStream = context.openFileInput(fileName);
//            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
//            notesArrayList = (ArrayList<Notes>) objectInputStream.readObject();
//            objectInputStream.close();
//            fileInputStream.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        return notesArrayList;
//    }
//
//
//    public static void setAt(Context context,int index,Notes newObject) {
//        try{
//            ArrayList<Notes> notesArrayList = readFromFile(context);
//            notesArrayList.set(index,newObject);
//            saveToFile(context,notesArrayList);
//        } catch (Exception e) {
//            Log.d("Setting","Error in Setting");
//            e.printStackTrace();
//        }
//    }
//
//    public static void add(Context context,Notes notes) {
//        try {
//            ArrayList<Notes> notesArrayList = readFromFile(context);
//            notesArrayList.add(notes);
//            saveToFile(context,notesArrayList);
//        } catch (Exception e) {
//            Log.d("Add","Error in Adding");
//            e.printStackTrace();
//        }
//    }
//
//    public static  void deleteAt(Context context,int index) {
//        try {
//            ArrayList<Notes> notesArrayList = readFromFile(context);
//            notesArrayList.remove(index);
//            saveToFile(context,notesArrayList);
//        } catch (Exception e) {
//            Log.d("Delete","Error in Deleting");
//            e.printStackTrace();
//        }
//    }


}
