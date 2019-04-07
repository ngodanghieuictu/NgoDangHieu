package com.example.macbookpro.ttcn_ngodanghieu.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.macbookpro.ttcn_ngodanghieu.Adapter.QuanLyKhoAdapter;
import com.example.macbookpro.ttcn_ngodanghieu.Fragment.FragmentXuatHang;
import com.example.macbookpro.ttcn_ngodanghieu.Fragment.FragmetChiTietSPKho;
import com.example.macbookpro.ttcn_ngodanghieu.Fragment.FramgentChiTiet;
import com.example.macbookpro.ttcn_ngodanghieu.InterFace.GoToScream;
import com.example.macbookpro.ttcn_ngodanghieu.Model.LinkServer;
import com.example.macbookpro.ttcn_ngodanghieu.Model.User_Login;
import com.example.macbookpro.ttcn_ngodanghieu.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QuanLyKho extends AppCompatActivity  implements GoToScream{
     ArrayList<com.example.macbookpro.ttcn_ngodanghieu.Model.QuanLyKho> quanLyKhos;
    RecyclerView recyclerView;
     QuanLyKhoAdapter quanLyKhoAdapter;
     LinkServer linkServer = new LinkServer();
     TextView mTextMessage;
     FragmentManager fragmentManager;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    quanLyKhos.clear();
                    getdata(linkServer.getUrlsp());
                    return true;
                case R.id.navigation_dashboard:
                    quanLyKhos.clear();
                    getdata(linkServer.getUrlsp()+"?loaisp=1");
                    return true;
                case R.id.navigation_notifications:
                    quanLyKhos.clear();
                    getdata(linkServer.getUrlsp()+"?loaisp=0");
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quan_ly_kho);
        fragmentManager =getSupportFragmentManager();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewqlk);
        quanLyKhos = new ArrayList<>();
        getdata(linkServer.getUrlsp());


        quanLyKhoAdapter = new QuanLyKhoAdapter(quanLyKhos,getApplicationContext(),this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayout.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(quanLyKhoAdapter);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    public void getdata(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, url, null
                , new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
//                int id, String img, String tensp, int sl, String ngaynhap, int id_user,String nsx, int loaisp
                for(int i=0;i<response.length();i++)
                {
                    try {
                        JSONObject object = response.getJSONObject(i);
                        quanLyKhos.add(new com.example.macbookpro.ttcn_ngodanghieu.Model.QuanLyKho(
                                object.getInt("ID"),
                                linkServer.getLocation()+object.getString("IMG"),
                                object.getString("TSP"),
                                object.getInt("SLSP"),
                                object.getString("NP"),
                                object.getInt("IDU"),
                                object.getString("NSX"),
                                object.getInt("LSP")
                        ));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                quanLyKhoAdapter.notifyDataSetChanged();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(QuanLyKho.this, "loi ket noi", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }


    @Override
    public void goTo(int positiom) {

    }
    public void TOast(int i){
        Toast.makeText(this, ""+i, Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_quan_ly_kho, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menuaddkho:
                Intent intent = new Intent(QuanLyKho.this,ThemHangKho.class);
                startActivityForResult(intent, 1);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            this.finish();
            startActivity(getIntent());


        }
    }
    public void showXuatHang(int id,final String tensp,int sl){
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    FragmentXuatHang xuathang = new FragmentXuatHang();
                    fragmentTransaction.replace(R.id.fragamentxuathang,xuathang);
                    fragmentTransaction.commit();
                    Bundle bundle = new Bundle();
                    bundle.putString("Tensp",tensp);
                    bundle.putInt("Idsp",id);
                    bundle.putInt("Slsp",sl);
                    xuathang.setArguments(bundle);

    }
    public void showChiTiet(int i){
        FragmetChiTietSPKho kho= new FragmetChiTietSPKho();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragchitiet,kho).commit();
        Bundle bundle = new Bundle();
        bundle.putSerializable("MangSP",quanLyKhos.get(i));
        kho.setArguments(bundle);
    }


}
