package com.example.assignmentapp.listerners;

import com.example.assignmentapp.model.DataDTO;

import io.reactivex.Observable;

public interface CallBacklistener {
    Observable<DataDTO> callbackresponse();
}
