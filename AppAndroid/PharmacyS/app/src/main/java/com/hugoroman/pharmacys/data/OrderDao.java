package com.hugoroman.pharmacys.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hugoroman.pharmacys.data.PharmacySContract.OrderTable;
import com.hugoroman.pharmacys.data.PharmacySContract.OrderProductTable;
import com.hugoroman.pharmacys.model.Order;
import com.hugoroman.pharmacys.model.Product;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public final class OrderDao {

    public static List<Order> getAllOrders(SQLiteDatabase db, String userEmail) {

        String selectQuery = "SELECT * FROM " + OrderTable.TABLE_NAME + " WHERE " +
                OrderTable.USER_ID + " = '" + userEmail + "'";

        Cursor c = db.rawQuery(selectQuery, null);

        List<Order> orders = new ArrayList<Order>();

        if(c != null && c.moveToFirst()) {
            do {
                Order order = new Order(c.getInt(c.getColumnIndex(OrderTable.ID)),
                                        c.getString(c.getColumnIndex(OrderTable.USER_ID)),
                                        c.getString(c.getColumnIndex(OrderTable.PHARMACY_ID)),
                                        new Date(c.getLong(c.getColumnIndex(OrderTable.DATE))));

                orders.add(order);

            } while(c.moveToNext());

            c.close();
        }

        return orders;
    }

    public static List<List<String>> getOrderInfo(SQLiteDatabase db, int orderId) {

        String selectQuery = "SELECT * FROM " + OrderProductTable.TABLE_NAME + " WHERE " +
                OrderProductTable.ID_ORDER + " = " + orderId;


        Cursor c = db.rawQuery(selectQuery, null);

        List<List<String>> productsQuantity = new ArrayList<List<String>>();

        if(c != null && c.moveToFirst()) {
            do {
                List<String> proQua = new ArrayList<String>();

                String productName = ProductDao.getProductName(db, c.getInt(c.getColumnIndex(OrderProductTable.PRODUCT_ID)));

                proQua.add(productName);
                proQua.add(c.getString(c.getColumnIndex(OrderProductTable.QUANTITY)));

                productsQuantity.add(proQua);
            } while(c.moveToNext());
        }

        if(c != null)
            c.close();

        return productsQuantity;
    }

    public static String getOrderPharmacyId(SQLiteDatabase db, int orderId) {

        String selectQuery = "SELECT " + OrderTable.PHARMACY_ID + " FROM " + OrderTable.TABLE_NAME + " WHERE " +
                OrderTable.ID + " = " + orderId;

        Cursor c = db.rawQuery(selectQuery, null);

        String pharmacyName = null;

        if(c != null) {
            c.moveToFirst();

            pharmacyName = c.getString(c.getColumnIndex(OrderTable.PHARMACY_ID));

            c.close();
        }

        return pharmacyName;
    }

    public static List<Product> getAllOrderProducts(SQLiteDatabase db, int orderId) {

        String selectQuery = "SELECT " + OrderProductTable.PRODUCT_ID + " FROM " + OrderProductTable.TABLE_NAME + " WHERE " +
                OrderProductTable.ID_ORDER + " = " + orderId;

        Cursor c = db.rawQuery(selectQuery, null);

        List<Product> products = new ArrayList<Product>();

        if(c != null && c.moveToFirst()) {
            do {
                Product product = ProductDao.getProduct(db, c.getInt(c.getColumnIndex(OrderProductTable.PRODUCT_ID)));

                products.add(product);
            } while(c.moveToNext());

            c.close();
        }

        return products;
    }
}