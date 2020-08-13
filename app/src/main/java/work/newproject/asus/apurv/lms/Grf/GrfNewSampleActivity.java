package work.newproject.asus.apurv.lms.Grf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import work.newproject.asus.apurv.lms.Admin.NewSampleActivity;
import work.newproject.asus.apurv.lms.R;
import work.newproject.asus.apurv.lms.adapter.SubEntyListAdapter;
import work.newproject.asus.apurv.lms.network.Api;
import work.newproject.asus.apurv.lms.network.ApiClints;
import work.newproject.asus.apurv.lms.network.model.GetList;
import work.newproject.asus.apurv.lms.utils.AppStrings;
import work.newproject.asus.apurv.lms.utils.MySharedpreferences;

public class GrfNewSampleActivity extends AppCompatActivity {

    @BindView(R.id.rvEntryList)
    RecyclerView rvEntryList;

    @BindView(R.id.progress_circular)
    ProgressBar progress_circular;


    @BindView(R.id.txtDash)
    TextView Analyst;
    CompositeDisposable disposable = new CompositeDisposable();
    Api api = ApiClints.getClient().create(Api.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grf_new_sample);
        ButterKnife.bind(this);

        Analyst.setText("Analyst");

//data
        getList();
    }

    private void getList() {
        showProgress();
        api.getGrf(MySharedpreferences.getInstance().get(this, AppStrings.userID)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<GetList>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull GetList getList) {

                        if (getList.getStatus().equalsIgnoreCase("success")) {
                            SubEntyListAdapter mainCatAdapter = new SubEntyListAdapter(GrfNewSampleActivity.this, getList.getData(), 3);
                            rvEntryList.setLayoutManager(new LinearLayoutManager(GrfNewSampleActivity.this, LinearLayoutManager.VERTICAL, false));
                            rvEntryList.setAdapter(mainCatAdapter);
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