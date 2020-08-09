package work.newproject.asus.apurv.lms.recever;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import work.newproject.asus.apurv.lms.R;

public class ReceverDashBoardActivity extends AppCompatActivity {


    @BindView(R.id.rvEntryList)
    RecyclerView rvEntryList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recever_dash_board);
        ButterKnife.bind(this);
    }
}