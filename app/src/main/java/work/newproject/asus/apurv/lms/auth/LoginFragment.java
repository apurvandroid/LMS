package work.newproject.asus.apurv.lms.auth;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import work.newproject.asus.apurv.lms.Admin.AdminDashboard;
import work.newproject.asus.apurv.lms.Grf.DrfDashBoardActivity;
import work.newproject.asus.apurv.lms.R;
import work.newproject.asus.apurv.lms.network.Api;
import work.newproject.asus.apurv.lms.network.ApiClints;
import work.newproject.asus.apurv.lms.network.model.LoginModel;
import work.newproject.asus.apurv.lms.recever.ReceverDashBoardActivity;
import work.newproject.asus.apurv.lms.utils.AppStrings;
import work.newproject.asus.apurv.lms.utils.MySharedpreferences;


public class LoginFragment extends Fragment {


    @BindView(R.id.tabLayout)
    TabLayout tabs;

    CompositeDisposable disposable = new CompositeDisposable();
    Api api = ApiClints.getClient().create(Api.class);


    @BindView(R.id.editTextTextEmailAddress)
    EditText editTextTextEmailAddress;

    @BindView(R.id.editTextTextPassword)
    EditText editTextTextPassword;

    @BindView(R.id.button)
    Button button;

    String loginType;

    @BindView(R.id.progress_circular)
    ProgressBar progress_circular;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        loginType = "admin";
        tabs.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                if (tab.getPosition() == 0) {
                    loginType = "admin";
                } else if (tab.getPosition() == 1) {
                    loginType = "jrf";
                } else if (tab.getPosition() == 2) {
                    loginType = "reciever";
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //        tabs.setBackground(getResources().getC);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        button.setOnClickListener(v -> login());
        return view;
    }


    private void login() {
        String number = editTextTextEmailAddress.getText().toString().trim();
        String pass = editTextTextPassword.getText().toString().trim();


        if (number.isEmpty()) {
            editTextTextEmailAddress.setError("Empty");
        } else if (pass.isEmpty()) {
            editTextTextPassword.setError("Empty");
        } else {
            showProgress();
            api.login(number, pass, loginType)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(new SingleObserver<LoginModel>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {

                        }

                        @Override
                        public void onSuccess(@NonNull LoginModel loginModel) {

                            if (loginModel.getStatus().equalsIgnoreCase("success")) {
                                if (loginModel.getData().get(0).getType().equalsIgnoreCase("reciever")) {
                                    MySharedpreferences.getInstance().save(getContext(), AppStrings.userID, loginModel.getData().get(0).getId());
                                    MySharedpreferences.getInstance().save(getContext(), AppStrings.loginType, loginModel.getData().get(0).getType());
                                    Intent intent = new Intent(getContext(), ReceverDashBoardActivity.class);
                                    startActivity(intent);
                                    getActivity().finish();
                                } else if (loginModel.getData().get(0).getType().equalsIgnoreCase("admin")) {
                                    Log.d("TAG", "onSuccess: " + loginModel.getData().get(0).getId());
                                    Log.d("TAG", "onSuccess: " + loginModel.getData().get(0).getType());
                                    MySharedpreferences.getInstance().save(getContext(), AppStrings.userID, loginModel.getData().get(0).getId());
                                    MySharedpreferences.getInstance().save(getContext(), AppStrings.loginType, loginModel.getData().get(0).getType());
                                    Intent intent = new Intent(getContext(), AdminDashboard.class);
                                    startActivity(intent);
                                    getActivity().finish();
                                }else {
                                    MySharedpreferences.getInstance().save(getContext(), AppStrings.userID, loginModel.getData().get(0).getId());
                                    MySharedpreferences.getInstance().save(getContext(), AppStrings.loginType, loginModel.getData().get(0).getType());
                                    Intent intent = new Intent(getContext(), DrfDashBoardActivity.class);
                                    startActivity(intent);
                                    getActivity().finish();
                                }

                            }


                            hideProgress();
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            hideProgress();
                            Log.d("TAG", "onError: " + e.getMessage());
                        }
                    });
        }

    }

    private void showProgress() {
        progress_circular.setVisibility(View.VISIBLE);
    }


    private void hideProgress() {
        progress_circular.setVisibility(View.INVISIBLE);
    }

}