package com.example.macbookpro.ttcn_ngodanghieu.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.macbookpro.ttcn_ngodanghieu.R;

import com.example.macbookpro.ttcn_ngodanghieu.Model.BaoCaoThongKe;

import java.util.List;

public class BaoCaoThongKeAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<BaoCaoThongKe> baoCaoThongKeList;

    public BaoCaoThongKeAdapter(Context context, int layout, List<BaoCaoThongKe> baoCaoThongKeList) {
        this.context = context;
        this.layout = layout;
        this.baoCaoThongKeList = baoCaoThongKeList;
    }

    @Override
    public int getCount() {
        return baoCaoThongKeList.size();
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
        private TextView txtvstt,txtvtenhang,txtvslhang,txtvgiahang;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);

            viewHolder.txtvstt = convertView.findViewById(R.id.stt);
            viewHolder.txtvtenhang = convertView.findViewById(R.id.tenhang);
            viewHolder.txtvslhang = convertView.findViewById(R.id.slhang);
            viewHolder.txtvgiahang = convertView.findViewById(R.id.giahang);

            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        BaoCaoThongKe ds =baoCaoThongKeList.get(position);
        viewHolder.txtvstt.setText(String.valueOf(position+1));
        viewHolder.txtvgiahang.setText(String.valueOf(ds.getGia()));
        viewHolder.txtvslhang.setText(String.valueOf(ds.getSl()));
        viewHolder.txtvtenhang.setText(ds.getTenhang());

        return convertView;
    }
}
