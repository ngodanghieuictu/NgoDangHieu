package com.example.macbookpro.ttcn_ngodanghieu.Adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.macbookpro.ttcn_ngodanghieu.Model.ChucNang;
import com.example.macbookpro.ttcn_ngodanghieu.R;



import java.util.List;

public class ChucNangAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<ChucNang> listChucNang;

    public ChucNangAdapter(Context context, int layout, List<ChucNang> listChucNang) {
        this.context = context;
        this.layout = layout;
        this.listChucNang = listChucNang;
    }

    @Override
    public int getCount() {
        return listChucNang.size();
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
        ImageView imgChucNang;
        TextView tvChucNang;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            //Anh Xa -> donng_car_view_chuc_nang
            viewHolder = new ViewHolder();
            viewHolder.imgChucNang = (ImageView) convertView.findViewById(R.id.imgChucNang) ;
            viewHolder.tvChucNang  = (TextView) convertView.findViewById(R.id.tvChucnang);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        //Gan du lieu -> img,tv CHuc Nang
        viewHolder.imgChucNang.setImageResource(listChucNang.get(position).getImgChucNang());
        viewHolder.tvChucNang.setText(listChucNang.get(position).getTenChucNang());

        return convertView;
    }
}
