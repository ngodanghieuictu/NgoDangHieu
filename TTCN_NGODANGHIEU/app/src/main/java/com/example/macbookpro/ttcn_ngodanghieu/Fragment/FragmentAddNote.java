package com.example.macbookpro.ttcn_ngodanghieu.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.macbookpro.ttcn_ngodanghieu.InterFace.SentDataMonAn;
import com.example.macbookpro.ttcn_ngodanghieu.InterFace.SentdataNote;
import com.example.macbookpro.ttcn_ngodanghieu.R;

public class FragmentAddNote extends Fragment {
    SentdataNote sentdataNote;
        EditText edttitle,edtcontent;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_add_note,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    edttitle = view.findViewById(R.id.addtitlenote);
    edtcontent = view.findViewById(R.id.addcontentnote);
    view.findViewById(R.id.huyaddnote).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.rladdnote)).commit();
        }
    });
    view.findViewById(R.id.themaddnote).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String title = edttitle.getText().toString().trim();
        String content = edttitle.getText().toString().trim();

        if (title.isEmpty()){
            Toast.makeText(getActivity(), "Khong duoc bo trong title", Toast.LENGTH_SHORT).show();
        }else if (content.isEmpty()){
            Toast.makeText(getActivity(), "Khong duoc bo trong truong content", Toast.LENGTH_SHORT).show();
        }else {
            sentdataNote.sentNote(title,content);
            getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.rladdnote)).commit();
        }
    }
    });
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        sentdataNote = (SentdataNote) getActivity();
    }


}
