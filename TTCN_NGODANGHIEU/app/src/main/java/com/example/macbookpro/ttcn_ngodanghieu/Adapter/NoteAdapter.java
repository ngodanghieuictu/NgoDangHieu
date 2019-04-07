package com.example.macbookpro.ttcn_ngodanghieu.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.macbookpro.ttcn_ngodanghieu.Model.Note;
import com.example.macbookpro.ttcn_ngodanghieu.R;

import java.util.List;

public class NoteAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Note> list;

    public NoteAdapter(Context context, int layout, List<Note> list) {
        this.context = context;
        this.layout = layout;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
class  ViewHolder{
        private TextView tvstt,tvtitle;
        private ImageView img;
}
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView ==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.dong_note,null);

            viewHolder.tvstt = convertView.findViewById(R.id.sttnote);
            viewHolder.tvtitle = convertView.findViewById(R.id.titlenote);
            viewHolder.img = convertView.findViewById(R.id.imgremovenote);



            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Note note = list.get(position);

        viewHolder.tvstt.setText(String.valueOf(position+1));
        viewHolder.tvtitle.setText(note.getTitle());
        viewHolder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "clik"+position, Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }
}
