package work.newproject.asus.apurv.lms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import work.newproject.asus.apurv.lms.recever.ReceverDashBoardActivity;
import work.newproject.asus.apurv.lms.utils.AppStrings;
import work.newproject.asus.apurv.lms.utils.MySharedpreferences;

public class SplashScreen extends AppCompatActivity {

    int time = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        Completable.timer(time, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe(SplashScreen.this::intentServiceFire);

    }

    public void intentServiceFire() {
        if (MySharedpreferences.getInstance().get(this, AppStrings.userID) != null) {
            Intent intent = new Intent(SplashScreen.this, ReceverDashBoardActivity.class);
            startActivity(intent);
            finish();

        } else {
            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

    }

}