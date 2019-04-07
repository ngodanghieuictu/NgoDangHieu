package com.example.macbookpro.ttcn_ngodanghieu.Fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.macbookpro.ttcn_ngodanghieu.R;

import static android.content.Context.MODE_PRIVATE;

public class FragmentThongTinNhanVien extends Fragment {
    TextView tvtenusser,tvchucvuusser;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_thongtin_home,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        tvtenusser = (TextView) view.findViewById(R.id.tvtenusser);
        tvchucvuusser = (TextView) view.findViewById(R.id.tvchucvuusser);
        Bundle bundle = getArguments();
        if (bundle!=null){
            int level = bundle.getInt("Level",0);
            String ten = bundle.getString("tenUser","");
            tvtenusser.setText("Họ Tên: "+ten);
            if(level==1){
                tvchucvuusser.setText("Chức vụ : Admin");
            }else {
                tvchucvuusser.setText("Chức vụ : Nhân viên");
            }
        }

        view.findViewById(R.id.imgexitthongthinhome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.fragthongtinhome)).commit();

            }
        });
    }
}
