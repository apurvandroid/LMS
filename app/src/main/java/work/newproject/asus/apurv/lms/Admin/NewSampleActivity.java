package work.newproject.asus.apurv.lms.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import work.newproject.asus.apurv.lms.Admin.adapter.SampleAdapter;
import work.newproject.asus.apurv.lms.R;
import work.newproject.asus.apurv.lms.ViewPdfOnlineAcivity;
import work.newproject.asus.apurv.lms.adapter.SubEntyListAdapter;
import work.newproject.asus.apurv.lms.network.Api;
import work.newproject.asus.apurv.lms.network.ApiClints;
import work.newproject.asus.apurv.lms.network.model.GetList;
import work.newproject.asus.apurv.lms.recever.ReceverDashBoardActivity;

public class NewSampleActivity extends AppCompatActivity implements SampleAdapter.DownloadPdf{


    @BindView(R.id.rvEntryList)
    RecyclerView rvEntryList;

    @BindView(R.id.progress_circular)
    ProgressBar progress_circular;


    String value;
    private ProgressDialog progressBar;

    private int STORAGE_PERMISSION_CODE = 1;
    String filePath;

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
                            SampleAdapter mainCatAdapter = new SampleAdapter(NewSampleActivity.this, getList.getData());
                            rvEntryList.setLayoutManager(new LinearLayoutManager(NewSampleActivity.this, LinearLayoutManager.VERTICAL, false));
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

    @Override
    public void pdf(String qrID) {
        value=qrID;

        downloadInocice(qrID);

    }


    public void downloadInocice(final String inID) {
        final ProgressDialog loading = ProgressDialog.show(NewSampleActivity.this, "Authenticating", "Please wait ", false, true);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://jvearth.in/lms/pdf/index.php/?qr_id="+inID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                System.out.println(response);

                Log.d("TAG", "onResponse: "+response);
                if (response.equalsIgnoreCase("error")) {

                } else {
                    if (checkPermission()) {
                            value = response;
                        Intent intent = new Intent(NewSampleActivity.this, ViewPdfOnlineAcivity.class);
                        intent.putExtra("url", response);
                        startActivity(intent);
                       /* new download().execute(response);*/
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
                Toast.makeText(NewSampleActivity.this,
                        "Network Problem", Toast.LENGTH_LONG).show();
                loading.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("order_id", inID);
                return map;
            }
        };
        Volley.newRequestQueue(NewSampleActivity.this).add(stringRequest);
    }



    private class download extends AsyncTask<String, Integer, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressBar = new ProgressDialog(NewSampleActivity.this);
            progressBar.setTitle("Wait Download");
            progressBar.setMessage("Please Wait");
            progressBar.setIndeterminate(false);
            progressBar.setMax(100);
            progressBar.setCancelable(false);
            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressBar.show();
        }


        @Override
        protected String doInBackground(String... strings) {


            try {
                URL url = new URL(strings[0]);
                URLConnection connection = url.openConnection();
                connection.connect();
                int fileLength = connection.getContentLength();
                // String filePath = Environment.getExternalStorageDirectory().getPath();
                filePath = Environment.getExternalStorageDirectory() + "/LMS/";
                InputStream inputStream = new BufferedInputStream(url.openStream());
                //  File dir = new File(filePath,"/Try11/kk");
                File dir = new File(filePath);
                if (!dir.exists())
                    dir.mkdirs();

                File file = new File(dir, value);
                if (!file.exists())
                    file.createNewFile();


                OutputStream stream = new FileOutputStream(file);

                byte data[] = new byte[1024];
                long total = 0;
                int count;
                while ((count = inputStream.read(data)) != -1) {
                    total += count;
                    publishProgress((int) (total * 100 / fileLength));
                    stream.write(data, 0, count);
                }
                stream.flush();
                stream.close();
                inputStream.close();


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            Log.d("TAG", "onProgressUpdate: "+value);
            progressBar.setProgress(values[0]);


        }

        @SuppressLint("WrongConstant")
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.dismiss();

            File directory = Environment.getExternalStoragePublicDirectory("LMS/");
            File file = new File(directory, value);
            Uri uri = FileProvider.getUriForFile(NewSampleActivity.this, getPackageName() + ".provider", file);
            Intent pdfOpenintent = new Intent(Intent.ACTION_VIEW);
            pdfOpenintent.setDataAndType(uri, "application/pdf");
            pdfOpenintent.setFlags(Intent.FILL_IN_ACTION);
            try {
                startActivity(pdfOpenintent);
            } catch (ActivityNotFoundException e) {
                Log.d("TAG", "onPostExecute: "+e.getLocalizedMessage());
            }
        }
    }

    private boolean checkPermission() {
        boolean read = ActivityCompat.checkSelfPermission(NewSampleActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        boolean write = ActivityCompat.checkSelfPermission(NewSampleActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
        ArrayList<String> list = new ArrayList<>();
        if (!read) list.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        if (!write) list.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (list.size() > 0) {
            ActivityCompat.requestPermissions((Activity) NewSampleActivity.this, list.toArray(new String[0]), STORAGE_PERMISSION_CODE);
            return false;
        } else {
            return true;
        }
    }
}