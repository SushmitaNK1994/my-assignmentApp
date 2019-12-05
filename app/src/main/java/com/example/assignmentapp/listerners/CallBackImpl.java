package com.example.assignmentapp.listerners;

import com.example.assignmentapp.model.DataDTO;
import com.example.assignmentapp.repository.FactsRepo;

import io.reactivex.Observable;

public class CallBackImpl implements CallBacklistener {
    private FactsRepo factsRepo;
    private Observable<DataDTO>dataDTOObservable;
    public CallBackImpl(){
        factsRepo=new FactsRepo();
    }
    @Override
    public Observable<DataDTO> callbackresponse() {
        dataDTOObservable=factsRepo.getFactsrepoobservable();
        return dataDTOObservable;
    }
}
