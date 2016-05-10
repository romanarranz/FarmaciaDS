package com.hugoroman.pharmacys.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hugoroman.pharmacys.model.Inventory;

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

        if(c != null)
            c.close();

        return inventories;
    }

    public static int getInventoryQuantity(SQLiteDatabase db, String pharmacyId, int productId) {

        String selectQuery = "SELECT " + PharmacySContract.InventoryTable.QUANTITY + " FROM " + PharmacySContract.InventoryTable.TABLE_NAME + " WHERE " +
                PharmacySContract.InventoryTable.PHARMACY_ID + " = '" + pharmacyId + "' AND " + PharmacySContract.InventoryTable.PRODUCT_ID + " = " + productId;

        Cursor c = db.rawQuery(selectQuery, null);

        int quantity = 0;

        if(c.moveToFirst())
            quantity = c.getInt(c.getColumnIndex(PharmacySContract.InventoryTable.QUANTITY));


        c.close();

        return quantity;
    }

    public static Inventory getInventory(SQLiteDatabase db, String pharmacyId, int productId) {

        String selectQuery = "SELECT * FROM " + PharmacySContract.InventoryTable.TABLE_NAME + " WHERE " +
                PharmacySContract.InventoryTable.PHARMACY_ID + " = '" + pharmacyId + "' AND " + PharmacySContract.InventoryTable.PRODUCT_ID + " = " + productId;

        Cursor c = db.rawQuery(selectQuery, null);

        Inventory inventory = null;

        if(c != null) {
            c.moveToFirst();

            inventory = new Inventory(c.getString(c.getColumnIndex(PharmacySContract.InventoryTable.PHARMACY_ID)),
                                        c.getInt(c.getColumnIndex(PharmacySContract.InventoryTable.PRODUCT_ID)),
                                        c.getFloat(c.getColumnIndex(PharmacySContract.InventoryTable.PRICE)),
                                        c.getInt(c.getColumnIndex(PharmacySContract.InventoryTable.QUANTITY)));

            c.close();
        }

        return inventory;
    }

    public static float getProductPrice(SQLiteDatabase db, String pharmacyId, int productId) {

        String selectQuery = "SELECT " + PharmacySContract.InventoryTable.PRICE + " FROM " + PharmacySContract.InventoryTable.TABLE_NAME + " WHERE " +
                PharmacySContract.InventoryTable.PHARMACY_ID + " = '" + pharmacyId + "' AND " + PharmacySContract.InventoryTable.PRODUCT_ID + " = " + productId;

        Cursor c = db.rawQuery(selectQuery, null);

        float productPrice = 0.0f;

        if(c != null) {
            c.moveToFirst();

            productPrice = c.getFloat(c.getColumnIndex(PharmacySContract.InventoryTable.PRICE));

            c.close();
        }

        return productPrice;
    }
}
