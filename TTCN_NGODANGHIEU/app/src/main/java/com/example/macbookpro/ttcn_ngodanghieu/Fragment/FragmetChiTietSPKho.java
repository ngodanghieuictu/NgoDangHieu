package com.example.macbookpro.ttcn_ngodanghieu.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.macbookpro.ttcn_ngodanghieu.Model.LinkServer;
import com.example.macbookpro.ttcn_ngodanghieu.Model.QuanLyKho;
import com.example.macbookpro.ttcn_ngodanghieu.R;
import com.squareup.picasso.Picasso;

public class FragmetChiTietSPKho extends Fragment {
    LinkServer linkServer = new LinkServer();
    ImageView imghinh,imgexit;
    TextView tvten,tvsoluong,tvngay,tvnsx;
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chi_tiet_sp_kho,container,false);
        Bundle bundle = getArguments();
        AnhXa();
        if (bundle!=null){
            QuanLyKho quanLyKho = (QuanLyKho) bundle.getSerializable("MangSP");
//            Picasso.with(getContext()).load(linkServer.getLocation()+quanLyKho.getImg()).into(imghinh);
            Picasso.with(getContext()).load(quanLyKho.getImg()).into(imghinh);
            tvten.setText("Tên sane phẩm: "+quanLyKho.getTensp());
            tvsoluong.setText("Số lượng: "+quanLyKho.getSl());
            tvngay.setText("Ngày nhập: "+quanLyKho.getNgaynhap());
            tvnsx.setText("Nhà sản xuất: "+quanLyKho.getNsx());
        }
        imgexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.fragchitiet)).commit();
            }
        });


        return view;
    }

    private void AnhXa() {
        imghinh = view.findViewById(R.id.imgchitiethangkho);
        tvten   = view.findViewById(R.id.tvchitiettenhang);
        tvsoluong   = view.findViewById(R.id.tvsoluongchitiethang);
        tvngay   = view.findViewById(R.id.tvngaynhaphang);
        tvnsx   = view.findViewById(R.id.tvnhasanxuat);
        imgexit   = view.findViewById(R.id.imgexit);
    }


}
