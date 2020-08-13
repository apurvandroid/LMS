package work.newproject.asus.apurv.lms.Admin.adapter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import work.newproject.asus.apurv.lms.Admin.TestActivity;
import work.newproject.asus.apurv.lms.Grf.FormSubMItActivity;
import work.newproject.asus.apurv.lms.R;
import work.newproject.asus.apurv.lms.adapter.SubEntyListAdapter;
import work.newproject.asus.apurv.lms.network.model.GetList;

public class SampleAdapter  extends RecyclerView.Adapter<SampleAdapter.ViewHolder> {
    Context context;
    List<GetList.Datum> list;
    DownloadPdf downloadPdf;


    public SampleAdapter(Context context, List<GetList.Datum> list) {
        this.context = context;
        this.list = list;
        this.downloadPdf= (DownloadPdf) context;

    }

    @NonNull
    @Override
    public SampleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sub_entry_list, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull SampleAdapter.ViewHolder holder, int position) {
        GetList.Datum data = list.get(position);

        holder.txtName.setText(data.getType());
        holder.txtSampleGrab.setText(data.getTypeOfSample());

        holder.txtPackingType.setText(data.getPlastic());
        holder.txtAddress.setText(data.getAddress());

        String s = data.getCreatedOn();
        String[] str = s.split(" ");  //now str[0] is "hello" and str[1] is "goodmorning,2,1"
        String str3 = str[0];  //goodmorning

        holder.txtPackingType.setText("Download PDF");
        holder.txtCollectionBY.setText(data.getReceivingDateLab());


        holder.layoutRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               downloadPdf.pdf(data.getQrId());

            }
        });

        holder.constlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, TestActivity.class);
                intent.putExtra("type", data.getType());
                intent.putExtra("barcodeID", data.getQrId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout constlayout,layoutRight;
        TextView txtName, txtSampleGrab, txtCollectionBY, txtPackingType, txtAddress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtCollectionBY = itemView.findViewById(R.id.txtCollectionBY);
            txtSampleGrab = itemView.findViewById(R.id.txtSampleGrab);
            txtPackingType = itemView.findViewById(R.id.txtPackingType);
            constlayout = itemView.findViewById(R.id.constlayout);
            txtAddress = itemView.findViewById(R.id.txtAddress);
            layoutRight=itemView.findViewById(R.id.layoutRight);
        }
    }


    public interface DownloadPdf{
        void pdf(String qrID);
    }
}
