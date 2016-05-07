package com.hugoroman.pharmacys.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hugoroman.pharmacys.model.Basket;
import com.hugoroman.pharmacys.model.Product;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public final class BasketDao {

    public static Basket getBasket(SQLiteDatabase db, String pharmacyCif, int productId) {

        String selectQuery = "SELECT * FROM " + PharmacySContract.BasketTable.TABLE_NAME + " WHERE " +
                PharmacySContract.BasketTable.PHARMACY_ID + " = '" + pharmacyCif + "' AND " + PharmacySContract.BasketTable.PRODUCT_ID + " = " + productId;

        Cursor c = db.rawQuery(selectQuery, null);

        List<Product> products = new ArrayList<Product>();

        if(c != null && c.moveToFirst()) {
            do {
                Product product = new Product(c.getInt(c.getColumnIndex(PharmacySContract.ProductTable.ID)),
                                                c.getInt(c.getColumnIndex(PharmacySContract.ProductTable.CATEGORY)),
                                                c.getString(c.getColumnIndex(PharmacySContract.ProductTable.NAME)),
                                                c.getString(c.getColumnIndex(PharmacySContract.ProductTable.DESCRIPTION)),
                                                c.getString(c.getColumnIndex(PharmacySContract.ProductTable.LABORATORY)),
                                                c.getString(c.getColumnIndex(PharmacySContract.ProductTable.UNITS)),
                                                new Date(c.getLong(c.getColumnIndex(PharmacySContract.ProductTable.EXPIRATION_DATE))),
                                                c.getInt(c.getColumnIndex(PharmacySContract.ProductTable.SIZE)),
                                                c.getString(c.getColumnIndex(PharmacySContract.ProductTable.LOT)),
                                                c.getString(c.getColumnIndex(PharmacySContract.ProductTable.URL_IMAGE)));

                products.add(product);
            } while(c.moveToNext());
        }

        return new Basket(products);
    }
}
