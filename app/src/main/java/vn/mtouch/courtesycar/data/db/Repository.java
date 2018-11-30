package vn.mtouch.courtesycar.data.db;

import android.util.Log;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

/**
 * Copyright (C) 2016, Mobitouch.
 *
 * @author VuNguyen
 * @since 8/20/18
 */

public class Repository {
//    public static boolean checkInventory(InventoryModel inventoryModel) {
//        boolean isSuccess;
//        InventoryCheckedModel inventoryCheckedModel = WorkWithDB.getInventoryCheckedByFixture(inventoryModel.getSku(), inventoryModel.getFixture());
//        inventoryCheckedModel.setStoreCode(inventoryModel.getStoreCode());
//        inventoryCheckedModel.setFixture(inventoryModel.getFixture());
//        inventoryCheckedModel.setSku(inventoryModel.getSku());
//        inventoryCheckedModel.setName(inventoryModel.getName());
//        inventoryCheckedModel.setPrice(inventoryModel.getPrice());
//        inventoryCheckedModel.setQuantity(inventoryModel.getQuantity());
//        inventoryCheckedModel.setQuantityCheck(inventoryModel.getQuantityCheck());
//        inventoryCheckedModel.setTimeCheck(Calendar.getInstance().getTimeInMillis());
//
//        //isSuccess = WorkWithDB.update(inventoryModel) > 0;
//        if(inventoryCheckedModel.getState() == InventoryCheckedModel.STATE_EMPTY) {
//            isSuccess = WorkWithDB.insert(inventoryCheckedModel) > 0;
//        } else {
//            isSuccess = WorkWithDB.update(inventoryCheckedModel) > 0;
//        }
//
//        return isSuccess;
//    }
//
//    public static InventoryModel searchInventoryByBarcode(String barcode) {
//        InventoryModel result = WorkWithDB.getInventory(barcode);
//        List<InventoryCheckedModel> list = WorkWithDB.getListInventoryCheckedByBarcode2(barcode);
//        double quantity = 0;
//        for(InventoryCheckedModel item : list) {
//            quantity += item.getQuantityCheck();
//        }
//        result.setQuantityCheck(quantity);
//        return result;
//    }
//
//    public static boolean isExistInInventoryChecked(String barcode, String fixture) {
//        InventoryCheckedModel inventoryCheckedModel =  WorkWithDB.getInventoryCheckedByFixture(barcode, fixture);
//        double quantity = 0;
//        List<InventoryCheckedModel> list = WorkWithDB.getListInventoryCheckedByBarcode2(barcode);
//        for(InventoryCheckedModel item : list) {
//            quantity += item.getQuantityCheck();
//        }
//        inventoryCheckedModel.setQuantityCheck(quantity);
//        if(inventoryCheckedModel.getState() == InventoryCheckedModel.STATE_CHECKED) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    public static int insertListInventory(List<InventoryModel> items) {
//
//        int countDeleteItem = 0;
//        List<InventoryModel> orderItemDelete;
//        int size = items.size();
//        int i;
//        for(i = 0; i + MAXIMUM_ITEMS_DELETE_BY_SQL < size; i+=MAXIMUM_ITEMS_DELETE_BY_SQL) {
//            orderItemDelete = items.subList(i, i + MAXIMUM_ITEMS_DELETE_BY_SQL);
//            countDeleteItem += insertListInventoryLimitRows(orderItemDelete);
//        }
//        orderItemDelete = items.subList(i, size);
//        countDeleteItem += insertListInventoryLimitRows(orderItemDelete);
//
//        return countDeleteItem;
//    }
//
//    private static int insertListInventoryLimitRows(List<InventoryModel> lst) {
//        if(lst.size() <= 0) {
//            return 0;
//        }
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("INSERT INTO inventory ( storeCode, fixture, sku, name, price, quantity, quantityCheck) VALUES ");
//        int size = lst.size();
//        for (int i = 0; i < size; i++) {
//            stringBuilder.append("("
//                    + "'" +lst.get(i).getStoreCode().trim() +"', "
//                    + "'" +lst.get(i).getFixture().trim() +"', "
//                    + "'" +lst.get(i).getSku().trim() +"', "
//                    + "'" +lst.get(i).getName().trim() +"', "
//                    +lst.get(i).getPrice() +", "
//                    +lst.get(i).getQuantity() +", "
//                    +lst.get(i).getQuantityCheck() + ")");
//            if(i != size - 1) {
//                stringBuilder.append(", ");
//            }
//        }
//        stringBuilder.append(";");
//        Log.d("Repository", stringBuilder.toString());
//        try {
//            return WorkWithDB.getDatabaseHelper().getInventoryDao().executeRawNoArgs(stringBuilder.toString());
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return 0;
//        }
//    }
}
