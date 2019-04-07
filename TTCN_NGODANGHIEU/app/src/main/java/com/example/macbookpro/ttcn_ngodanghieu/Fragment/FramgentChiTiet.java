package com.example.macbookpro.ttcn_ngodanghieu.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.macbookpro.ttcn_ngodanghieu.Activity.AdminHome;
import com.example.macbookpro.ttcn_ngodanghieu.Activity.QuanLyTaiChinh;
import com.example.macbookpro.ttcn_ngodanghieu.Adapter.BaoCaoThongKeAdapter;
import com.example.macbookpro.ttcn_ngodanghieu.InterFace.GoToScream;
import com.example.macbookpro.ttcn_ngodanghieu.InterFace.SentdataNote;
import com.example.macbookpro.ttcn_ngodanghieu.Login;
import com.example.macbookpro.ttcn_ngodanghieu.Model.BaoCaoThongKe;
import com.example.macbookpro.ttcn_ngodanghieu.Model.LinkServer;
import com.example.macbookpro.ttcn_ngodanghieu.Model.User_Login;
import com.example.macbookpro.ttcn_ngodanghieu.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;

public class FramgentChiTiet extends android.support.v4.app.Fragment {
    ListView lv1,lv2;
    LinkServer linkServer = new LinkServer();
    View view;

    GoToScream goToScream;
    TextView txtvtongtienxuatkho,txtvtongtienlai,txtvtongtiendoanhthu;
    BaoCaoThongKeAdapter baoCaoThongKeAdapter1;
    BaoCaoThongKeAdapter baoCaoThongKeAdapter2;
    ArrayList<BaoCaoThongKe> baoCaoThongKeArrayList1;
    ArrayList<BaoCaoThongKe> baoCaoThongKeArrayList2;
    int stt;
    int phanloai=-1;
    int thu =0;
    int thu2=0;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frargment_chitiet,container,false);

        AnhXa();
        baoCaoThongKeArrayList1 = new ArrayList<>();
        baoCaoThongKeArrayList2 = new ArrayList<>();
        stt =getArguments().getInt("phanloai");
//        Toast.makeText(getActivity(),""+stt,Toast.LENGTH_LONG).show();
        if(stt ==0){
            getxuathang(linkServer.getLocation()+"TTCN_NGODANGHIEU/Model/get_hangxuat.php?phanloai=1");
            phanloai=1;

        }else if(stt ==1){
            getxuathang(linkServer.getLocation()+"TTCN_NGODANGHIEU/Model/get_hangxuat.php?phanloai=2");
            phanloai=2;
//            getdoanhthu(linkServer.getLocation()+"TTCN_NGODANGHIEU/Model/get_doanhthu.php?phanloai=2");
        }


        addArray();
        baoCaoThongKeAdapter1 = new BaoCaoThongKeAdapter(getActivity(),R.layout.dong_bao_cao_tai_chinh,baoCaoThongKeArrayList1);
        baoCaoThongKeAdapter2 = new BaoCaoThongKeAdapter(getActivity(),R.layout.dong_bao_cao_tai_chinh,baoCaoThongKeArrayList2);
        lv1.setAdapter(baoCaoThongKeAdapter1);
        lv2.setAdapter(baoCaoThongKeAdapter2);
//        tong();
        Log.d("TONG",thu+"|||"+thu2);
//        goToScream.sent(thu,thu2);
        return view;
    }



    private void addArray() {

//        baoCaoThongKeArrayList1.add(new BaoCaoThongKe("NGo dang hiuw","hihi",1,100,1));
//        baoCaoThongKeArrayList2.add(new BaoCaoThongKe("NGo dang hiuw","hihi",1,100,1));
//        baoCaoThongKeArrayList1.add(new BaoCaoThongKe("NGo dang hiuw","hihi",1,10000,1));
//        baoCaoThongKeArrayList2.add(new BaoCaoThongKe("NGo dang hiuw","hihi",1,100000,1));
//        baoCaoThongKeArrayList1.add(new BaoCaoThongKe("NGo dang hiuw","hihi",1,1000,1));


    }

    private void AnhXa() {
        lv1 = (ListView) view.findViewById(R.id.lvdsct);
        lv2 = (ListView) view.findViewById(R.id.lvdsdt);
        txtvtongtiendoanhthu = (TextView) view.findViewById(R.id.tongtiendoanhthu);
        txtvtongtienxuatkho = (TextView) view.findViewById(R.id.tongtienxuatkho);
        txtvtongtienlai = (TextView) view.findViewById(R.id.tongtienlai);

    }
    private void getxuathang(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        thu=0;
                        baoCaoThongKeArrayList1.clear();
                        for(int i=0;i<response.length();i++)
                        {

                            try {
                                JSONObject object = response.getJSONObject(i);
                                baoCaoThongKeArrayList1.add(new BaoCaoThongKe(
                                                object.getString("TSP"),
                                                object.getString("DATE"),
                                                object.getInt("SL"),
                                                object.getInt("GIA"),
                                                1
                                        ));
                                thu+=object.getInt("SL")* object.getInt("GIA");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        txtvtongtienxuatkho.setText("Tổng tiền xuất kho: "+thu);

                        baoCaoThongKeAdapter1.notifyDataSetChanged();
                        getdoanhthu(linkServer.getLocation()+"TTCN_NGODANGHIEU/Model/get_doanhthu.php?phanloai="+phanloai);
                        Log.d("PHANLOAI",phanloai+"");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),"Lỗi đường truyền,  vui lòng thử lại!", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
    private void getdoanhthu(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        thu2=0;
                        baoCaoThongKeArrayList2.clear();
                        for(int i=0;i<response.length();i++)
                        {

                            try {
                                JSONObject object = response.getJSONObject(i);
                                baoCaoThongKeArrayList2.add(new BaoCaoThongKe(
                                        object.getString("TSP"),
                                        object.getString("DATE"),
                                        object.getInt("SL"),
                                        object.getInt("GIA"),
                                        1
                                ));
                                thu2+=object.getInt("SL")* object.getInt("GIA");

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        Log.d("SSS",thu2+"||"+thu);
                        int tonglai =thu2-thu;
                        txtvtongtiendoanhthu.setText("Tổng tiền doanh thu : "+thu2);
                        txtvtongtienlai.setText("Tổng tiền lãi: "+tonglai);
                        baoCaoThongKeAdapter2.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(),"Lỗi đường truyền,  vui lòng thử lại!", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

}
