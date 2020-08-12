package work.newproject.asus.apurv.lms.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import work.newproject.asus.apurv.lms.Admin.TestActivity;
import work.newproject.asus.apurv.lms.Grf.FormSubMItActivity;
import work.newproject.asus.apurv.lms.R;
import work.newproject.asus.apurv.lms.network.model.GetList;

public class SubEntyListAdapter extends RecyclerView.Adapter<SubEntyListAdapter.ViewHolder> {
    Context context;
    List<GetList.Datum> list;
    int id;


    public SubEntyListAdapter(Context context, List<GetList.Datum> list, int id) {
        this.context = context;
        this.list = list;
        this.id = id;
    }

    @NonNull
    @Override
    public SubEntyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sub_entry_list, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SubEntyListAdapter.ViewHolder holder, int position) {
        GetList.Datum data = list.get(position);

        holder.txtName.setText(data.getType());
        holder.txtSampleGrab.setText(data.getTypeOfSample());
        holder.txtCollectionBY.setText(data.getCollectedBy());
        holder.txtPackingType.setText(data.getPlastic());
        holder.txtAddress.setText(data.getAddress());


        holder.constlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id == 2) {
                    Intent intent = new Intent(context, TestActivity.class);
                    intent.putExtra("type", data.getType());
                    intent.putExtra("barcodeID", data.getQrId());
                    context.startActivity(intent);
                } else if (id == 3) {
                    Intent intent = new Intent(context, FormSubMItActivity.class);
                    intent.putExtra("type", data.getType());
                    intent.putExtra("barcodeID", data.getQrId());
                    context.startActivity(intent);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout constlayout;
        TextView txtName, txtSampleGrab, txtCollectionBY, txtPackingType, txtAddress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtCollectionBY = itemView.findViewById(R.id.txtCollectionBY);
            txtSampleGrab = itemView.findViewById(R.id.txtSampleGrab);
            txtPackingType = itemView.findViewById(R.id.txtPackingType);
            constlayout = itemView.findViewById(R.id.constlayout);
            txtAddress = itemView.findViewById(R.id.txtAddress);
        }
    }
}
