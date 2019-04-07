package com.example.macbookpro.ttcn_ngodanghieu.Activity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.macbookpro.ttcn_ngodanghieu.Model.LinkServer;
import com.example.macbookpro.ttcn_ngodanghieu.R;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class ThemNhanSu extends AppCompatActivity {
    EditText edtnamelogin,edtpasslogin,edtpass2login,edtnameuserlogin;
    LinkServer linkServer = new LinkServer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_nhan_su);
        AnhXa();
    }

    private void AnhXa() {
        edtnamelogin = (EditText) findViewById(R.id.edtnamelogin);
        edtpasslogin = (EditText) findViewById(R.id.edtpasslogin);
        edtpass2login = (EditText) findViewById(R.id.edtpass2login);
        edtnameuserlogin = (EditText) findViewById(R.id.edtnameuserlogin);

    }
    private void Ktra(){
        String namelogin = edtnamelogin.getText().toString();
        String nameuserlogin = edtnameuserlogin.getText().toString();
        String passlogin = edtpasslogin.getText().toString();
        String pass2login = edtpass2login.getText().toString();

        if (namelogin.isEmpty()){
            Toat_ns("Không được bỏ trống trường tên đăng nhập! Mời bạn nhập lại!");
        }else if (passlogin.isEmpty()){
            Toat_ns("Không được bỏ trống trường mật khẩu! Mời bạn nhập lại!");
        }else if(pass2login.isEmpty()){
            Toat_ns("Không được bỏ trống trường mật khẩu! Mời bạn nhập lại!");
        }else if (nameuserlogin.isEmpty()){
            Toat_ns("Không được bỏ trống trường họ tên! Mời bạn nhập lại!");
        }else if (namelogin.isEmpty()&&passlogin.isEmpty()&&pass2login.isEmpty()&&nameuserlogin.isEmpty()){
            Toat_ns("Các trường không đưuocj bỏ trống! Mời bạn nhập lại!");
        }else {
            if (passlogin.equalsIgnoreCase(pass2login)){
                Intent i = new Intent();
                i.putExtra("name", namelogin);
                i.putExtra("pass", pass2login);
                i.putExtra("nameuser", nameuserlogin);
                setResult(RESULT_OK, i);
                finish();

            }else {
                Toat_ns("Mật khẩu không giống nhau! Mời bạn nhập lại!");
            }
        }

    }
    private void Toat_ns(String str){
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    public void themNhanSu(View view){
        Ktra();
    }
    public void huyNhanSu(View view){
        finish();
    }


}
