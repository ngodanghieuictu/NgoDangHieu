package com.example.macbookpro.ttcn_ngodanghieu.Fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
import com.example.macbookpro.ttcn_ngodanghieu.Activity.QuanLyOrder;
import com.example.macbookpro.ttcn_ngodanghieu.Adapter.OderAdapter;
import com.example.macbookpro.ttcn_ngodanghieu.InterFace.SentDataMonAn;
import com.example.macbookpro.ttcn_ngodanghieu.Model.LinkServer;
import com.example.macbookpro.ttcn_ngodanghieu.Model.OderMonAn;
import com.example.macbookpro.ttcn_ngodanghieu.Model.User_Login;
import com.example.macbookpro.ttcn_ngodanghieu.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;

public class FragmentMonAn extends Fragment {
    LinkServer linkServer =new LinkServer();
    TextView txtvtenmonan,txtvgia,txtvtong;
    EditText edtsl;
    ImageView img;
    SentDataMonAn dataMonAn;

    int gia=0,id=-1;
    String ten="";
    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_monan,container,false);
        AnhXa();

        String mamonan = getArguments().getString("mamoanan");
        getData(linkServer.getLocation()+"TTCN_NGODANGHIEU/Model/get_monan.php?mamonan="+mamonan);
//        edtsl.setOnEditorActionListener(new EditText.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_SEARCH ||
//                        actionId == EditorInfo.IME_ACTION_DONE ||
//                        event != null &&
//                                event.getAction() == KeyEvent.ACTION_DOWN &&
//                                event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
//                    if (event == null || !event.isShiftPressed()) {
//                        // the user is done typing.
//                        int sl =Integer.parseInt(edtsl.getText().toString());
//                        int tong = gia*sl;
//                        txtvtong.setText(tong+"");
//                        return true; // consume.
//                    }
//                }
//
//                return false;
//            }
//        });
        edtsl.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (edtsl.getText().toString().equals("")==false){
                    int sl =Integer.parseInt(edtsl.getText().toString());
                    int tong = gia*sl;
                    txtvtong.setText(tong+"");
                }
                if (id == -1) {
                    Animation a = AnimationUtils.loadAnimation(getActivity(), R.anim.animition_loading);
                    img.startAnimation(a);
                }
            }
        });
        view.findViewById(R.id.btngoithem).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edtsl.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "So luong khong hop le!", Toast.LENGTH_SHORT).show();
                }else {
                    int sl =Integer.parseInt(edtsl.getText().toString());
                    dataMonAn.sendata(ten,id,sl,gia);
                }

            }
        });
        view.findViewById(R.id.btnhuythanhtoanhoadon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataMonAn.delete();
            }
        });
        view.findViewById(R.id.btnthanhtoanhoadon).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataMonAn.hienthanhtoan();
            }
        });

        return view;
    }


    private void AnhXa() {
        txtvtenmonan = (TextView) view.findViewById(R.id.tenmonan);
        edtsl = (EditText) view.findViewById(R.id.edtslmonan);
        txtvgia = (TextView) view.findViewById(R.id.giamonan);
        txtvtong = (TextView) view.findViewById(R.id.tongtienmonan);
        img = (ImageView) view.findViewById(R.id.imgmonan);
//        lvoder = (ListView) view.findViewById(R.id.lvoder);
    }
    private void getData(String url){
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONObject object = response.getJSONObject(0);
                                Picasso.with(getContext()).load(linkServer.getUrlimg()+object.getString("IMG")).into(img);
                                id=object.getInt("ID");
                                ten =object.getString("TSP");
                                txtvtenmonan.setText(ten);
                                gia=object.getInt("GIA");
                                txtvgia.setText(gia+"");
                                edtsl.setText(1+"");
                                int tong = Integer.parseInt(edtsl.getText().toString())*gia;
                                txtvtong.setText(tong+"");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        requestQueue.add(jsonArrayRequest);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataMonAn = (SentDataMonAn) getActivity();
    }
}
