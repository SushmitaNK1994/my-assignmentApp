package com.example.assignmentapp.model;

import androidx.lifecycle.LiveData;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataDTO  {
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("rows")
    @Expose
    public List<FactsDTO> rowsList;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<FactsDTO> getRowsList() {
        return rowsList;
    }

    public void setRowsList(List<FactsDTO> rowsList) {
        this.rowsList = rowsList;
    }
}
