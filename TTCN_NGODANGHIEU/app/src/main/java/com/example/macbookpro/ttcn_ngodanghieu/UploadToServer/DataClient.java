package com.example.macbookpro.ttcn_ngodanghieu.UploadToServer;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface DataClient {
    @Multipart
    @POST("uploat.php")
    Call<String> uploadphoto(@Part MultipartBody.Part photo);
}
