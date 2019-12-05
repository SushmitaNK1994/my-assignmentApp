package com.example.assignmentapp.IOHelper;

import com.example.assignmentapp.model.DataDTO;
import com.example.assignmentapp.model.FactsDTO;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface FactsApi {
    @GET("s/2iodh4vg0eortkl/facts.json")
    Observable<DataDTO>getFactsDTO();
}
