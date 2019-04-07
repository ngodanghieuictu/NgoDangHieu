package com.example.macbookpro.ttcn_ngodanghieu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.macbookpro.ttcn_ngodanghieu.Activity.AdminHome;
import com.example.macbookpro.ttcn_ngodanghieu.Model.LinkServer;
import com.example.macbookpro.ttcn_ngodanghieu.Model.User_Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    EditText edttenlogin,edtmklogin;
    CheckBox ckblogin;
    Button btnlogin;
    ArrayList<User_Login> user_logins;
    LinkServer linkServer =new LinkServer();
    SharedPreferences ghinho;

    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
//        int a = getIntent().getIntExtra("ktra",0);
        kiemtradangnhap();
        AnhXa();
        hienthiuser();
        Init();



        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XuLyLogin();

            }
        });
    }
    private void Init() {
        user_logins = new ArrayList<>();

    }
    private void Thongbao(String thongbao){
        Toast.makeText(this, thongbao, Toast.LENGTH_SHORT).show();
    }
    private void XuLyLogin() {
        String tdn = edttenlogin.getText().toString();
        String mk = edtmklogin.getText().toString();

        if (tdn.isEmpty()){
            Thongbao("Không được bỏ trống trường tên đăng nhập! Mời bạn nhâp!");
        }else if(mk.isEmpty()){
            Thongbao("Không được bỏ trống trường mật khẩu đăng nhập! Mời bạn nhâp!");
        }else if(tdn.isEmpty()&&mk.isEmpty()){
            Thongbao("Không được bỏ trống  trường đăng nhập! Mời bạn nhâp!");
        }else{
//            Getuser("http://192.168.1.58/TTCN_NGODANGHIEU/Model/get_user.php?name="+tdn+"&pass="+mk);
            Getuser(linkServer.getUrlLogin()+"?name="+tdn+"&pass="+mk);
//            Toast.makeText(this, ""+linkServer.getUrlLogin(), Toast.LENGTH_SHORT).show();
        }

    }
    private Boolean Ktra(){
        String tdn = edttenlogin.getText().toString().trim();
        String mk  = edtmklogin.getText().toString().trim();

        if(tdn.isEmpty()&&mk.isEmpty()){
            Toast.makeText(Login.this,"Vui lòng nhập tài khoản, mật khẩu!",Toast.LENGTH_SHORT).show();
        }else if(tdn.isEmpty()){
            Toast.makeText(Login.this, "Vui lòng nhập tên tài khoản!", Toast.LENGTH_SHORT).show();
        }else if(mk.isEmpty()){
            Toast.makeText(Login.this, "Vui lòng nhập mật khẩu!", Toast.LENGTH_SHORT).show();
        }else{

        }

        return true;
    }
    //Hiển thị tên đăng nhập mật khẩu khi đc ghi nhớ
    private void hienthiuser(){
        ghinho =getSharedPreferences("datalogin",MODE_PRIVATE);
        edttenlogin.setText(ghinho.getString("tdn",""));
        edtmklogin.setText(ghinho.getString("mk",""));
        ckblogin.setChecked(ghinho.getBoolean("ckblogin",false));
    }
    //Xử lý ghi nhớ tài khoan
    private void  KiemtraCkb(String nameuser,String passuser){
        if(ckblogin.isChecked()){
            SharedPreferences.Editor editor = ghinho.edit();
            editor.putString("tdn",nameuser);
            editor.putString("mk",passuser);
            editor.putBoolean("ckblogin",true);
            editor.commit();
        }else {
            SharedPreferences.Editor editor = ghinho.edit();
            editor.remove("tdn");
            editor.remove("mk");
            editor.remove("ckblogin");
            editor.commit();
        }
    }
    //lấy dữ liệu table_user
    private void Getuser(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        user_logins.clear();
                        for(int i=0;i<response.length();i++)
                        {
                            try {
                                JSONObject object = response.getJSONObject(i);
                                user_logins.add(new User_Login(
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
                        if (user_logins.size()==1){
                            int lv =user_logins.get(0).getLevel();
                            String tenuser=user_logins.get(0).getName_usser();
                            if (lv==1){
                                KiemtraCkb(user_logins.get(0).getNamelogin_user(),user_logins.get(0).getPass_user());
                                Intent intent = new Intent(Login.this,AdminHome.class);
                                SharedPreferences.Editor editor = ghinho.edit();
                                editor.putInt("level",lv);
                                editor.putString("tenuser",tenuser);
                                editor.commit();
//                                intent.putExtra("tenuser",user_logins.get(0).getName_usser());
//                                intent.putExtra("level",user_logins.get(0).getLevel());
//                                startActivity(intent);
                                startActivityForResult(intent, 2);
//                                finish();
                            }else if(lv>1){
                                KiemtraCkb(user_logins.get(0).getNamelogin_user(),user_logins.get(0).getPass_user());
                                Intent intent = new Intent(Login.this,AdminHome.class);
                                SharedPreferences.Editor editor = ghinho.edit();
                                editor.putInt("level",lv);
                                editor.putString("tenuser",tenuser);
                                editor.commit();
//                                intent.putExtra("tenuser",user_logins.get(0).getName_usser());
//                                intent.putExtra("level",user_logins.get(0).getLevel());
//                                startActivity(intent);
                                startActivityForResult(intent, 2);
//                                finish();
                            }
                        }else {
                            Thongbao("Sai tên đăng nhập hoặc mật khẩu!");
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this,"Lỗi đường truyền,  vui lòng thử lại!", Toast.LENGTH_SHORT).show();
            }
        }
        );
        requestQueue.add(jsonArrayRequest);
    }
    private void insert_ns(String url1) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest =new StringRequest(Request.Method.POST, url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JSONArray jsonArray =null;
                        try {
                            jsonArray = new JSONArray(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        try {
                            JSONObject user = jsonArray.getJSONObject(0);
                            int id = user.getInt("ID");
                            String tdn =user.getString("TDN");
                            String mk  = user.getString("MK");
                            int level = user.getInt("LEVEL");
                            String hoten = user.getString("HT");
                            user_logins.add(new User_Login(id,tdn,mk,level,hoten));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Login.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params =new HashMap<>();
                params.put("nameloginuser",edttenlogin.getText().toString().trim());
                params.put("passuser",edtmklogin.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    // Xử lý kết nối các component vs file java
    private void AnhXa() {
        img = (ImageView) findViewById(R.id.logologin);
        edtmklogin  = (EditText) findViewById(R.id.edtmklogin);
        edttenlogin = (EditText) findViewById(R.id.edttenlogin);
        ckblogin    = (CheckBox) findViewById(R.id.ckblogin);
        btnlogin    = (Button) findViewById(R.id.btnlogin);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("KIEMTRA", requestCode +"|"+resultCode+"|"+data.putExtra("ktra",0));
        if (requestCode == 2 && resultCode == RESULT_OK) {
            xoadangnhap();
        }
    }
    private void xoadangnhap(){
        SharedPreferences.Editor editor = ghinho.edit();
        editor.remove("level");
        editor.remove("tenuser");
        editor.commit();
    }
    private void kiemtradangnhap(){
        ghinho =getSharedPreferences("datalogin",MODE_PRIVATE);
        int dangnhap= ghinho.getInt("level",0);
       if (dangnhap!=0){
//           xoadangnhap();

           Log.d("LEVEL",dangnhap+"");
           startActivityForResult(new Intent(Login.this,AdminHome.class),2);
       }

    }
}
