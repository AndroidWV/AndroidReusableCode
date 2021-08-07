package com.org.wvprojectstructure.login;
import android.os.Bundle;
import android.view.View;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.org.wvprojectstructure.R;
import com.org.wvprojectstructure.activity.BaseActivity;
import com.org.wvprojectstructure.databinding.ActivityLoginBinding;
import com.org.wvprojectstructure.utils.Const;
import java.util.HashMap;
import retrofit2.Call;

public class LoginActivity extends BaseActivity<ActivityLoginBinding> implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        childBinding.setClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginTV:
                LoginFunc();
                break;
        }
    }

    private void LoginFunc(){
        HashMap<String, Object> values = apiCalling.getHashMapObject(
                "mobile", childBinding.mobileEditText.getText().toString().trim(),
                "password", childBinding.passwordEditText.getText().toString().trim());

        Call<JsonElement> call = restAPI.postWithTokenApi(Const.getToken(),getString(R.string.api_customer_signup), values);
        projectViewModel.callAPI(LoginActivity.this,call,getString(R.string.api_customer_signup),true,childBinding.emailInputLayout).observe(this, responseModel -> apiResponse(responseModel.getJson(), responseModel.getFrom()));
    }


    private void apiResponse(JsonObject json, String from) {
        Const.myLog(from, json.toString());
        if (from != null) {
            int status = 0;
            String msg = "";
            try {
                status = json.get("status").getAsInt();
                msg = json.get("message").getAsString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (from.equalsIgnoreCase(getString(R.string.api_customer_signup))) {
                if (status == 200) {

                } else if (status == 401) {

                }
            }
        }
    }
}