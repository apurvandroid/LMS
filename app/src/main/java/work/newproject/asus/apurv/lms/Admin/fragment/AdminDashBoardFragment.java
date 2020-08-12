package work.newproject.asus.apurv.lms.Admin.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import work.newproject.asus.apurv.lms.Admin.NewSampleActivity;
import work.newproject.asus.apurv.lms.R;

public class AdminDashBoardFragment extends Fragment {


    @BindView(R.id.constanlayoutNewSapmple)
    ConstraintLayout constanlayoutNewSapmple;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.admin_dashboard_fragment, container, false);
        ButterKnife.bind(this,view);

        constanlayoutNewSapmple.setOnClickListener(v -> openNewSample());
        return view;
    }

    private void openNewSample() {

        Intent intent=new Intent(getContext(), NewSampleActivity.class);
        startActivity(intent);
    }
}