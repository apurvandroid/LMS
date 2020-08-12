package work.newproject.asus.apurv.lms.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import work.newproject.asus.apurv.lms.Admin.adapter.TestAdapter;
import work.newproject.asus.apurv.lms.ModelClass.DataModel;
import work.newproject.asus.apurv.lms.ModelClass.GroundWaterModel;
import work.newproject.asus.apurv.lms.R;
import work.newproject.asus.apurv.lms.network.Api;
import work.newproject.asus.apurv.lms.network.ApiClints;
import work.newproject.asus.apurv.lms.network.model.AddNewForm;
import work.newproject.asus.apurv.lms.network.model.getGrfModel;

public class TestActivity extends AppCompatActivity {


    @BindView(R.id.rvList)
    RecyclerView rvList;

    @BindView(R.id.spinner)
    MaterialSpinner spinner;

    @BindView(R.id.btSave)
    Button btSave;
    String type, barcodeID;

    @BindView(R.id.progress_circular)
    ProgressBar progress_circular;
    List<GroundWaterModel> listWater;
    CompositeDisposable disposable = new CompositeDisposable();
    Api api = ApiClints.getClient().create(Api.class);

    List<DataModel> dataList = new ArrayList<>();


    int pos;
    private List<String> spinnerList;

    String valueSend = "1";
    String valueID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
        type = getIntent().getStringExtra("type");
        barcodeID = getIntent().getStringExtra("barcodeID");
        Log.d("TAG", "onCreate: " + type);
        spinnerList = new ArrayList<>();
        listWater = new ArrayList<>();
        dataList = new ArrayList<>();


        btSave.setOnClickListener(v -> getJson());

        getSpinnerData();

        spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long idd, String item) {

                pos = position;

            }
        });
        getList(type);


    }


    private void getList(String id) {
        if (id.equalsIgnoreCase("Industrial Source")) {
            List<GroundWaterModel> list = new ArrayList<>();
            for (int i = 0; i < getWasteWater().size(); i++) {
                GroundWaterModel dataModel = new GroundWaterModel();
                dataModel.setName(getWasteWater().get(i));
                dataModel.setChecked(false);
                list.add(dataModel);
            }

            listWater = list;

            LinearLayoutManager mLayoutManager = new LinearLayoutManager(TestActivity.this);
            rvList.setLayoutManager(mLayoutManager);
            TestAdapter adapter = new TestAdapter(listWater, TestActivity.this);
            rvList.setAdapter(adapter);
        } else if (id.equalsIgnoreCase("Sample Source (River/Lake/Pond/Drain/S.T.P./any other)")) {
            List<GroundWaterModel> list = new ArrayList<>();
            for (int i = 0; i < getGroundWater().size(); i++) {
                GroundWaterModel dataModel = new GroundWaterModel();
                dataModel.setName(getGroundWater().get(i));
                dataModel.setChecked(false);
                list.add(dataModel);
            }
            listWater = list;

            LinearLayoutManager mLayoutManager = new LinearLayoutManager(TestActivity.this);
            rvList.setLayoutManager(mLayoutManager);
            TestAdapter adapter = new TestAdapter(listWater, TestActivity.this);
            rvList.setAdapter(adapter);
        } else {
            List<GroundWaterModel> list = new ArrayList<>();
            for (int i = 0; i < getSurfaceList().size(); i++) {
                GroundWaterModel dataModel = new GroundWaterModel();
                dataModel.setName(getSurfaceList().get(i));
                dataModel.setChecked(false);
                list.add(dataModel);
            }

            listWater = list;
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(TestActivity.this);
            rvList.setLayoutManager(mLayoutManager);
            TestAdapter adapter = new TestAdapter(listWater, TestActivity.this);
            rvList.setAdapter(adapter);
        }
    }


    private List<String> getGroundWater() {
        List<String> groundWater = new ArrayList<>();
        groundWater.add("pH, 4500 H⁺ B (4-95 to 4-99) Electrometric Method");
        groundWater.add("Turbidity, 2130B (2-13 to 2-15) Nephelometric Method");
        groundWater.add("Colour, 2120B (2-6 to 2-7) Visual Comparison Method");
        groundWater.add("Conductivity, 2510B (2-56 to 2-59) Laboratory Method");
        groundWater.add("Suspended Solids, 2540 D (2-70 to 2-71) Total Suspended Solids dried at 103-105ºC");
        groundWater.add("Total Dissolved Solids, 2540 C (2-69 to 2-70) Total Dissolved Solids dried at 180ºC");
        groundWater.add("Total Solids, 2540 B (2-68 to 2-69) Total Solids dried at 103-105ºC");
        groundWater.add("Total Hardness, 2340 (2-48 to 2-50) EDTA Titrimetric Method");
        groundWater.add("Calcium Hardness, 3500-Ca B (3-69 to 3-70) EDTA  Titrimetric Method");
        groundWater.add("Magnesium Hardness, 3500-Mg B (3-86) Calculation Method");
        groundWater.add("Sodium Na, 3500-Na B (3-99 to 3-100) Flame Photometric Method");
        groundWater.add("Potassium K, 3500-K B (3-89 to 3-90) Flame Photometric Method");
        groundWater.add("Chloride/ Cl¯, 4500-Cl-B (4-75 to 4-76) Argentometric Method");
        groundWater.add("Fluoride as F¯ , 4500- F D (4-86 to 4-87) ( SPADNS Method), 4500-F¯ C(4-90 to 4-91) (Selective Electrode Method)");
        groundWater.add("Sulphate as SO₄¯² , 4500-SO₄¯² E (4-199 tp 4-200) Turbidimetric Method");
        groundWater.add("Phosphate-P, 4500 PD (4-163 to 4-164) Stannous Chloride Method");
        groundWater.add("Nitrate-Nitrogen 4500-NO₃¯- B, (4-127) Ultraviolet Spectophotometric Method");
        groundWater.add("Ammonical Nitrogen, 4500-NH₃ F (4-199 to 4-120) Phenate Method");
        groundWater.add("Alkalinity, 2320 B (2-37 to 2-39) Titration Method");
        groundWater.add("Total Chromium/T.Cr APHA, 3111 A+B (3-16 to 3-21) Atomic Absorption Spectrometry");
        groundWater.add("Copper/Cu, 3111-A+B (3-16 to 3-21) Atomic Absorption Spectrometry");
        groundWater.add("Cadmium/Cd 3111-A+B (3-16 to 3-21) Atomic Absorption Spectrometry");
        groundWater.add("Lead/Pb ,3111-A+B (3-16 to 3-21) Atomic Absorption Spectrometry");
        groundWater.add("Iron/Fe, 3111-A+B (3-16 to 3-21) Atomic Absorption Spectrometry");
        groundWater.add("Nickel/Ni, 3111-A+B (3-16 to 3-21) Atomic Absorption Spectrometry");
        groundWater.add("Zinc/Zn , 3111-A+B (3-16 to 3-21) Atomic Absorption Spectrometry");
        return groundWater;
    }


    private List<String> getWasteWater() {
        List<String> wasteWater = new ArrayList<>();
        wasteWater.add("Colour and Odour");
        wasteWater.add("Suspended Solids mg/l, Max");
        wasteWater.add("Particulate size of suspended solids");
        wasteWater.add("pH Value");
        wasteWater.add("Temperature");
        wasteWater.add("Oil and Grease mg/l Max.");
        wasteWater.add("Total residual chorine mg/l Max");
        wasteWater.add("Ammonical Nitrogen (as N), mg/l Max");
        wasteWater.add("Total Kjeldahl;Nitrogen (as NH₃) mg/l Max");
        wasteWater.add("Free ammonia (as NH₃) mg/l Max");
        wasteWater.add("Biochemical Oxygen Demand 1(3 days at 27ºC) mg/l, Max");
        wasteWater.add("Chemical Oxygen Demand, mg/l, Max");
        wasteWater.add("Arsenic ( as As), mg/l, Max");
        wasteWater.add("Mercury (as Hg), mg/l, Max");
        wasteWater.add("Lead (as Pb), mg/l, Max");
        wasteWater.add("Cadmium (as Cd), mg/l, Max");
        wasteWater.add("Hexavalent chromium (as Cr⁺⁶), mg/l, Max");
        wasteWater.add("Total chromium (as Cr) mg/l, Max");
        wasteWater.add("Copper (as Cu, mg/l, Max");
        wasteWater.add("Zinc (as Zn), mg/l, Max");
        wasteWater.add("Selenium (as Se), mg/l, Max");
        wasteWater.add("Nickel (as Ni), mg/l, Max");
        wasteWater.add("Cyanide (as CN), mg/l, Max");
        wasteWater.add("Fluoride (as F), mg/l, Max");
        wasteWater.add("Dissolved Phosphates (as P), mg/l, Max");
        wasteWater.add("Sulphide (as S), mg/l, Max");
        wasteWater.add("Phenolic Compounds (as C₆H₅OH) mg/l, Max ");
        wasteWater.add("Radioactive materials :                           (a) Alpha emitter micro curie/ml         (b) Beta emitter micro curie/ml");
        wasteWater.add("Bio- assay test");
        wasteWater.add("Manganese (as Mn)");
        wasteWater.add("Iron (as Fe)");
        wasteWater.add("Vanadium (as V)");
        wasteWater.add("Nitrate Nitrogen");
        return wasteWater;
    }


    private List<String> getSurfaceList() {
        List<String> surfaceWater = new ArrayList<>();
        surfaceWater.add("pH, 4500 B  Electrometric Method");
        surfaceWater.add("Turbidity, 2130B  Nephelometric Method");
        surfaceWater.add("Colour, 2120B Visual Comparison Method");
        surfaceWater.add("Conductivity, 2510B Laboratory Method");
        surfaceWater.add("Suspended Solids, 2540D Total Suspended Solids dried at 103-105ºC");
        surfaceWater.add("Total Dissolved Solids, 2540C Total Dissolved Solids dried at 180ºC");
        surfaceWater.add("Total Solids, 2540B Total Solids dried at 103-105ºC");
        surfaceWater.add("Total Hardness, 2340 EDTA Titrimetric Method");
        surfaceWater.add("Calcium Hardness, 3500-Ca B EDTA  Titrimetric Method");
        surfaceWater.add("Magnesium Hardness, 3500-Mg B Calculation Method");
        surfaceWater.add("Sodium Na, 3500-Na B Flame Photometric Method");
        surfaceWater.add("Potassium K, 3500-K B Flame Photometric Method");
        surfaceWater.add("Chloride/ Cl-, 4500-Cl-B  Argentometric Method");
        surfaceWater.add("Fluoride as F, 4500- F D SPADNS Method");
        surfaceWater.add("Sulphate as SO₄¯² , 4500-SO₄¯² E Turbidimetric Method");
        surfaceWater.add("Phosphate-P, 4500 PD Stannous Chloride Method");
        surfaceWater.add("Nitrate-Nitrogen 4500-NO₃¯-B,  Ultraviolet Spectrophotometric Method");
        surfaceWater.add("Ammonical Nitrogen, 4500-NH₃ F Phenate Method");
        surfaceWater.add("Alkalinity, 2320B Titrimetric Method");
        surfaceWater.add("D.O. 4500-OB Iodometric Method");
        surfaceWater.add("B.O.D. 3 Day 27ºC IS 3025 (Part 44): 1993 Biochemical oxygen demand ");
        surfaceWater.add("C.O.D., 5220 D Open Refflux Method ");
        surfaceWater.add("Total Chromium/T.Cr APHA, 3111 B Atomic Absorption Spectrometry");
        surfaceWater.add("Copper/ Cu, 3111-B  Atomic Absorption Spectrometry");
        surfaceWater.add("Cadmium/Cd 3111-B  Atomic absorption Spectrometry");
        surfaceWater.add("Lead/ Pb , 3111-B  Atomic Absorption Spectrometry");
        surfaceWater.add("Iron / Fe,3111-B  Atomic Absorption Spectrometry");
        surfaceWater.add("Nickel/Ni, 3111-B  Atomic Absorption Spectrometry");
        surfaceWater.add("Zinc/ Zn , 3111-B  Atomic Absorption Spectrometry");

        return surfaceWater;
    }


    private void getJson() {

        Pattern p = Pattern.compile("\\[(.*?)\\]");
        Matcher m = p.matcher(listWater.toString());

        while (m.find()) {
            Gson gson = new Gson();
            String jsonCartList = gson.toJson(listWater);
            //      Log.d("TAG", "getJson: "+jsonCartList);
            for (int i = 0; i < spinnerList.size(); i++) {
                if (pos == i) {
                    valueSend = dataList.get(i).getName();
                    valueID = dataList.get(i).getId();
                    break;
                }
            }


            showProgress();
            api.sendTestForm(valueID, valueSend, barcodeID, jsonCartList).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<AddNewForm>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onSuccess(@NonNull AddNewForm addNewForm) {
                            if (addNewForm.getStatus().equalsIgnoreCase("success")) {
                                Toast.makeText(TestActivity.this, addNewForm.getMessage(), Toast.LENGTH_SHORT).show();
                                onBackPressed();
                            } else {
                                Toast.makeText(TestActivity.this, addNewForm.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                            hideProgress();
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            hideProgress();
                        }
                    });

        }
    }


    private void getSpinnerData() {

        showProgress();
        api.getGrfLIst().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<getGrfModel>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onSuccess(@NonNull getGrfModel getGrfModel) {
                        if (getGrfModel.getStatus().equalsIgnoreCase("success")) {
                            for (int i = 0; i < getGrfModel.getData().size(); i++) {
                                spinnerList.add(getGrfModel.getData().get(i).getName());
                                DataModel model = new DataModel();
                                model.setId(getGrfModel.getData().get(i).getJrfId());
                                model.setName(getGrfModel.getData().get(i).getName());
                                dataList.add(model);
                            }

                            valueSend = getGrfModel.getData().get(0).getName();
                            pos = 0;
                            spinner.setItems(spinnerList);
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

}