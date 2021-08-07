package com.org.wvprojectstructure.activity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.gson.Gson;
import com.org.wvprojectstructure.R;
import com.org.wvprojectstructure.api_calling.APICalling;
import com.org.wvprojectstructure.api_calling.RestAPI;
import com.org.wvprojectstructure.databinding.ActivityBaseBinding;
import com.org.wvprojectstructure.utils.CommonMethods;
import com.org.wvprojectstructure.utils.MyApplication;
import com.org.wvprojectstructure.viewModel.ProjectViewModel;

public class BaseActivity<MyChildBinding extends ViewDataBinding> extends AppCompatActivity {
    private ActivityBaseBinding parentBinding;
    protected MyChildBinding childBinding;
    private Toolbar toolbar;
    private TextView toolbarTitle;
    private View footerView;

    /*Code for API calling*/
    protected MyApplication myApplication;
    protected Gson gson;
    protected APICalling apiCalling;
    protected RestAPI restAPI;
    protected ProjectViewModel projectViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parentBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.activity_base, null, false);
        baseActivityInit();
    }

    @Override
    public void setContentView(int layoutResID) {
        childBinding = DataBindingUtil.inflate(getLayoutInflater(), layoutResID, parentBinding.childFL, true);
        super.setContentView(parentBinding.activityContent);
        initialHeaderFooter();
    }

    private void baseActivityInit() {
        myApplication = (MyApplication) getApplicationContext();
        restAPI = APICalling.webServiceInterface();
        gson = new Gson();
        apiCalling = new APICalling(this);
        projectViewModel = new ViewModelProvider(this).get(ProjectViewModel.class);
    }

    private void initialHeaderFooter() {
        toolbar = findViewById(R.id.toolbar);
        toolbarTitle = findViewById(R.id.toolbarTitle);
        footerView = findViewById(R.id.footerView);
        if (useToolbar()) {
            try {
                setSupportActionBar(toolbar);
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setDisplayShowTitleEnabled(false);
                }
                toolbar.setTitle("");
                getSupportActionBar().setTitle("");
                if (useToolCancel()) {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    getSupportActionBar().setHomeAsUpIndicator(CommonMethods.getDrawableWrapper(this, R.drawable.ic_cross));
                } else if (useBackPress()) {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    getSupportActionBar().setHomeAsUpIndicator(CommonMethods.getDrawableWrapper(this, R.mipmap.back_arrow));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            toolbar.setVisibility(View.VISIBLE);
        } else {
            toolbar.setVisibility(View.GONE);
        }

        //setFooterView();
        showFooter(false);
    }

    /**
     * Use toolbar boolean.
     *
     * @return the boolean
     */
    protected boolean useToolbar() {
        return false;
    }

    protected boolean useToolbarMenu() {
        return true;
    }


    public boolean useToolCancel() {
        return false;
    }

    public boolean useBackPress() {
        return false;
    }

    public boolean hideToolbarTitle() {
        return false;
    }

    /**
     * Show footer.
     *
     * @param isVisible the is visible
     */
    protected void showFooter(boolean isVisible) {
        if (isVisible) {
            footerView.setVisibility(View.VISIBLE);
        } else {
            footerView.setVisibility(View.GONE);
        }
    }
}