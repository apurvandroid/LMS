package work.newproject.asus.apurv.lms.Grf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ProgressBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import work.newproject.asus.apurv.lms.R;
import work.newproject.asus.apurv.lms.network.Api;
import work.newproject.asus.apurv.lms.network.ApiClints;

public class TestREportActivity extends AppCompatActivity {



    @BindView(R.id.rvEntryList)
    RecyclerView rvEntryList;

    @BindView(R.id.progress_circular)
    ProgressBar progress_circular;


    CompositeDisposable disposable = new CompositeDisposable();
    Api api = ApiClints.getClient().create(Api.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_r_eport);
        ButterKnife.bind(this);

    }
}