package work.newproject.asus.apurv.lms.recever;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import work.newproject.asus.apurv.lms.MainActivity;
import work.newproject.asus.apurv.lms.QrScanActivity;
import work.newproject.asus.apurv.lms.R;

public class ReceverDashBoardActivity extends AppCompatActivity {


    @BindView(R.id.rvEntryList)
    RecyclerView rvEntryList;

    
    @BindView(R.id.imgScanCode)
    ImageView imgScanCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recever_dash_board2);
        ButterKnife.bind(this);
        imgScanCode.setOnClickListener(v -> openScanner());
    }

    private void openScanner() {
        Intent intent=new Intent(ReceverDashBoardActivity.this, QrScanActivity.class);
        intent.putExtra("pageID","1");
        startActivity(intent);
        finish();
    }
}