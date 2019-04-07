package com.example.macbookpro.ttcn_ngodanghieu.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.macbookpro.ttcn_ngodanghieu.Activity.ListNhanVien;
import com.example.macbookpro.ttcn_ngodanghieu.Activity.QuanLyKho;
import com.example.macbookpro.ttcn_ngodanghieu.Model.LinkServer;
import com.example.macbookpro.ttcn_ngodanghieu.R;

import java.util.HashMap;
import java.util.Map;

public class FragmentXuatHang  extends Fragment {
    View view;
    TextView txtvtenhang;
    EditText edtsoluonghangxuat;
    LinkServer linkServer =new LinkServer();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_xuat_kho,container,false);
        AnhXa();

        String tensp =getArguments().getString("Tensp");
        txtvtenhang.setText(tensp);
        view.findViewById(R.id.btnhuyxuathang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.fragamentxuathang)).commit();
            }
        });
        view.findViewById(R.id.btnxuathang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id =getArguments().getInt("Idsp");
                int sl =getArguments().getInt("Slsp");
                    int soluong ;
                    try {
                        soluong=Integer.parseInt(edtsoluonghangxuat.getText().toString().trim());
                    }catch (NumberFormatException e){
                        soluong=0;
                    }if(soluong==0){
                        Toast.makeText(getActivity(), "Moi ban nhap lai so luong", Toast.LENGTH_SHORT).show();

                    }else if(soluong>sl){
                        Toast.makeText(getActivity(), "So luong vuot qua trong kho", Toast.LENGTH_SHORT).show();
                    }else {
                        updateSP(linkServer.getUrlupdatexuathang(),id,soluong);
                    }



            }
        });

        return view;


    }

    private void AnhXa() {
        txtvtenhang = (TextView) view.findViewById(R.id.txtvtenhangxuat) ;
        edtsoluonghangxuat = (EditText) view.findViewById(R.id.edtsoluonghangxuat);
    }


    private void updateSP(String urlupdate,final int id, final int sl){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest stringRequest = new StringRequest(Request.Method.POST,urlupdate,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success"))
                        {
                            Toast.makeText(getActivity(), "Xuat thành công.", Toast.LENGTH_SHORT).show();
                            getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.fragamentxuathang)).commit();

                            getActivity().finish();
                            getActivity().startActivity(getActivity().getIntent());
                        }else{

                            Toast.makeText(getActivity(), "Xuat thất bại! Vui lòng thử lại!", Toast.LENGTH_SHORT).show();
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
                params.put("ID",String.valueOf(id));
                params.put("SL",String.valueOf(sl));
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

}
