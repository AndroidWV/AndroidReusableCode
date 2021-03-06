package com.org.wvprojectstructure.api_calling;
import com.google.gson.JsonElement;
import java.util.Map;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Url;

public interface RestAPI {
    @GET
    Call<JsonElement> getApi(@Url String remainingURL);

    @FormUrlEncoded
    @POST
    Call<JsonElement> postApi(@Url String remainingURL, @FieldMap Map<String, Object> fields);

    @POST
    Call<JsonElement> postApiToken(@Header("authorization") String authToken, @Url String remainingURL);

    @POST
    Call<JsonElement> postApiToken(@Url String remainingURL);

    @GET
    Call<JsonElement> getApiToken(@Header("authorization") String authToken, @Url String remainingURL);

    @FormUrlEncoded
    @POST
    Call<JsonElement> postWithTokenApi(@Header("authorization") String authToken, @Url String remainingURL, @FieldMap Map<String, Object> fields);

    @Multipart
    @POST
    Call<JsonElement> postWithTokenMultiPartApi(@Header("authorization") String authToken, @Url String remainingURL, @Part MultipartBody.Part image);

    @Multipart
    @POST
    Call<JsonElement> postWithTokenMultiPartWithDataApi(@Header("authorization") String authToken, @Url String remainingURL, @PartMap Map<String, RequestBody> fields, @Part MultipartBody.Part image);

}
