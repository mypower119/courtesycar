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
    public static final int BASE_LICENSE_TYPE = 0;
    public static final int BASE_LICENSE_TYPE_1 = BASE_LICENSE_TYPE + 1;
    public static final int BASE_LICENSE_TYPE_2 = BASE_LICENSE_TYPE + 2;
    public static final int BASE_LICENSE_TYPE_3 = BASE_LICENSE_TYPE + 3;
    String name = "";
    int type = 0;

    public static List<LicenseModel> getAllLicense() {
        ArrayList<LicenseModel> arrayList = new ArrayList<>();

        LicenseModel licenseModel1 = new LicenseModel();
        licenseModel1.setName("Driver License from my country");
        licenseModel1.setType(BASE_LICENSE_TYPE);
        arrayList.add(licenseModel1);

        LicenseModel licenseModel2 = new LicenseModel();
        licenseModel2.setName("Full (Australia)");
        licenseModel2.setType(BASE_LICENSE_TYPE_1);
        arrayList.add(licenseModel2);

        LicenseModel licenseModel3 = new LicenseModel();
        licenseModel3.setName("P2");
        licenseModel3.setType(BASE_LICENSE_TYPE_2);
        arrayList.add(licenseModel3);

        LicenseModel licenseModel4 = new LicenseModel();
        licenseModel4.setName("P1");
        licenseModel4.setType(BASE_LICENSE_TYPE_3);
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
