package com.example.ginni.mynotesapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NoteAdapter extends ArrayAdapter<Notes> {
    ArrayList<Notes> notes = new ArrayList<>();

    public NoteAdapter(@NonNull Context context, ArrayList<Notes> notes) {
        super(context, 0, notes);
        this.notes = notes;
    }


    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup
            parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_notes, null);
        }

        Notes note = getItem(position);

        if (note != null) {
            TextView title = convertView.findViewById(R.id.list_note_title);
            TextView date = convertView.findViewById(R.id.list_note_date);
            TextView content = convertView.findViewById(R.id.list_note_content);

            title.setText(note.getTitle());
            date.setText(note.getTime());
            content.setText(note.getNote());

        }
        return convertView;
    }

    @Nullable
    @Override
    public Notes getItem(int position) {
        return notes.get(position);
    }
}