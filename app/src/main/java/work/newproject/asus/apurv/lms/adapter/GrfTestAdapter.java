package work.newproject.asus.apurv.lms.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import work.newproject.asus.apurv.lms.Admin.adapter.TestAdapter;
import work.newproject.asus.apurv.lms.ModelClass.GroundWaterModel;
import work.newproject.asus.apurv.lms.R;
import work.newproject.asus.apurv.lms.network.model.send_form_model;

public class GrfTestAdapter extends RecyclerView.Adapter<GrfTestAdapter.ViewHolder> {

    Context context;
    List<GroundWaterModel> list;
    private LayoutInflater inflater;
    SetData data;

    public GrfTestAdapter(Context context, List<GroundWaterModel> list) {
        this.context = context;
        this.list = list;
        this.inflater = LayoutInflater.from(context);
        this.data= (SetData) context;
    }

    @NonNull
    @Override
    public GrfTestAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.from(context).inflate(R.layout.grf_test_report, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GrfTestAdapter.ViewHolder holder, int position) {
        GroundWaterModel datum=list.get(position);
        holder.txtString.setText(datum.getName());
        holder.checkBox.setChecked(datum.isChecked());

       if (holder.checkBox.isChecked()){
           holder.edNote.addTextChangedListener(new TextWatcher() {
               @Override
               public void beforeTextChanged(CharSequence s, int start, int count, int after) {

               }

               @Override
               public void onTextChanged(CharSequence s, int start, int before, int count) {
                   data.setData(holder.edNote.getText().toString(),datum.getName(),datum.isChecked(),position);

               }

               @Override
               public void afterTextChanged(Editable s) {

               }
           });
       }else {
           holder.edNote.setEnabled(false);
       }




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        TextView txtString;
        EditText edNote;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.setIsRecyclable(false);
            txtString = itemView.findViewById(R.id.txtString);
            checkBox = itemView.findViewById(R.id.checkBox);
            edNote=itemView.findViewById(R.id.edNote);
        }
    }

    public interface SetData{
        void setData(String text,String name,boolean checked,int pos);
    }
}
