package vn.mtouch.courtesycar.data.db.local.room;

import vn.mtouch.courtesycar.data.db.model.BorrowContractModel;
import vn.mtouch.courtesycar.data.db.model.CarModel;
import vn.mtouch.courtesycar.data.db.model.roomdb.BorrowContractDBO;
import vn.mtouch.courtesycar.data.db.model.roomdb.CarDBO;

/**
 * Copyright (C) 2016, Mobitouch.
 *
 * @author VuNguyen
 * @since 12/2/18
 */

public class Mappers {

    // tham số là dbo
    public static final Mapper<CarDBO, CarModel> FROM_DBO_TO_CAR = dbo -> {
        CarModel carModel = new CarModel();

        if (dbo != null) {
            carModel.setCarCode(dbo.carCode);
            carModel.setCarName(dbo.carName);
            carModel.setQrCode(dbo.qrCode);
            carModel.setId(dbo.id);
        }

        return carModel;
    };

    public static final Mapper<BorrowContractDBO, BorrowContractModel> FROM_DBO_TO_CONTRACT = dbo -> {
        BorrowContractModel contract = new BorrowContractModel();

        if (dbo != null) {
            contract = new BorrowContractModel(
                    dbo.id, dbo.carName, dbo.carCode,
                    dbo.timeIn, dbo.timeOut, dbo.fullName, dbo.dateOfBirth,
                    dbo.phoneNumber, dbo.state, dbo.licenseType,
                    dbo.qrCode, dbo.pathFrontLicense, dbo.pathBackLicense);
        }
        return contract;
    };
}
