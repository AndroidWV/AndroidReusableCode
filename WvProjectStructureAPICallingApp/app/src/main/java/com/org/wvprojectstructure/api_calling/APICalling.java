package com.org.wvprojectstructure.api_calling;
import android.app.Activity;
import android.content.Context;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class APICalling  {
    private static final String BASE_URL = "https://developer.webvilleedemo.xyz";
    private static final String IMAGE_URL = "https://developer.webvilleedemo.xyz";
    private String TAG = "APICalling";
    private Activity activity;
    private Gson gson;
    private String result = "";
    private static Retrofit retrofit = null;

    /**
     * Instantiates a new Api calling.
     *
     * @param context the context
     */
    public APICalling(Context context) {
        activity = (Activity) context;
    }

    /**
     * Web service interface rest api.
     *
     * @return the rest api
     */
    public static RestAPI webServiceInterface() {
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();
        return retrofit.create(RestAPI.class);
    }



    /**
     * Gets hash map object.
     *
     * @param nameValuePair the name value pair
     * @return the hash map object
     */
    public HashMap<String, Object> getHashMapObject(Object... nameValuePair) {
        HashMap<String, Object> HashMap;
        if (null != nameValuePair && nameValuePair.length % 2 == 0) {
            HashMap = new HashMap<>();
            int i = 0;
            while (i < nameValuePair.length) {
                HashMap.put(nameValuePair[i].toString(), nameValuePair[i + 1]);
                i += 2;
            }
        } else {
            HashMap = new HashMap<>();
        }
        return HashMap;
    }

    /**
     * Gets hash map object part.
     *
     * @param nameValuePair the name value pair
     * @return the hash map object part
     */
    public HashMap<String, RequestBody> getHashMapObjectPart(Object... nameValuePair) {
        HashMap<String, RequestBody> HashMap;
        if (null != nameValuePair && nameValuePair.length % 2 == 0) {
            HashMap = new HashMap<>();
            int i = 0;
            while (i < nameValuePair.length) {
                HashMap.put(nameValuePair[i].toString(), createPartFromString(nameValuePair[i + 1].toString()));
                i += 2;
            }
        } else {
            HashMap = new HashMap<>();
        }
        return HashMap;
    }

    private RequestBody createPartFromString(String partString) {
        return RequestBody.create(MultipartBody.FORM, partString);
    }
}
