package com.example.macbookpro.ttcn_ngodanghieu.Activity;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.macbookpro.ttcn_ngodanghieu.Adapter.AdapterNhanVien;
import com.example.macbookpro.ttcn_ngodanghieu.Login;
import com.example.macbookpro.ttcn_ngodanghieu.Model.LinkServer;
import com.example.macbookpro.ttcn_ngodanghieu.Model.User_Login;
import com.example.macbookpro.ttcn_ngodanghieu.R;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ListNhanVien extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<User_Login> nhanvienlist;
    AdapterNhanVien adapterNhanVien;
    LinkServer linkServer = new LinkServer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nhan_vien);
//        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));
        AnhXa();
        nhanvienlist = new ArrayList<>();
        getDataNhanVien(linkServer.getUrlqlnv());

        if(nhanvienlist!=null){
            adapterNhanVien = new AdapterNhanVien(nhanvienlist,getApplicationContext(),this);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setAdapter(adapterNhanVien);
        }
    }
    private void AnhXa() {
        recyclerView = ( RecyclerView) findViewById(R.id.mylistview);
    }
    private void getDataNhanVien(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i=0;i<response.length();i++)
                        {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                nhanvienlist.add(new User_Login(
                                        object.getInt("ID"),
                                        object.getString("TDN"),
                                        object.getString("MK"),
                                        object.getInt("LEVEL"),
                                        object.getString("HT")
                                ));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ListNhanVien.this,"Lỗi đường truyền,  vui lòng thử lại!", Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue.add(jsonArrayRequest);
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
                Intent intent = new Intent(ListNhanVien.this,ThemNhanSu.class);
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
            String namelogin = data.getStringExtra("name");
            String pass2login = data.getStringExtra("pass");
            String nameuserlogin = data.getStringExtra("nameuser");
            insert_ns(linkServer.getUrlinsertUser(),namelogin,pass2login,nameuserlogin,2);
            finish();
            startActivity(getIntent());
        }
    }
    private void insert_ns(String url1, final String name, final String pass, final String nameuser, int level) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest =new StringRequest(Request.Method.POST, url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("success"))
                        {
                            Toast.makeText(ListNhanVien.this, "Thêm thành công.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ListNhanVien.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params =new HashMap<>();
                params.put("TDN",name);
                params.put("MK",pass);
                params.put("LEVEL","2");
                params.put("HVT",nameuser);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    public void Deletens(final int idsp,int level){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,linkServer.getUrldeletenhansu(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success"))
                        {
                            finish();
                            startActivity(getIntent());
                            Toast.makeText(ListNhanVien.this, "Xóa thành công.", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(ListNhanVien.this, "Xóa thất bại! Vui lòng thử lại!", Toast.LENGTH_SHORT).show();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("ID",String.valueOf(idsp));
                return params;
            }
        };
        if (level==1){
            Toast_ns("Bạn không thể xoá Admin");
        }else {
            requestQueue.add(stringRequest);
        }
    }
    public void dialogCapnhatMatKhau(final int id){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_update_nhan_su);

        final EditText edtpass1 = (EditText) dialog.findViewById(R.id.dialogedtpass1);
        final EditText edtpass2 = (EditText) dialog.findViewById(R.id.dialogedtpass2);

        dialog.findViewById(R.id.dialogbtnhuycapnhat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.findViewById(R.id.dialogbtncapnhat).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dialogedtpass1 = edtpass1.getText().toString().trim();
                String dialogedtpass2 = edtpass2.getText().toString().trim();
                if (dialogedtpass1.isEmpty()||dialogedtpass2.isEmpty()){
                    Toast_ns("Mời bạn nhập lại mật khẩu muốn đổi!");
                }else{
                    if (dialogedtpass1.equalsIgnoreCase(dialogedtpass2)){
                        updateMatKhau(linkServer.getUrlupdatepassnhansu(),id,dialogedtpass2);
                            dialog.cancel();

                    }else {
                        Toast_ns("Mật khẩu không trùng nhau! Mời kiểm tra lại!");
                    }
                }
            }
        });
        dialog.show();

    }
    private void updateMatKhau(String urlupdate, final int idns, final String pass){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,urlupdate,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success"))
                        {
                            Toast.makeText(ListNhanVien.this, "Cập nhât thành công.", Toast.LENGTH_SHORT).show();
                        }else{

                            Toast.makeText(ListNhanVien.this, "Cập nhât thất bại! Vui lòng thử lại!", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("ID",String.valueOf(idns));
                params.put("MK",pass);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    private void Toast_ns(String str){
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

}
