package com.example.macbookpro.ttcn_ngodanghieu.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.macbookpro.ttcn_ngodanghieu.Activity.FullscreenActivity;
import com.example.macbookpro.ttcn_ngodanghieu.Fragment.FragmentXuatHang;
import com.example.macbookpro.ttcn_ngodanghieu.InterFace.GoToScream;
import com.example.macbookpro.ttcn_ngodanghieu.InterFace.ItemClickListener;
import com.example.macbookpro.ttcn_ngodanghieu.Model.LinkServer;
import com.example.macbookpro.ttcn_ngodanghieu.Model.QuanLyKho;
import com.example.macbookpro.ttcn_ngodanghieu.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class QuanLyKhoAdapter extends RecyclerView.Adapter<QuanLyKhoAdapter.ViewHolder> {
    private ArrayList<QuanLyKho> list;
    private Context context;
    private com.example.macbookpro.ttcn_ngodanghieu.Activity.QuanLyKho main;
    LinkServer linkServer = new LinkServer();


    public QuanLyKhoAdapter(ArrayList<QuanLyKho> list, Context context, com.example.macbookpro.ttcn_ngodanghieu.Activity.QuanLyKho main) {
        this.list = list;
        this.context = context;
        this.main = main;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.dong_quan_ly_kho,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.tensp.setText(list.get(i).getTensp());
        if (list.get(i).getSl()<=0){
            viewHolder.tinhtrangsp.setText("Het hang.");
        }else if(list.get(i).getSl()<=10){
            viewHolder.tinhtrangsp.setText("Sap het.");
        }else{
            viewHolder.tinhtrangsp.setText("Con hang.");
        }
        QuanLyKho qlk = list.get(i);
        String link = linkServer.getLocation()+qlk.getImg();
        Log.d("DANGHIEU",link);
        Picasso.with(context).load(qlk.getImg()).into(viewHolder.img);

        viewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                if (isLongClick){
                    Toast.makeText(context, "longclick", Toast.LENGTH_SHORT).show();
                    main.showXuatHang(list.get(position).getId(),list.get(position).getTensp(),list.get(position).getSl());
                }else{
                    main.showChiTiet(i);
//                        Intent intent = new Intent(main,FullscreenActivity.class);
//                        intent.putExtra("data",list.get(position));
//                        main.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{

        private ImageView img;
        private TextView tensp,tinhtrangsp;
        private ItemClickListener itemClickListener;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.imgqlk);
            tensp = (TextView) itemView.findViewById(R.id.tensp);
            tinhtrangsp = (TextView) itemView.findViewById(R.id.tinhtrangsp);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }
        public void setItemClickListener(ItemClickListener itemClickListener){
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),false);
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v,getAdapterPosition(),true);
            return true;
        }
    }

}
