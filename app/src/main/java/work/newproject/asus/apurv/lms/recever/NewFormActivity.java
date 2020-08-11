package work.newproject.asus.apurv.lms.recever;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import work.newproject.asus.apurv.lms.R;
import work.newproject.asus.apurv.lms.network.Api;
import work.newproject.asus.apurv.lms.network.ApiClints;
import work.newproject.asus.apurv.lms.network.model.AddNewForm;

public class NewFormActivity extends AppCompatActivity {

    String barcodeID;

    private String emptyTag = "Empty";

    CompositeDisposable disposable = new CompositeDisposable();
    Api api = ApiClints.getClient().create(Api.class);


    @BindView(R.id.spinnerIndusrey)
    Spinner spinnerIndusrey;

    @BindView(R.id.spinnerTypeOfSample)
    Spinner spinnerTypeOfSample;

    @BindView(R.id.editTextTextPersonName)
    EditText edSamplePoint;

    @BindView(R.id.editTextTextPersonName2)
    EditText edSampleCollectionBy;

    @BindView(R.id.date)
    EditText edDate;

    @BindView(R.id.phone)
    EditText edSampleDignosticAndPhone;

    @BindView(R.id.text2)
    Spinner spinnerQty;

    @BindView(R.id.details)
    EditText edDetails;

    @BindView(R.id.date2)
    EditText edDateSapmpleRec;

    @BindView(R.id.color)
    EditText color;

    @BindView(R.id.btSave)
    Button btSave;


    String selectIndustriItem, selectTypeOfSampleItem, selectQty;

    List<String> indusrryType = new ArrayList<>();
    List<String> selectTpeOfSampleItmeList = new ArrayList<>();
    List<String> qtyList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_form);
        ButterKnife.bind(this);
        barcodeID = getIntent().getStringExtra("barcodeID");

        btSave.setOnClickListener(v -> saveData());


        indusrryType.add("Industrial Source ");
        indusrryType.add("Sample Source (River/Lake/Pond/Drain/S.T.P./any other)");
        indusrryType.add("Sample Source (Tubewell/Dug-well/ Shallow pump/Supply water/Handpump");

        selectIndustriItem = "Industrial Source";

        selectTpeOfSampleItmeList.add("Grab");
        selectTpeOfSampleItmeList.add("Composit");
        selectTpeOfSampleItmeList.add("Integrated");

        selectTypeOfSampleItem = "Grab";

        qtyList.add("Plastic");
        qtyList.add("jaricane");
        qtyList.add("other");

        selectQty = "Plastic";


        ArrayAdapter a = new ArrayAdapter(this, android.R.layout.simple_spinner_item, qtyList);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerQty.setAdapter(a);


        ArrayAdapter aaa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, selectTpeOfSampleItmeList);
        aaa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTypeOfSample.setAdapter(aaa);

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, indusrryType);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerIndusrey.setAdapter(aa);


        spinnerIndusrey.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapter, View v, int i, long lng) {

                selectIndustriItem = adapter.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });


        spinnerTypeOfSample.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapter, View v, int i, long lng) {

                selectTypeOfSampleItem = adapter.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });


        spinnerQty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView adapter, View v, int i, long lng) {

                selectQty = adapter.getItemAtPosition(i).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }
        });


    }


    private void saveData() {
        String samplePoint = edSamplePoint.getText().toString().trim();
        String sampleCollectionBY = edSampleCollectionBy.getText().toString().trim();
        String date = edDate.getText().toString().trim();
        String sampleDignosticAndPhone = edSampleDignosticAndPhone.getText().toString().trim();
        String details = edDetails.getText().toString().trim();
        String sampleRec = edDateSapmpleRec.getText().toString().trim();
        String c = color.getText().toString().trim();


        if (samplePoint.isEmpty()) {
            edSamplePoint.setError(emptyTag);
        } else if (sampleCollectionBY.isEmpty()) {
            edSampleCollectionBy.setError(emptyTag);
        } else if (date.isEmpty()) {
            edDate.setError(emptyTag);
        } else if (sampleDignosticAndPhone.isEmpty()) {
            edSampleDignosticAndPhone.setError(emptyTag);
        } else if (details.isEmpty()) {
            edDetails.setError(emptyTag);
        } else if (sampleRec.isEmpty()) {
            edDateSapmpleRec.setError(emptyTag);
        } else if (c.isEmpty()) {
            color.setError(emptyTag);
        } else {
            api.addForm(c, sampleRec, selectQty, "", "", sampleDignosticAndPhone, date, sampleCollectionBY, selectTypeOfSampleItem, "", "", "")
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new SingleObserver<AddNewForm>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onSuccess(@NonNull AddNewForm addNewForm) {

                        }

                        @Override
                        public void onError(@NonNull Throwable e) {

                        }
                    });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(NewFormActivity.this, ReceverDashBoardActivity.class);
        startActivity(intent);
        finish();
    }
}