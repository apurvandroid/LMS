package work.newproject.asus.apurv.lms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import work.newproject.asus.apurv.lms.auth.LoginFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openDashBoard();
    }

    private void openDashBoard() {
        Bundle args = new Bundle();
        Fragment fragmentt = new LoginFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        fragmentt.setArguments(args);
        transaction.replace(R.id.fragment, fragmentt);
        transaction.commit();
    }
}