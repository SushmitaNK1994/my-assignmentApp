package com.example.assignmentapp.viewmodel;

import androidx.lifecycle.ViewModel;
import com.example.assignmentapp.listerners.CallBackImpl;
import com.example.assignmentapp.model.DataDTO;
import io.reactivex.Observable;

public class FactsViewModel extends ViewModel {
    private CallBackImpl callBack;
    public Observable<DataDTO> dataDTOObservable;

    public FactsViewModel() {
        callBack = new CallBackImpl();
        dataDTOObservable = callBack.callbackresponse();
    }

}
