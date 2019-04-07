package com.example.macbookpro.ttcn_ngodanghieu.Activity;
import com.example.macbookpro.ttcn_ngodanghieu.Adapter.NoteAdapter;
import com.example.macbookpro.ttcn_ngodanghieu.Fragment.FragmentAddNote;
import com.example.macbookpro.ttcn_ngodanghieu.InterFace.SentdataNote;
import com.example.macbookpro.ttcn_ngodanghieu.R;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;

import java.util.ArrayList;

public class Note extends AppCompatActivity implements SentdataNote {
    NoteAdapter noteAdapter;
    ArrayList<com.example.macbookpro.ttcn_ngodanghieu.Model.Note> noteArrayList;
    ListView lvnote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        lvnote = findViewById(R.id.lvnote);
        noteArrayList = new ArrayList<>();
        noteAdapter = new NoteAdapter(this,R.layout.dong_note,noteArrayList);
        lvnote.setAdapter(noteAdapter);
        findViewById(R.id.addnote).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.rladdnote,new FragmentAddNote()).commit();
            }
        });
    }


    @Override
    public void sentNote(String title, String content) {
        noteArrayList.add(new com.example.macbookpro.ttcn_ngodanghieu.Model.Note(1,title,content));
        noteAdapter.notifyDataSetChanged();
//        getSupportFragmentManager().beginTransaction().remove(new FragmentAddNote()).commit();
//        getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.rladdnote)).commit();
//        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

    }

//    public static void hideKeyboard(Note activity) {
//        View view = activity.findViewById(android.R.id.content);
//        if (view != null) {
//            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Note.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
//        }
//    }
}
