package work.newproject.asus.apurv.lms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import work.newproject.asus.apurv.lms.recever.NewFormActivity;
import work.newproject.asus.apurv.lms.recever.ReceverDashBoardActivity;

import static android.Manifest.permission.CAMERA;

public class QrScanActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private static final int REQUEST_CAMERA = 1;
    private ZXingScannerView scannerView;
    String pageID;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_scan);

        pageID=getIntent().getStringExtra("pageID");

        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
        if (checkPermission()) {
            //Toast.makeText(getApplicationContext(), "Permission already granted!", Toast.LENGTH_LONG).show();
        } else {
            requestPermission();
        }


    }

    private boolean checkPermission() {
        return (ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA) == PackageManager.PERMISSION_GRANTED);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{CAMERA}, REQUEST_CAMERA);
    }

    @Override
    public void onResume() {
        super.onResume();
        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            if (checkPermission()) {
                if (scannerView == null) {
                    scannerView = new ZXingScannerView(this);
                    setContentView(scannerView);
                }
                scannerView.setResultHandler(this);
                scannerView.startCamera();
            } else {
                requestPermission();
            }
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        scannerView.stopCamera();
    }


    @Override
    public void handleResult(Result rawResult) {
        scannerView.removeAllViews();
        scannerView.stopCamera();


        Intent intent=new Intent(QrScanActivity.this, NewFormActivity.class);
        intent.putExtra("barcodeID",rawResult.toString());
        startActivity(intent);
        finish();
    }


    @Override
    public void onBackPressed() {
        if (pageID.equalsIgnoreCase("1")){
            Intent intent=new Intent(this, ReceverDashBoardActivity.class);
            startActivity(intent);
            finish();
        }else {
            super.onBackPressed();
        }

    }
}