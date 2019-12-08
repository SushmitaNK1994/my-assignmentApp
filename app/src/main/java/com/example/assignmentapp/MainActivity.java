package com.example.assignmentapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.assignmentapp.IOHelper.ConnectivityReceiver;
import com.example.assignmentapp.model.DataDTO;
import com.example.assignmentapp.viewmodel.FactsViewModel;
import com.example.assignmentapp.views.FactsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener,SwipeRefreshLayout.OnRefreshListener{
    @BindView(R.id.recyclerView_facts)
    RecyclerView recyclerView;
    @BindView(R.id.toolBar_view)
    Toolbar toolbar;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.network_textView)
    TextView network_textView;
    private Context mContext;
    private FactsViewModel factsViewModel;
    FactsAdapter factsAdapter;
    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        checkInternetConnection();
        swipeRefreshLayout.setOnRefreshListener(this);
        mContext = this;
        factsViewModel = ViewModelProviders.of(this).get(FactsViewModel.class);
        factsViewModel.dataDTOObservable.observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).
                subscribeWith(new Observer<DataDTO>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        d.dispose();
                    }

                    @Override
                    public void onNext(DataDTO dataDTO) {
                        toolbar.setTitle(dataDTO.getTitle());
                        toolbar.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(mContext);
                        recyclerView.setLayoutManager(layoutManager);
                        factsAdapter = new FactsAdapter(mContext, dataDTO);
                        recyclerView.setAdapter(factsAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MyApplication.getInstance(),R.string.error_check,Toast.LENGTH_SHORT ).show();

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void checkInternetConnection(){
        boolean isConnected=ConnectivityReceiver.isConnected();
        showToast(isConnected);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MyApplication.getInstance().setConnectivityListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        MyApplication.getInstance().setConnectivityListener(this);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showToast(isConnected);
    }

    public void showToast(boolean isConnected){
        if(isConnected) {
            Toast.makeText(MyApplication.getInstance(),R.string.network_connected,Toast.LENGTH_SHORT ).show();
        }else {
            network_textView.setVisibility(View.VISIBLE);
            Toast.makeText(MyApplication.getInstance(),R.string.network_error,Toast.LENGTH_SHORT ).show();
        }
    }

    @Override
    public void onRefresh() {
        if(factsAdapter!=null){
          factsAdapter.notifyDataSetChanged();
        }
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

}



