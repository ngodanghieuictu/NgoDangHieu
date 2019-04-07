package com.example.macbookpro.ttcn_ngodanghieu.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;

import com.example.macbookpro.ttcn_ngodanghieu.Activity.ListNhanVien;
import com.example.macbookpro.ttcn_ngodanghieu.InterFace.ItemClickListener;
import com.example.macbookpro.ttcn_ngodanghieu.Model.User_Login;
import com.example.macbookpro.ttcn_ngodanghieu.R;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdapterNhanVien extends RecyclerView.Adapter<AdapterNhanVien.ViewHolder> {
    private ArrayList<User_Login> list;
    private Context context;
    ListNhanVien listNhanVien;

    public AdapterNhanVien(ArrayList<User_Login> list, Context context, ListNhanVien listNhanVien) {
        this.list = list;
        this.context = context;
        this.listNhanVien = listNhanVien;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.dong_nhan_vien,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        viewHolder.tenNhanVien.setText(list.get(i).getName_usser());
        if (list.get(i).getLevel()==1){
            viewHolder.chucVuNhanVien.setText("Admin");
        }else{
            viewHolder.chucVuNhanVien.setText("Nhan vien");
        }
        viewHolder.imgNhanVien.setImageResource(R.drawable.ic_user);
        viewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, final int position, boolean isLongClick) {
                PopupMenu popupMenu;
                popupMenu = new PopupMenu(context,viewHolder.imgtuychon);

                popupMenu.getMenuInflater().inflate(R.menu.menu_nhan_su,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.menu_popsuans:{
                                listNhanVien.dialogCapnhatMatKhau(list.get(position).getId());
                                break;
                            }
                            case R.id.menu_popxoans:{
                                XacNhanXoa(list.get(position).getName_usser(),list.get(position).getId(),list.get(position).getLevel());
                                break;
                            }
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
        private ImageView imgNhanVien,imgtuychon;
        private TextView tenNhanVien,chucVuNhanVien;

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        private ItemClickListener itemClickListener;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgNhanVien = (ImageView) itemView.findViewById(R.id.imgNhanVien);
            tenNhanVien = (TextView) itemView.findViewById(R.id.txtvTenNhanVien);
            chucVuNhanVien = (TextView) itemView.findViewById(R.id.txtvChucVu);
            imgtuychon = (ImageView) itemView.findViewById(R.id.imgtuychon);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {
            this.itemClickListener.onClick(v,getAdapterPosition(),false);

        }

        @Override
        public boolean onLongClick(View v) {
            this.itemClickListener.onClick(v,getAdapterPosition(),true);
            return true;
        }
    }
    private void XacNhanXoa(String ten, final int idsp,final int level){
        AlertDialog.Builder alerDialog = new AlertDialog.Builder(listNhanVien);
        alerDialog.setTitle("Thông báo");
//        alerDialog.setIcon(R.drawable.canhbao);
        alerDialog.setMessage("Bạn có muốn xóa nhân viên: ' "+ten+" ' này không ?" );
        alerDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listNhanVien.Deletens(idsp,level);
            }
        });
        alerDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alerDialog.show();

    }

}
