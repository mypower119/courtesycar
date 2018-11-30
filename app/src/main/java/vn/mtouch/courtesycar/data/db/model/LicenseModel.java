package vn.mtouch.courtesycar.data.db.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C) 2016, Mobitouch.
 *
 * @author VuNguyen
 * @since 11/28/18
 */

public class LicenseModel {
    String name;
    int type;

    public static List<LicenseModel> getAllLicense() {
        ArrayList<LicenseModel> arrayList = new ArrayList<>();

        LicenseModel licenseModel1 = new LicenseModel();
        licenseModel1.setName("Hello");
        licenseModel1.setType(0);
        arrayList.add(licenseModel1);

        LicenseModel licenseModel2 = new LicenseModel();
        licenseModel1.setName("Hello");
        licenseModel1.setType(1);
        arrayList.add(licenseModel2);

        LicenseModel licenseModel3 = new LicenseModel();
        licenseModel1.setName("Hello");
        licenseModel1.setType(2);
        arrayList.add(licenseModel3);

        LicenseModel licenseModel4 = new LicenseModel();
        licenseModel1.setName("Hello");
        licenseModel1.setType(3);
        arrayList.add(licenseModel4);

        return arrayList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
