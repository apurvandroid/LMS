package work.newproject.asus.apurv.lms.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import work.newproject.asus.apurv.lms.Admin.fragment.AdminDashBoardFragment;
import work.newproject.asus.apurv.lms.R;
import work.newproject.asus.apurv.lms.auth.LoginFragment;

public class AdminDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        openDashBoard();
    }


    private void openDashBoard() {
        Bundle args = new Bundle();
        Fragment fragmentt = new AdminDashBoardFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        fragmentt.setArguments(args);
        transaction.replace(R.id.fragment, fragmentt);
        transaction.commit();
    }
}