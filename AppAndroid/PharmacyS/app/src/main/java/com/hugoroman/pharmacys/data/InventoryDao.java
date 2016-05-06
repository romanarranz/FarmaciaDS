package com.hugoroman.pharmacys.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.hugoroman.pharmacys.model.Inventory;
import com.hugoroman.pharmacys.model.Pharmacy;

import java.util.ArrayList;
import java.util.List;

public final class InventoryDao {

    public static List<Inventory> getPharmacyInventory(SQLiteDatabase db, String pharmacyId) {

        String selectQuery = "SELECT * FROM " + PharmacySContract.InventoryTable.TABLE_NAME + " WHERE "
                + PharmacySContract.InventoryTable.PHARMACY_ID + " = '" + pharmacyId + "'";

        //Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        List<Inventory> inventories = new ArrayList<Inventory>();

        if(c != null && c.moveToFirst()) {
            do {
                Inventory inventory = new Inventory(c.getString(c.getColumnIndex(PharmacySContract.InventoryTable.PHARMACY_ID)),
                                                    c.getInt(c.getColumnIndex(PharmacySContract.InventoryTable.PRODUCT_ID)),
                                                    c.getFloat(c.getColumnIndex(PharmacySContract.InventoryTable.PRICE)),
                                                    c.getInt(c.getColumnIndex(PharmacySContract.InventoryTable.QUANTITY)));

                inventories.add(inventory);
            } while(c.moveToNext());
        }

        return inventories;
    }
}
