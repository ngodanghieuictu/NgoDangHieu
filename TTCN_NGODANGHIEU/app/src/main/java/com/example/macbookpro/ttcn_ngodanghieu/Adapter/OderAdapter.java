package com.example.macbookpro.ttcn_ngodanghieu.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.macbookpro.ttcn_ngodanghieu.Activity.QuanLyOrder;
import com.example.macbookpro.ttcn_ngodanghieu.Fragment.FragmentMonAn;
import com.example.macbookpro.ttcn_ngodanghieu.Model.QuanLyKho;
import com.example.macbookpro.ttcn_ngodanghieu.R;

import com.example.macbookpro.ttcn_ngodanghieu.Model.OderMonAn;

import java.util.List;

public class OderAdapter extends BaseAdapter {
    private QuanLyOrder context;
    private int layout;
    private List<OderMonAn> list;

    public OderAdapter(QuanLyOrder context, int layout, List<OderMonAn> list) {
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
     class ViewHolder{
        TextView tenodersp,slodersp;
        ImageView imageView;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);

            viewHolder.tenodersp = (TextView) convertView.findViewById(R.id.tenodersp);
            viewHolder.slodersp = (TextView) convertView.findViewById(R.id.slodersp);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imgremovemonan);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        OderMonAn oderMonAn = list.get(position);
        viewHolder.tenodersp.setText(oderMonAn.getTenMonAn());
        viewHolder.slodersp.setText(oderMonAn.getSl()+"");
        viewHolder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.remove_monan(position);
            }
        });
        return convertView;
    }

}
