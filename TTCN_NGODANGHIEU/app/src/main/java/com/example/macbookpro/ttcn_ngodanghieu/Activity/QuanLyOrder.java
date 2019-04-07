package com.example.macbookpro.ttcn_ngodanghieu.Activity;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.macbookpro.ttcn_ngodanghieu.Adapter.HoaDonAdapter;
import com.example.macbookpro.ttcn_ngodanghieu.Adapter.OderAdapter;
import com.example.macbookpro.ttcn_ngodanghieu.Fragment.FragmentMonAn;
import com.example.macbookpro.ttcn_ngodanghieu.Fragment.FramgentChiTiet;
import com.example.macbookpro.ttcn_ngodanghieu.InterFace.SentDataMonAn;
import com.example.macbookpro.ttcn_ngodanghieu.Model.LinkServer;
import com.example.macbookpro.ttcn_ngodanghieu.Model.OderMonAn;
import com.example.macbookpro.ttcn_ngodanghieu.R;
import com.google.gson.Gson;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QuanLyOrder extends AppCompatActivity implements SentDataMonAn {
    FragmentManager fragmentManager;
    LinkServer linkServer = new LinkServer();
    EditText editText;
    ListView lvoder;
    int level =-1;
    ArrayList<OderMonAn> arrayListoder=new ArrayList<>();;
    HoaDonAdapter hoaDonAdapter;
    OderAdapter arrayAdapteroder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quan_ly_order);
        SharedPreferences ghinho;
        ghinho =getSharedPreferences("datalogin",MODE_PRIVATE);
//        Toast.makeText(this, ""+ghinho.getInt("level",0), Toast.LENGTH_SHORT).show();
//        int level =  intent.getIntExtra("level",0);
        level =  ghinho.getInt("level",0);
        editText = (EditText) findViewById(R.id.edtmamonan);
        lvoder = (ListView) findViewById(R.id.lvoder);

        fragmentManager = getSupportFragmentManager();

        arrayAdapteroder = new OderAdapter(this,R.layout.dong_oder_monan,arrayListoder);
        lvoder.setAdapter(arrayAdapteroder);
        // Start Intent QRCODE
        final IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        findViewById(R.id.qrcode).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentIntegrator.initiateScan();
            }
        });
        // Xử lý hàng động thay đổi giá trị Edittext
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                findViewById(R.id.btntimkiem).performClick();
                String a =editText.getText().toString();
                showMoAn(a);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                editText.setText(result.getContents()+"");

//                try {
//                    JSONObject jsonObject = new JSONObject(result.getContents());
////                    String a =jsonObject.getString("");
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    // Xử lý hiển thị Fragment hiện ẩn Món ăn
    private void showMoAn(String ma) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentMonAn fma= new FragmentMonAn();
        fragmentTransaction.replace(R.id.fragamentmonan,fma,"monan");
        fragmentTransaction.addToBackStack("monan");
        Bundle bundle = new Bundle();
        bundle.putString("mamoanan",ma);
        fma.setArguments(bundle);
        fragmentTransaction.commitAllowingStateLoss();
    }
    // Xử lý hành động xoá món ăn khỏi danh sách Order
    public void remove_monan(int position){
        arrayListoder.remove(position);
        arrayAdapteroder.notifyDataSetChanged();
    }
    // Xử lý thêm món ăn vào danh sách Order
    public void addOder(final String ten,final int id,final int sl,final int gia){
        Toast.makeText(this, "Đã thêm "+ten+"Vào danh sách.", Toast.LENGTH_SHORT).show();
        int size =arrayListoder.size();
//                String tenMonAn, int id, int sl, int gia, int thanhtien
        if (size==0){
            arrayListoder.add(new OderMonAn(ten,id,sl,gia,gia*sl,level));
        }else {
            int dem=0;
            for (int i=0;i<size;i++){
                if (id ==arrayListoder.get(i).getId()){
                    arrayListoder.get(i).setSl(sl+arrayListoder.get(i).getSl());
                    dem=1;
                }
            }
            if (dem ==0){
                arrayListoder.add(new OderMonAn(ten,id,sl,gia,gia*sl,level));
            }

        }


        arrayAdapteroder.notifyDataSetChanged();
    }
    @Override
    public void onBackPressed() {
        this.finish();
    }
    // Xử lý dữ liệu được truyền về từ FRagment thông qua interface SentDataMonAn
    @Override
    public void sendata(String ten, int id, int sl, int gia) {
        addOder(ten,id,sl,gia);
    }

    @Override
    public void delete() {
        arrayListoder.clear();
        arrayAdapteroder.notifyDataSetChanged();
        finish();
    }

    @Override
    public void hienthanhtoan() {
        dialoghienthihoadon();
    }

    public void dialoghienthihoadon(){

//        AlertDialog.Builder adb = new AlertDialog.Builder(this);
//        Dialog d = adb.setView(new View(this)).create();
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_hien_thi_hoa_don);
        dialog.setCanceledOnTouchOutside(false);
        ListView lv = dialog.findViewById(R.id.lvhoadon);
        hoaDonAdapter = new HoaDonAdapter(this,R.layout.dong_hoa_don,arrayListoder);
        lv.setAdapter(hoaDonAdapter);
        TextView tongtien = dialog.findViewById(R.id.tongtienhoadon);
        int tong = 0;
        for (OderMonAn or:arrayListoder){
            tong+=or.getGia()*or.getSl();
        }
        tongtien.setText("Tổng tiền: "+tong);
        dialog.findViewById(R.id.btnhuychapnhanhoadon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.findViewById(R.id.btnchapnhanthanhtoanhoadon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                String datatoJson = gson.toJson(arrayListoder);
                insert_hoadon(linkServer.getLocation()+"TTCN_NGODANGHIEU/Model/inser_hoadon_cthd.php",datatoJson);
                dialog.cancel();
                finish();
            }
        });

        dialog.show();

    }

    private void insert_hoadon(String url,final String data){
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest =new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("success"))
                        {
                            Toast.makeText(QuanLyOrder.this, "Thêm thành công.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(QuanLyOrder.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params =new HashMap<>();
                params.put("MangOrder",data);

                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
}
