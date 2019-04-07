package com.example.macbookpro.ttcn_ngodanghieu.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.macbookpro.ttcn_ngodanghieu.Adapter.ChucNangAdapter;
import com.example.macbookpro.ttcn_ngodanghieu.Fragment.FragmentThongTinNhanVien;
import com.example.macbookpro.ttcn_ngodanghieu.Fragment.FragmetChiTietSPKho;
import com.example.macbookpro.ttcn_ngodanghieu.Login;
import com.example.macbookpro.ttcn_ngodanghieu.Model.ChucNang;
import com.example.macbookpro.ttcn_ngodanghieu.Model.User_Login;
import com.example.macbookpro.ttcn_ngodanghieu.R;

import java.util.ArrayList;

public class AdminHome extends AppCompatActivity {
    GridView gvChucNang;
    ImageView imgtuychonhome;
    ChucNangAdapter chucNangAdapter;
    ArrayList<ChucNang> chucNangArrayList;
    User_Login user;

    SharedPreferences ghinho;

    int level=-1;
    String ten = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        AnhXa();
        SharedPreferences ghinho;
        ghinho =getSharedPreferences("datalogin",MODE_PRIVATE);
//        Toast.makeText(this, ""+ghinho.getInt("level",0), Toast.LENGTH_SHORT).show();
//        int level =  intent.getIntExtra("level",0);
        ten =ghinho.getString("tenuser","");
        level =  ghinho.getInt("level",0);
        init();
        gvChucNang.setAdapter(chucNangAdapter);
        GirlChucNang();

        imgtuychonhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menupoptuychon();
            }
        });

    }

    private void AnhXa() {
        gvChucNang = (GridView) findViewById(R.id.gvhome) ;
        imgtuychonhome =(ImageView) findViewById(R.id.imgtuychonhome);
    }
    private void phanCapChuNang() {



//        Intent intent = getIntent();

        if(level ==1){
            chucNangArrayList.clear();
            chucNangArrayList.add(new ChucNang(0,R.drawable.thongke,"Quản lý tài chính"));
            chucNangArrayList.add(new ChucNang(1,R.drawable.qlnv,"Quản lý nhân viên"));
            chucNangArrayList.add(new ChucNang(2,R.drawable.qlkh,"Quan lý kho"));
            chucNangArrayList.add(new ChucNang(3,R.drawable.qlorder,"Quản lý Oder"));
//            chucNangArrayList.add(new ChucNang(4,R.drawable.khieunai,"Quản lý khiếu nại"));
//            chucNangArrayList.add(new ChucNang(5,R.drawable.qlnq,"Quản lý điều hành"));
            chucNangArrayList.add(new ChucNang(6,R.drawable.noiquy,"Nội quy"));
            chucNangArrayList.add(new ChucNang(7,R.drawable.notepad,"Note"));
        }else {
            chucNangArrayList.add(new ChucNang(3,R.drawable.qlorder,"Quản lý Oder"));
            chucNangArrayList.add(new ChucNang(7,R.drawable.notepad,"Note"));

        }

    }
    private void init() {
        // Khoi tao mang object chuc nang
        chucNangArrayList = new ArrayList<>();
        phanCapChuNang();
        //Khoi Tao adapter CHuc Nang
        chucNangAdapter = new ChucNangAdapter(AdminHome.this,R.layout.dong_chuc_nang,chucNangArrayList);

    }
    private void GirlChucNang() {
        gvChucNang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(level==1){
                    switch (position){
                        case 0:{
                            startActivity(new Intent(AdminHome.this,QuanLyTaiChinh.class));

                            break;
                        }
                        case 1:{
                            startActivity(new Intent(AdminHome.this,ListNhanVien.class));
                            break;
                        }
                        case 2:{
                            startActivity(new Intent(AdminHome.this,QuanLyKho.class));
                            break;
                        }
                        case 3:{
                            startActivity(new Intent(AdminHome.this,QuanLyOrder.class));
                            break;
                        }
                        case 4:{
                            Toast.makeText(AdminHome.this, "QLTC", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case 5:{
                            startActivity(new Intent(AdminHome.this,Note.class));
                            break;
                        }
                        case 6:{
                            Toast.makeText(AdminHome.this, "QLTC", Toast.LENGTH_SHORT).show();
                            break;
                        }
                        case 7:{

                            break;
                        }
                        case 8:{

                            break;
                        }
                    }
                }else{
                    switch (position){
                        case 0:{
                            startActivity(new Intent(AdminHome.this,QuanLyOrder.class));
                            break;
                        }
                        case 1:{
                            startActivity(new Intent(AdminHome.this,Note.class));
                            break;
                        }
                    }
                }
            }
        });

    }

    private void menupoptuychon(){
        PopupMenu popupMenu;
        popupMenu = new PopupMenu(this,imgtuychonhome);

        popupMenu.getMenuInflater().inflate(R.menu.menu_tuy_chon_home,popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.menu_popthongtinhome:{
                        FragmentThongTinNhanVien ttnv= new FragmentThongTinNhanVien();
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragthongtinhome,ttnv).commit();
                        Bundle bundle = new Bundle();
                        bundle.putInt("Level",level);
                        bundle.putString("tenUser",ten);
                        ttnv.setArguments(bundle);
                        break;
                    }
                    case R.id.menu_popdangxuathome:{
                        XacNhandangxuat();
                        break;
                    }
                }
                return true;
            }
        });
        popupMenu.show();
    }
    private void XacNhandangxuat(){
        AlertDialog.Builder alerDialog = new AlertDialog.Builder(this);
        alerDialog.setTitle("Thông báo");
//        alerDialog.setIcon(R.drawable.canhbao);

        alerDialog.setMessage("Bạn có muốn đăng xuất khỏi ứng dụng?" );
        alerDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent();
                intent.putExtra("ktra",3);
                setResult(RESULT_OK,intent);
//                startActivity(intent);
                finish();

//                finish();
//                startActivity(new Intent(AdminHome.this,Login.class));
            }
        });
        alerDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


            }
        });
        alerDialog.show();

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
