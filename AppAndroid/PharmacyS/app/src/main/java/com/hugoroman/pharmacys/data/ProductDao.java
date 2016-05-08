package com.hugoroman.pharmacys.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hugoroman.pharmacys.model.Product;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public final class ProductDao {

    public static Product getProduct(SQLiteDatabase db, int id) {

        String selectQuery = "SELECT * FROM " + PharmacySContract.ProductTable.TABLE_NAME + " WHERE "
                + PharmacySContract.ProductTable.ID + " = " + id;

        Cursor c = db.rawQuery(selectQuery, null);

        if(c != null)
            c.moveToFirst();

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

        if(c != null)
            c.close();

        return product;
    }

    public static String getProductCategoryName(SQLiteDatabase db, int idProduct) {

        String selectQueryCategoryId = "SELECT " + PharmacySContract.ProductTable.CATEGORY + " FROM " + PharmacySContract.ProductTable.TABLE_NAME + " WHERE "
                + PharmacySContract.ProductTable.ID + " = " + idProduct;

        Cursor cId = db.rawQuery(selectQueryCategoryId, null);

        if(cId!= null)
            cId.moveToFirst();

        int categoryID = cId.getInt(cId.getColumnIndex(PharmacySContract.ProductTable.CATEGORY));

        cId.close();

        String selectQueryCategoryName = "SELECT " + PharmacySContract.CategoryTable.NAME + " FROM " + PharmacySContract.CategoryTable.TABLE_NAME + " WHERE " +
                PharmacySContract.CategoryTable.ID + " = '" + categoryID + "'";

        Cursor c = db.rawQuery(selectQueryCategoryName, null);

        if(c != null)
            c.moveToFirst();

        String categoryName = c.getString(c.getColumnIndex(PharmacySContract.CategoryTable.NAME));

        c.close();

        return categoryName;
    }

    public static int getProductCategoryId(SQLiteDatabase db, int idProduct) {

        String selectQueryCategoryId = "SELECT " + PharmacySContract.ProductTable.CATEGORY + " FROM " + PharmacySContract.ProductTable.TABLE_NAME + " WHERE "
                + PharmacySContract.ProductTable.ID + " = " + idProduct;

        Cursor cId = db.rawQuery(selectQueryCategoryId, null);

        if(cId != null)
            cId.moveToFirst();

        int categoryId = cId.getInt(cId.getColumnIndex(PharmacySContract.ProductTable.CATEGORY));

        cId.close();

        return categoryId;
    }

    public static List<Product> getAllProductsByCategoryId(SQLiteDatabase db, int categoryId) {

        String selectQuery = "SELECT * FROM " + PharmacySContract.ProductTable.TABLE_NAME + " WHERE " + PharmacySContract.ProductTable.CATEGORY + " = " + categoryId;

        List<Product> products = new ArrayList<Product>();

        Cursor c = db.rawQuery(selectQuery, null);

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

        if(c != null)
            c.close();

        return products;
    }
}