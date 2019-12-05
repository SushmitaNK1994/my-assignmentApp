package com.example.assignmentapp.repository;

import android.annotation.SuppressLint;

import com.example.assignmentapp.IOHelper.FactsApi;
import com.example.assignmentapp.IOHelper.ServiceGenarator;
import com.example.assignmentapp.model.DataDTO;

import io.reactivex.Observable;


public class FactsRepo {
    private FactsApi apiService;

    @SuppressLint("CheckResult")
    public FactsRepo() {
    }

    public Observable<DataDTO> getFactsrepoobservable() {
        apiService = ServiceGenarator.getRetrofitInstance().create(FactsApi.class);
        Observable<DataDTO> dataDTOObservable = apiService.getFactsDTO();
        return dataDTOObservable;
    }
}
