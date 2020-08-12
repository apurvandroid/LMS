package work.newproject.asus.apurv.lms.Admin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import work.newproject.asus.apurv.lms.ModelClass.GroundWaterModel;
import work.newproject.asus.apurv.lms.R;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {

    List<GroundWaterModel> list;
    Context context;
    private LayoutInflater inflater;

    public TestAdapter(List<GroundWaterModel> list, Context context) {
        this.list = list;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.from(context).inflate(R.layout.test_list_adapter, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TestAdapter.ViewHolder holder, int position) {

        final GroundWaterModel model = list.get(position);
        holder.txtString.setText(model.getName());

        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setChecked(list.get(position).isChecked());

        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                list.get(holder.getAdapterPosition()).setChecked(isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        TextView txtString;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.setIsRecyclable(false);
            txtString = itemView.findViewById(R.id.txtString);
            checkBox = itemView.findViewById(R.id.checkBox);
        }
    }
}
