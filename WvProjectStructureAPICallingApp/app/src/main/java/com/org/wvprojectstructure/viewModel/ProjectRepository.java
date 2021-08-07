package com.org.wvprojectstructure.viewModel;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.org.wvprojectstructure.R;
import com.org.wvprojectstructure.api_calling.MyProgressDialog;
import com.org.wvprojectstructure.api_calling.ServiceCallback;
import com.org.wvprojectstructure.utils.CommonMethods;
import java.io.IOException;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectRepository implements ServiceCallback<JsonElement> {
    private String TAG = "ProjectRepository";

    private Context mContext;
    private Activity activity;
    private boolean isShowLoader;
    private Gson gson;

    public ProjectRepository() {
        gson = new Gson();
    }

    public void showLoader(Activity activity,boolean isLoader) {
        if (activity != null && !(activity).isDestroyed()) {
            if (isLoader) {
                MyProgressDialog.getInstance().show(activity);
            }else{
                MyProgressDialog.getInstance().dismiss();
            }
        }
    }

    public void hideLoader(Activity activity,boolean isLoader) {
        if (activity != null && !(activity).isDestroyed()) {
            if (!isLoader) {
                MyProgressDialog.getInstance().dismiss();
            }
        }
    }

    public MutableLiveData<ResponseModel> callAPI(Context context, Call<JsonElement> call, String from, Boolean isShowLoader, View view) {
        activity = (Activity) context;
        this.mContext = context;
        showLoader(activity, isShowLoader);
        MutableLiveData<ResponseModel> liveDataObj = new MutableLiveData<>();
        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(@NonNull Call<JsonElement> call, @NonNull Response<JsonElement> response) {
                int statusCode = response.code();
                hideLoader(activity,false);
                try {
                    if (statusCode >= 200 && statusCode < 300 && response.isSuccessful()) {
                        JsonElement user1 = response.body();
                        String result = gson.toJson(user1);
                        JsonElement element = gson.fromJson(result, JsonElement.class);
                        JsonObject jsonObj = element.getAsJsonObject();
                        liveDataObj.setValue(new ResponseModel(jsonObj, from));

                    } else if (statusCode == 401) {
                        ResponseBody user1 = response.errorBody();
                        if (user1 != null) {
                            JsonElement element = gson.fromJson(user1.string(), JsonElement.class);
                            JsonObject jsonObj = element.getAsJsonObject();
                            liveDataObj.setValue(new ResponseModel(jsonObj, from));
                        } else {
                            unauthenticated(response, call, this, view);
                        }

                    } else if (statusCode >= 400 && statusCode < 500) {
                        clientError(response, call, this, view);
                    }  else if (statusCode == 500) {
                        serverError(response, call, this, view);
                    } else if (statusCode > 500 && statusCode < 600) {
                        serverError(response, call, this, view);
                    } else {
                        unexpectedError(new RuntimeException("Unexpected response " + response), call, this, view);
                    }
                } catch (Exception e) {
                    CommonMethods.showLogs(TAG, "api calling  " + e.toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<JsonElement> call, @NonNull Throwable t) {
                hideLoader(activity,false);
                if (t instanceof IOException) {
                    networkError((IOException) t, call, this, view);
                } else {
                    unexpectedError(t, call, this, view);
                }
            }
        });
        return liveDataObj;
    }

    @Override
    public void unauthenticated(Response<?> response, Call<JsonElement> call, Callback<JsonElement> callback, View view) {
        if (call == null) {
            if (mContext != null)
                CommonMethods.showToast(mContext, mContext.getResources().getString(R.string.unable_auth));
        } else {
            retry(call, callback, view);
        }
    }

    @Override
    public void clientError(Response<?> response, Call<JsonElement> call, Callback<JsonElement> callback, View view) {
        if (call == null) {
            if (mContext != null)
            CommonMethods.showSnackBar(mContext, view ,mContext.getResources().getString(R.string.client_not_response));
        } else {
            CommonMethods.showSnackBar(mContext, view ,mContext.getResources().getString(R.string.client_not_response));
//            retry(call, callback, view);
        }
    }

    @Override
    public void serverError(Response<?> response, Call<JsonElement> call, Callback<JsonElement> callback, View view) {
        if (call == null) {
            if (mContext != null)
                CommonMethods.showSnackBar(mContext, view ,mContext.getResources().getString(R.string.server_not_response));
        } else {
            CommonMethods.showSnackBar(mContext, view ,mContext.getResources().getString(R.string.server_not_response));
//            retry(call, callback, view);
        }
    }

    @Override
    public void networkError(IOException e, Call<JsonElement> call, Callback<JsonElement> callback, View view) {
        if (call == null) {
            if (mContext != null)
                CommonMethods.showToast(mContext, mContext.getResources().getString(R.string.network_conn));
        } else {
            CommonMethods.showSnackBar(mContext, view ,mContext.getResources().getString(R.string.network_conn));
        }
    }

    @Override
    public void unexpectedError(Throwable t, Call<JsonElement> call, Callback<JsonElement> callback, View view) {
        if (call == null) {
            if (mContext != null)
                CommonMethods.showToast(mContext, mContext.getResources().getString(R.string.something_wrong));
        } else {
            retry(call, callback, view);
        }
    }

    private void retry(final Call<JsonElement> call, final Callback<JsonElement> callback, View view) {
        try {
            Snackbar.make(view, mContext.getResources().getString(R.string.something_wrong_check_network), Snackbar.LENGTH_INDEFINITE).
                    setAction(mContext.getResources().getString(R.string.retry), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            CommonMethods.preventTwoClick(v);
                            call.clone().enqueue(callback);
                        }
                    }).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
