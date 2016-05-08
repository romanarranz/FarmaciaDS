package com.hugoroman.pharmacys.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hugoroman.pharmacys.model.Basket;
import com.hugoroman.pharmacys.model.Pharmacy;
import com.hugoroman.pharmacys.model.Product;

import java.util.ArrayList;
import java.util.List;

public final class BasketDao {

    public static Basket getBasket(SQLiteDatabase db) {

        String selectQuery = "SELECT * FROM " + PharmacySContract.BasketTable.TABLE_NAME;

        Cursor c = db.rawQuery(selectQuery, null);

        List<List<Object>> productsPharmaciesQuantities = new ArrayList<List<Object>>();

        if(c != null && c.moveToFirst()) {
            do {
                List<Object> productPharmacyQuantity = new ArrayList<Object>();

                String pharmacyCif = c.getString(c.getColumnIndex(PharmacySContract.BasketTable.PHARMACY_ID));

                Pharmacy pharmacy = PharmacyDao.getPharmacy(db, pharmacyCif);

                int productID = c.getInt(c.getColumnIndex(PharmacySContract.BasketTable.PRODUCT_ID));

                Product product = ProductDao.getProduct(db, productID);

                productPharmacyQuantity.add(pharmacy);
                productPharmacyQuantity.add(product);
                productPharmacyQuantity.add(c.getInt(c.getColumnIndex(PharmacySContract.BasketTable.QUANTITY)));

                productsPharmaciesQuantities.add(productPharmacyQuantity);
            } while(c.moveToNext());
        }

        if(c != null)
            c.close();

        return new Basket(productsPharmaciesQuantities);
    }

    public static void addToBasket(SQLiteDatabase db, String pharmacyCif, int productId, int quantity) {

        String selectQuery = "SELECT * FROM " + PharmacySContract.BasketTable.TABLE_NAME + " WHERE " +
                PharmacySContract.BasketTable.PHARMACY_ID + " = '" + pharmacyCif + "' AND " + PharmacySContract.BasketTable.PRODUCT_ID + " = " + productId;

        Cursor c = db.rawQuery(selectQuery, null);

        ContentValues contentValues = new ContentValues();

        boolean update = false;

        if(c != null)
            update = c.moveToFirst();

        if(!update) {
            contentValues.put(PharmacySContract.BasketTable.PHARMACY_ID, pharmacyCif);
            contentValues.put(PharmacySContract.BasketTable.PRODUCT_ID, productId);
            contentValues.put(PharmacySContract.BasketTable.QUANTITY, quantity);
            db.insert(PharmacySContract.BasketTable.TABLE_NAME, null, contentValues);
        }
        else {
            contentValues.put(PharmacySContract.BasketTable.QUANTITY, quantity);
            db.update(PharmacySContract.BasketTable.TABLE_NAME, contentValues,
                    PharmacySContract.BasketTable.PHARMACY_ID + " = ? AND " + PharmacySContract.BasketTable.PRODUCT_ID + " = ?", new String[] { pharmacyCif, String.valueOf(productId) });
        }
    }

    public static void removeFromBasket(SQLiteDatabase db, String pharmacyCif, int productId) {

        db.delete(PharmacySContract.BasketTable.TABLE_NAME,
                PharmacySContract.BasketTable.PHARMACY_ID + " = ? AND " + PharmacySContract.BasketTable.PRODUCT_ID + " = ?",
                new String[] { pharmacyCif, String.valueOf(productId) });
    }
}
