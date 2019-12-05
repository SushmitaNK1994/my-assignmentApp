package com.example.assignmentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toolbar;
import com.example.assignmentapp.model.DataDTO;
import com.example.assignmentapp.viewmodel.FactsViewModel;
import com.example.assignmentapp.views.FactsAdapter;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    private Context mContext;
    private FactsViewModel factsViewModel;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        toolbar=findViewById(R.id.toolBar);
        recyclerView=findViewById(R.id.recyclerView_facts);
        factsViewModel = ViewModelProviders.of(this).get(FactsViewModel.class);
        factsViewModel.dataDTOObservable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).
                subscribeWith(new Observer<DataDTO>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(DataDTO dataDTO) {
                        toolbar.setTitle(dataDTO.getTitle());
                        toolbar.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
                        recyclerView.setLayoutManager(layoutManager);
                        FactsAdapter factsAdapter = new FactsAdapter(mContext, dataDTO);
                        recyclerView.setAdapter(factsAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    }



