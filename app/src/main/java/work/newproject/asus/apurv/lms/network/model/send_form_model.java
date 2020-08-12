package work.newproject.asus.apurv.lms.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class send_form_model {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }


    public class Datum {

        @SerializedName("asign_id")
        @Expose
        private String asignId;
        @SerializedName("qr_id")
        @Expose
        private String qrId;
        @SerializedName("json")
        @Expose
        private String json;
        @SerializedName("jrf_id")
        @Expose
        private String jrfId;
        @SerializedName("jrf_name")
        @Expose
        private String jrfName;
        @SerializedName("created_on")
        @Expose
        private String createdOn;

        public String getAsignId() {
            return asignId;
        }

        public void setAsignId(String asignId) {
            this.asignId = asignId;
        }

        public String getQrId() {
            return qrId;
        }

        public void setQrId(String qrId) {
            this.qrId = qrId;
        }

        public String getJson() {
            return json;
        }

        public void setJson(String json) {
            this.json = json;
        }

        public String getJrfId() {
            return jrfId;
        }

        public void setJrfId(String jrfId) {
            this.jrfId = jrfId;
        }

        public String getJrfName() {
            return jrfName;
        }

        public void setJrfName(String jrfName) {
            this.jrfName = jrfName;
        }

        public String getCreatedOn() {
            return createdOn;
        }

        public void setCreatedOn(String createdOn) {
            this.createdOn = createdOn;
        }

    }

}
