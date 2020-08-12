package work.newproject.asus.apurv.lms.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetList {
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

        @SerializedName("receiving_form_id")
        @Expose
        private String receivingFormId;
        @SerializedName("type")
        @Expose
        private String type;
        @SerializedName("sampling_point")
        @Expose
        private String samplingPoint;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("type_of_sample")
        @Expose
        private String typeOfSample;
        @SerializedName("collected_by")
        @Expose
        private String collectedBy;
        @SerializedName("date_of_collection")
        @Expose
        private String dateOfCollection;
        @SerializedName("deposit_by")
        @Expose
        private String depositBy;
        @SerializedName("plastic")
        @Expose
        private String plastic;
        @SerializedName("packing")
        @Expose
        private String packing;
        @SerializedName("receiving_date_lab")
        @Expose
        private String receivingDateLab;
        @SerializedName("color")
        @Expose
        private String color;
        @SerializedName("login_id")
        @Expose
        private String loginId;
        @SerializedName("qr_id")
        @Expose
        private String qrId;
        @SerializedName("login_type")
        @Expose
        private String loginType;
        @SerializedName("created_on")
        @Expose
        private String createdOn;

        public String getReceivingFormId() {
            return receivingFormId;
        }

        public void setReceivingFormId(String receivingFormId) {
            this.receivingFormId = receivingFormId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getSamplingPoint() {
            return samplingPoint;
        }

        public void setSamplingPoint(String samplingPoint) {
            this.samplingPoint = samplingPoint;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getTypeOfSample() {
            return typeOfSample;
        }

        public void setTypeOfSample(String typeOfSample) {
            this.typeOfSample = typeOfSample;
        }

        public String getCollectedBy() {
            return collectedBy;
        }

        public void setCollectedBy(String collectedBy) {
            this.collectedBy = collectedBy;
        }

        public String getDateOfCollection() {
            return dateOfCollection;
        }

        public void setDateOfCollection(String dateOfCollection) {
            this.dateOfCollection = dateOfCollection;
        }

        public String getDepositBy() {
            return depositBy;
        }

        public void setDepositBy(String depositBy) {
            this.depositBy = depositBy;
        }

        public String getPlastic() {
            return plastic;
        }

        public void setPlastic(String plastic) {
            this.plastic = plastic;
        }

        public String getPacking() {
            return packing;
        }

        public void setPacking(String packing) {
            this.packing = packing;
        }

        public String getReceivingDateLab() {
            return receivingDateLab;
        }

        public void setReceivingDateLab(String receivingDateLab) {
            this.receivingDateLab = receivingDateLab;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getLoginId() {
            return loginId;
        }

        public void setLoginId(String loginId) {
            this.loginId = loginId;
        }

        public String getQrId() {
            return qrId;
        }

        public void setQrId(String qrId) {
            this.qrId = qrId;
        }

        public String getLoginType() {
            return loginType;
        }

        public void setLoginType(String loginType) {
            this.loginType = loginType;
        }

        public String getCreatedOn() {
            return createdOn;
        }

        public void setCreatedOn(String createdOn) {
            this.createdOn = createdOn;
        }

    }
}
