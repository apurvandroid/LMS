package work.newproject.asus.apurv.lms.Grf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import work.newproject.asus.apurv.lms.Admin.fragment.AdminDashBoardFragment;
import work.newproject.asus.apurv.lms.R;

public class DrfDashBoardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drf_dash_board);
        openDashBoard();
    }
    private void openDashBoard() {
        Bundle args = new Bundle();
        Fragment fragmentt = new GrfFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        fragmentt.setArguments(args);
        transaction.replace(R.id.fragment, fragmentt);
        transaction.commit();
    }
}