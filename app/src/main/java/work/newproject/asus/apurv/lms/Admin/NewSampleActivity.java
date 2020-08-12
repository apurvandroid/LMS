package work.newproject.asus.apurv.lms.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import work.newproject.asus.apurv.lms.R;
import work.newproject.asus.apurv.lms.adapter.SubEntyListAdapter;
import work.newproject.asus.apurv.lms.network.Api;
import work.newproject.asus.apurv.lms.network.ApiClints;
import work.newproject.asus.apurv.lms.network.model.GetList;
import work.newproject.asus.apurv.lms.recever.ReceverDashBoardActivity;

public class NewSampleActivity extends AppCompatActivity {



    @BindView(R.id.rvEntryList)
    RecyclerView rvEntryList;

    @BindView(R.id.progress_circular)
    ProgressBar progress_circular;

    CompositeDisposable disposable = new CompositeDisposable();
    Api api = ApiClints.getClient().create(Api.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_sample);
        ButterKnife.bind(this);

        getList();
    }


    private void getList() {

        showProgress();
        api.getList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<GetList>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull GetList getList) {

                        if (getList.getStatus().equalsIgnoreCase("success")) {
                            SubEntyListAdapter mainCatAdapter = new SubEntyListAdapter(NewSampleActivity.this, getList.getData(),2);
                            rvEntryList.setLayoutManager(new LinearLayoutManager(NewSampleActivity.this, LinearLayoutManager.VERTICAL, false));
                            rvEntryList.setAdapter(mainCatAdapter);

                        } else {

                        }

                        hideProgress();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        hideProgress();
                    }
                });
    }
    private void showProgress() {
        progress_circular.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        progress_circular.setVisibility(View.GONE);
    }

}