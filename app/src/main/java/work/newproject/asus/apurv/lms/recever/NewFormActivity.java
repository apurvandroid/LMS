package work.newproject.asus.apurv.lms.recever;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
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
import work.newproject.asus.apurv.lms.utils.AppStrings;
import work.newproject.asus.apurv.lms.utils.MySharedpreferences;

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

    @BindView(R.id.edAddress)
    EditText edAddress;


    @BindView(R.id.progress_circular)
    ProgressBar progress_circular;

    String selectIndustriItem, selectTypeOfSampleItem, selectQty;

    List<String> indusrryType = new ArrayList<>();
    List<String> selectTpeOfSampleItmeList = new ArrayList<>();
    List<String> qtyList = new ArrayList<>();

    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_form);
        ButterKnife.bind(this);
        barcodeID = getIntent().getStringExtra("barcodeID");


        edDate.setOnClickListener(v -> openDate(v, 1));
        edDateSapmpleRec.setOnClickListener(v -> openDate(v, 2));

        btSave.setOnClickListener(v -> saveData());


        indusrryType.add("Industrial Source ");
        indusrryType.add("Sample Source (River/Lake/Pond/Drain/S.T.P./any other)");
        indusrryType.add("Sample Source (Tubewell/Dug-well/ Shallow pump/Supply water/Handpump");

        selectIndustriItem = "Industrial Source";

        selectTpeOfSampleItmeList.add("Grab");
        selectTpeOfSampleItmeList.add("Composit");
        selectTpeOfSampleItmeList.add("Integrated");

        selectTypeOfSampleItem = "Grab";

        qtyList.add("Plastic jaricane");
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

    private void openDate(View view, int id) {
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        setDate(view, id);
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view, int id) {
        if (id == 1) {
            showDialog(999);
        } else {
            showDialog(100);
        }


    }

    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        } else {
            return new DatePickerDialog(this,
                    myDateListenerRec, year, month, day);
        }
    }


    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    edDate.setText(new StringBuilder().append(year).append("-")
                            .append(month).append("-").append(day));
                }
            };


    private DatePickerDialog.OnDateSetListener myDateListenerRec = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    edDateSapmpleRec.setText(new StringBuilder().append(year).append("-")
                            .append(month).append("-").append(day));
                }
            };

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
        } else if (edAddress.getText().toString().trim().isEmpty()) {
            edAddress.setError(emptyTag);

        } else {
            showProgress();
            api.addForm(c, sampleRec, details, selectQty, sampleDignosticAndPhone, date, sampleCollectionBY, selectTypeOfSampleItem, edAddress.getText().toString().trim(), samplePoint, selectIndustriItem,barcodeID, MySharedpreferences.getInstance().get(NewFormActivity.this, AppStrings.userID),MySharedpreferences.getInstance().get(NewFormActivity.this, AppStrings.loginType))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new SingleObserver<AddNewForm>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onSuccess(@NonNull AddNewForm addNewForm) {
                            hideProgress();
                            if (addNewForm.getStatus().equalsIgnoreCase("success")) {
                                Toast.makeText(NewFormActivity.this, addNewForm.getMessage(), Toast.LENGTH_SHORT).show();
                                onBackPressed();
                            } else {
                                Toast.makeText(NewFormActivity.this, addNewForm.getMessage(), Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            hideProgress();
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


    private void showProgress() {
        progress_circular.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        progress_circular.setVisibility(View.GONE);
    }

}