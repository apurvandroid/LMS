package work.newproject.asus.apurv.lms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import work.newproject.asus.apurv.lms.R;
import work.newproject.asus.apurv.lms.network.model.GetList;

public class GrfSampleFormAdapter extends RecyclerView.Adapter<GrfSampleFormAdapter.ViewHolder>  {

    Context context;
    List<GetList.Datum> list;


    public GrfSampleFormAdapter(Context context, List<GetList.Datum> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public GrfSampleFormAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.grf_simple_adapter, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GrfSampleFormAdapter.ViewHolder holder, int position) {

        GetList.Datum data = list.get(position);

        holder.txtName.setText(data.getType());
        holder.txtSampleGrab.setText(data.getTypeOfSample());
        holder.txtCollectionBY.setText(data.getCollectedBy());
        holder.txtPackingType.setText(data.getPlastic());
        holder.txtAddress.setText(data.getAddress());
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
