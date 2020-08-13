package work.newproject.asus.apurv.lms.Grf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jaredrummler.materialspinner.MaterialSpinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import work.newproject.asus.apurv.lms.Admin.TestActivity;
import work.newproject.asus.apurv.lms.ModelClass.DataModel;
import work.newproject.asus.apurv.lms.ModelClass.GroundWaterModel;
import work.newproject.asus.apurv.lms.R;
import work.newproject.asus.apurv.lms.adapter.GrfTestAdapter;
import work.newproject.asus.apurv.lms.adapter.SubEntyListAdapter;
import work.newproject.asus.apurv.lms.network.Api;
import work.newproject.asus.apurv.lms.network.ApiClints;
import work.newproject.asus.apurv.lms.network.model.AddNewForm;
import work.newproject.asus.apurv.lms.network.model.send_form_model;
import work.newproject.asus.apurv.lms.utils.AppStrings;
import work.newproject.asus.apurv.lms.utils.MySharedpreferences;

public class FormSubMItActivity extends AppCompatActivity implements GrfTestAdapter.SetData {


    @BindView(R.id.rvList)
    RecyclerView rvList;


    @BindView(R.id.btSave)
    Button btSave;
    String type, barcodeID;

    List<GroundWaterModel> list;

    @BindView(R.id.progress_circular)
    ProgressBar progress_circular;
    List<GroundWaterModel> listWater;
    CompositeDisposable disposable = new CompositeDisposable();
    Api api = ApiClints.getClient().create(Api.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_sub_m_it);
        ButterKnife.bind(this);
        barcodeID = getIntent().getStringExtra("barcodeID");
        list = new ArrayList<>();


        btSave.setOnClickListener(v -> sendForm());
        getDataList();
    }


    private void getDataList() {
        showProgress();
        api.sendForm(MySharedpreferences.getInstance().get(this, AppStrings.userID), barcodeID).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<send_form_model>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull send_form_model send_form_model) {

                        if (send_form_model.getStatus().equalsIgnoreCase("success")) {

                            try {
                                JSONArray jsonArray = new JSONArray(send_form_model.getData().get(0).getJson());
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    GroundWaterModel model = new GroundWaterModel();
                                    model.setChecked(jsonObject.getBoolean("isChecked"));
                                    model.setName(jsonObject.getString("name"));
                                    model.setNote("-");
                                    model.setValue(jsonObject.getString("value"));
                                    model.setPosition(jsonObject.getInt("position"));

                                    list.add(model);
                                }


                                Log.d("TAG", "onSuccess: " + list.get(3).getName());
                                GrfTestAdapter mainCatAdapter = new GrfTestAdapter(FormSubMItActivity.this, list);
                                rvList.setLayoutManager(new LinearLayoutManager(FormSubMItActivity.this, LinearLayoutManager.VERTICAL, false));
                                rvList.setAdapter(mainCatAdapter);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }


                        }
                        hideProgress();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        hideProgress();
                    }
                });
    }


    @Override
    public void setData(String text, String name, boolean checked,String value,int dataValue, int pos) {
        GroundWaterModel model = new GroundWaterModel();
        for (int i = 0; i < list.size(); i++) {

            model.setChecked(checked);
            model.setName(name);
            model.setNote(text);
            model.setPosition(dataValue);
            model.setValue(value);


       /*
            if (list.get(i).isChecked()){
                Log.d("TAG", "setData: "+list.get(i).getName());

            }*/

        }
        list.remove(pos);
        list.add(pos, model);
    }


    private void sendForm() {
        showProgress();
        Gson gson = new Gson();
        String jsonCartList = gson.toJson(list);
        api.sendGrfForm(MySharedpreferences.getInstance().get(this, AppStrings.userID), barcodeID, jsonCartList).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<AddNewForm>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull AddNewForm addNewForm) {
                        Log.d("TAG", "onSuccess: "+addNewForm.getStatus());
                        if (addNewForm.getStatus().equalsIgnoreCase("success")) {
                            Toast.makeText(FormSubMItActivity.this, addNewForm.getMessage(), Toast.LENGTH_SHORT).show();
                            onBackPressed();
                        } else {
                            Toast.makeText(FormSubMItActivity.this, addNewForm.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        hideProgress();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        hideProgress();
                        Log.d("TAG", "onError: "+e.getMessage());
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