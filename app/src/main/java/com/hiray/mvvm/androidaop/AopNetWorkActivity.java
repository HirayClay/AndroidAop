package com.hiray.mvvm.androidaop;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hiray.mvvm.androidaop.model.AndroidItemModel;
import com.hiray.mvvm.androidaop.model.ResponseWrapper;
import com.hiray.mvvm.androidaop.network.RestApiHelper;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AopNetWorkActivity extends AppCompatActivity {

    List<AndroidItemModel> models;
    private QuickAdapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aop_net_work);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }


    @AspectNetWork
    public void getUpdatedAndroidNews(View view) {

        RestApiHelper.restApi().getAndroidProjects("http://gank.io/api/data/Android/10/1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseWrapper<List<AndroidItemModel>>>() {
                    Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                    }

                    @Override
                    public void onNext(ResponseWrapper<List<AndroidItemModel>> listResponseWrapper) {
                        if (listResponseWrapper.isOk()) {
                            recyclerView.setAdapter(adapter = new QuickAdapter(R.layout.android_item, listResponseWrapper.data));
                        }
                        disposable.dispose();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }


    class QuickAdapter extends BaseQuickAdapter<AndroidItemModel, BaseViewHolder> {


        public QuickAdapter(int layoutResId, @Nullable List<AndroidItemModel> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, AndroidItemModel item) {
            helper.setText(R.id.author, item.author)
                    .setText(R.id.repo, item.repo)
                    .setText(R.id.project_desc, item.desc);
        }
    }
}
