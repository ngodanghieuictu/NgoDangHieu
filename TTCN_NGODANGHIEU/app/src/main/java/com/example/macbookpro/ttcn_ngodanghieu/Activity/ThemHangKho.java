package com.example.macbookpro.ttcn_ngodanghieu.Activity;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.macbookpro.ttcn_ngodanghieu.Model.LinkServer;
import  com.example.macbookpro.ttcn_ngodanghieu.R;
import com.example.macbookpro.ttcn_ngodanghieu.UploadToServer.APIUtils;
import com.example.macbookpro.ttcn_ngodanghieu.UploadToServer.DataClient;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Callback;


public class ThemHangKho extends AppCompatActivity {
    EditText editTexttenhang,edtnhasanxuat,edtsoluong,edtgiahangnhapvao;
    ImageView imageViewthemhang;
    LinkServer linkServer =new LinkServer();
    RadioGroup rg;
    String realpath ="",tenimg="";
    int level=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_hang_kho);
        AnhXa();
        SharedPreferences ghinho;
        ghinho =getSharedPreferences("datalogin",MODE_PRIVATE);
        level=ghinho.getInt("level",0);
    }
    public void onclikimageViewthemhang(View v){
        Intent intent =new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent,123);
    }
    public void onclickthemhang(View v){
        int b=rg.getCheckedRadioButtonId();
        int a=-1;
        switch (b){
            case R.id.spchebien:{
                a=-1;a=1;
                break;
            }
            case R.id.spanlien:{
                a=-1;a=0;
                break;
            }
        }
        if(ktra(a)){
            uploadimage();
            if (tenimg!=""){
                toast(tenimg);
                insert_hang(linkServer.getUrlinserthang(),a,tenimg,level);
                setResult(RESULT_OK);
                finish();
            }
        }
    }
    private boolean ktra(int a){
        String ten,nhasanxuat;
        String soluong,gia;
        ten = editTexttenhang.getText().toString();
        nhasanxuat = edtnhasanxuat.getText().toString();
        soluong = edtsoluong.getText().toString();
        gia = edtgiahangnhapvao.getText().toString();
        if (ten.isEmpty()){
            toast("Không được bỏ trống trường tên! Mời bạn nhập lại!");
        }else if (nhasanxuat.isEmpty()){
            toast("Không được bỏ trống trường nhà sản xuất! Mời bạn nhập lại!");
        }else if (soluong.isEmpty()){
            toast("Không được bỏ trống trường số lượng! Mời bạn nhập lại!");
        }else if (soluong.isEmpty()){
            toast("Không được bỏ trống trường giá! Mời bạn nhập lại!");
        }else if (realpath.equals("")){
            toast("Bạn chưa chọn ảnh! Mời bạn chọn ảnh!");
        }else if (a==-1){
            toast("Bạn chưa chọn lại sản phẩm! Mời bạn chọn!");
        }else {
            return true;
        }
        return false;
    }
    private void toast(String str){
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
    public void onclickhuythemhang(View v){
        this.finish();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode==123&&resultCode==RESULT_OK &&data!=null){
            Uri uri =data.getData();
            realpath = getRealPathFromURI(uri);
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageViewthemhang.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }


        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    public String getRealPathFromURI (Uri contentUri) {
        String path = null;
        String[] proj = { MediaStore.MediaColumns.DATA };
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        return path;
    }
    private void uploadimage(){
        File file = new File(realpath);
        String file_path =file.getAbsolutePath();
        String[] arrayfile = file_path.split("\\.");
        file_path =arrayfile[0]+"_"+System.currentTimeMillis()+"."+arrayfile[1];
        Log.d("LLLL",arrayfile[0]);
        String[] ten =arrayfile[0].split("/");
        tenimg = ten[5]+"_"+System.currentTimeMillis()+"."+arrayfile[1];
        Log.d("BBBBBBBB",ten[5]);

        final RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"),file);

        MultipartBody.Part body = MultipartBody.Part.createFormData("uploaded_file",file_path,requestBody);

        DataClient dataClient = APIUtils.getData();
        retrofit2.Call<String> callback = dataClient.uploadphoto(body);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(retrofit2.Call<String> call, retrofit2.Response<String> response) {
                if (response !=null){
                    String errr = response.body();
                    Log.d("BBB",errr);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<String> call, Throwable t) {
                Log.d("AAAAAAAAAAAAAAA", t.getMessage());
            }
        });
    }

    private void AnhXa() {
        editTexttenhang = (EditText) findViewById(R.id.edttenhangnhap);
        edtnhasanxuat = (EditText) findViewById(R.id.edtnsxhangnhap);
        edtgiahangnhapvao =(EditText) findViewById(R.id.edtgiahangnhapvao);
        edtsoluong = (EditText) findViewById(R.id.edtslhangnhap);
        rg = (RadioGroup) findViewById(R.id.rgthemhangnhap);
        imageViewthemhang = (ImageView) findViewById(R.id.imageViewthemhang);
    }

    private void chonngay(){
        final Calendar calendar = Calendar.getInstance();
        int ngay =calendar.get(Calendar.DATE);
        int thang = calendar.get(Calendar.MONTH);
        int nam  =calendar.get(Calendar.YEAR);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                // i nam i1 thang i2 nam
                calendar.set(i,i1,i2);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//                edtngaynhap.setText(simpleDateFormat.format(calendar.getTime()));
            }
        },nam,thang,ngay);
        datePickerDialog.show();
    }
    private void insert_hang(String url1, final int loaisp, final String img,final int level) {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest =new StringRequest(Request.Method.POST, url1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("success"))
                        {
                            Toast.makeText(ThemHangKho.this, "Thêm thành công.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ThemHangKho.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params =new HashMap<>();
                params.put("TSP",editTexttenhang.getText().toString().trim());
                params.put("SL",edtsoluong.getText().toString().trim());
//                params.put("NGAYNHAP",nam+"-"+thang+"-"+ngay);
                params.put("IDUSER",""+level);
                params.put("NSX",edtnhasanxuat.getText().toString().trim());
                params.put("LOAISP",loaisp+"");
                params.put("GIA",edtgiahangnhapvao.getText().toString().trim());
                params.put("IMG",img);
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

}
