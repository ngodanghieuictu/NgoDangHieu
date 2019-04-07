package com.example.macbookpro.ttcn_ngodanghieu.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.macbookpro.ttcn_ngodanghieu.Model.OderMonAn;
import com.example.macbookpro.ttcn_ngodanghieu.R;

import java.util.List;

public class HoaDonAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<OderMonAn> list;

    public HoaDonAdapter(Context context, int layout, List<OderMonAn> list) {
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

    class ViewHolder {
        TextView stthoadon,tenthucdon,soluonghoadon,dongiahoadon,thanhtienhoadon;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);

            viewHolder.stthoadon = (TextView)convertView.findViewById(R.id.stthoadon);
            viewHolder.tenthucdon = (TextView) convertView.findViewById(R.id.tenthucdon);
//            viewHolder.donvihoadon = (TextView) convertView.findViewById(R.id.donvihoadon);
            viewHolder.soluonghoadon = (TextView) convertView.findViewById(R.id.soluonghoadon);
            viewHolder.dongiahoadon = (TextView) convertView.findViewById(R.id.dongiahoadon);
            viewHolder.thanhtienhoadon = (TextView) convertView.findViewById(R.id.thanhtienhoadon);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        OderMonAn hoadon = list.get(position);

        viewHolder.stthoadon.setText(String.valueOf(position+1));
        viewHolder.tenthucdon.setText(hoadon.getTenMonAn());
//        viewHolder.donvihoadon.setText("null");
        viewHolder.soluonghoadon.setText(String.valueOf(hoadon.getSl()));
        viewHolder.dongiahoadon.setText(String.valueOf(hoadon.getGia()));
        viewHolder.thanhtienhoadon.setText(String.valueOf(hoadon.getSl()*hoadon.getGia()));

        return convertView;
    }
}
