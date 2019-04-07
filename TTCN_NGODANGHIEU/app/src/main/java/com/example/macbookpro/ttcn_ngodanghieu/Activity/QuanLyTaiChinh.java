package com.example.macbookpro.ttcn_ngodanghieu.Activity;

import android.support.transition.FragmentTransitionSupport;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.macbookpro.ttcn_ngodanghieu.Adapter.ChucNangAdapter;
import com.example.macbookpro.ttcn_ngodanghieu.Fragment.FramgentChiTiet;
import com.example.macbookpro.ttcn_ngodanghieu.Model.ChucNang;
import com.example.macbookpro.ttcn_ngodanghieu.R;

import org.json.JSONArray;

import java.util.ArrayList;

public class QuanLyTaiChinh extends AppCompatActivity {
    GridView gv;
    ChucNangAdapter chucNangAdapter;
    ArrayList<ChucNang> chucNangArrayList;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_tai_chinh);
        AnhXa();
        fragmentManager = getSupportFragmentManager();
        chucNangArrayList = new ArrayList<>();
        chucNangArrayList.add(new ChucNang(2,R.drawable.thongke,"Theo tháng"));
        chucNangArrayList.add(new ChucNang(3,R.drawable.thongke,"Theo năm"));
        chucNangAdapter = new ChucNangAdapter(this,R.layout.dong_chuc_nang,chucNangArrayList);
        gv.setAdapter(chucNangAdapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:{
                        showFragment(position);
                        break;
                    }
                    case 1:{
                        showFragment(position);
                        break;
                    }

                }
            }
        });
    }
    private void AnhXa() {
        gv = (GridView) findViewById(R.id.gvQLTC) ;
    }
    private void showFragment(int position){
        FragmentTransaction fragmentTransitionSupport = fragmentManager.beginTransaction();
        FramgentChiTiet chiTiet = new FramgentChiTiet();
        fragmentTransitionSupport.add(R.id.fragmentchitiet,chiTiet,"chitiet");
        fragmentTransitionSupport.addToBackStack("chitiet");

        Bundle bundle = new Bundle();
        bundle.putInt("phanloai",position);
        chiTiet.setArguments(bundle);

        fragmentTransitionSupport.commit();

    }
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() >0){
            getFragmentManager().popBackStack();
        }else{
            super.onBackPressed();
        }

    }


}
