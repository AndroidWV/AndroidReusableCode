package com.org.wvprojectstructure.activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.org.wvprojectstructure.R;
import com.org.wvprojectstructure.databinding.ActivityMainBinding;
import com.org.wvprojectstructure.login.LoginActivity;
import com.org.wvprojectstructure.utils.Const;

public class SplashActivity extends BaseActivity<ActivityMainBinding> {
    private final int SPLASH_DISPLAY_LENGTH = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                if (Const.isUserLogin()){
                    Intent mainIntent = new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(mainIntent);
                }else {
                    Intent mainIntent = new Intent(SplashActivity.this,LoginActivity.class);
                    startActivity(mainIntent);
                }

            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}