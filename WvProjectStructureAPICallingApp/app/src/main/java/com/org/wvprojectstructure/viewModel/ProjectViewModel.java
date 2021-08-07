package com.org.wvprojectstructure.viewModel;

import android.app.Application;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.JsonElement;

import retrofit2.Call;

public class ProjectViewModel extends AndroidViewModel {
    private ProjectRepository projectRepository;

    public ProjectViewModel(@NonNull Application application) {
        super(application);
        projectRepository = new ProjectRepository();
    }

    public MutableLiveData<ResponseModel> callAPI(Context context, Call<JsonElement> call, String from, Boolean isShowLoader , View view) {
        return projectRepository.callAPI(context,call,from,isShowLoader,view);
    }

}
