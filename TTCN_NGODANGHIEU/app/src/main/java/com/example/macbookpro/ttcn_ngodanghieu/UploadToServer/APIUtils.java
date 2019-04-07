package com.example.macbookpro.ttcn_ngodanghieu.UploadToServer;

public class APIUtils {
    public static final String base_url="http://192.168.1.71/TTCN_NGODANGHIEU/Model/";

    public static DataClient getData(){

        return RetrofitClinet.getClient(base_url).create(DataClient.class);
    }
}
