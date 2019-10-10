package vn.mtouch.courtesycar.presentation.features.backup_restore.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import vn.mtouch.courtesycar.data.db.model.BorrowContractModel;
import vn.mtouch.courtesycar.data.db.model.CarModel;

public class SyncDataModel {
    @SerializedName("cars")
    @Expose
    List<CarModel> cars;
    @SerializedName("contracts")
    @Expose
    List<BorrowContractModel> contracts;
    @SerializedName("term")
    @Expose
    String term;

    public List<CarModel> getCars() {
        return cars;
    }

    public void setCars(List<CarModel> cars) {
        this.cars = cars;
    }

    public List<BorrowContractModel> getContracts() {
        return contracts;
    }

    public void setContracts(List<BorrowContractModel> contracts) {
        this.contracts = contracts;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
