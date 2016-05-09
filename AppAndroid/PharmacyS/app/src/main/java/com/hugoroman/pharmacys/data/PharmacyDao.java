package com.hugoroman.pharmacys.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hugoroman.pharmacys.data.PharmacySContract.PharmacyTable;
import com.hugoroman.pharmacys.model.Pharmacy;

import java.util.ArrayList;
import java.util.List;

// Clase que realiza las operaciones con la base de datos referentes a las farmacias solamente
public final class PharmacyDao {

    public static Pharmacy getPharmacy(SQLiteDatabase db, String pharmacyId) {

        // Coge los datos de la base de datos y los mete en un objeto Pharmacy y lo devuelve
        String selectQuery = "SELECT * FROM " + PharmacyTable.TABLE_NAME + " WHERE "
                + PharmacyTable.ID + " = '" + pharmacyId + "'";

        Cursor c = db.rawQuery(selectQuery, null);

        Pharmacy pharmacy = null;

        if(c != null) {
            c.moveToFirst();

            pharmacy = new Pharmacy(c.getString(c.getColumnIndex(PharmacyTable.ID)),
                                    c.getString(c.getColumnIndex(PharmacyTable.NAME)),
                                    c.getInt(c.getColumnIndex(PharmacyTable.PHONE_NUMBER)),
                                    c.getString(c.getColumnIndex(PharmacyTable.DESCRIPTION)),
                                    c.getInt(c.getColumnIndex(PharmacyTable.START_SCHEDULE)),
                                    c.getInt(c.getColumnIndex(PharmacyTable.END_SCHEDULE)),
                                    c.getDouble(c.getColumnIndex(PharmacyTable.LATITUDE)),
                                    c.getDouble(c.getColumnIndex(PharmacyTable.LONGITUDE)),
                                    c.getString(c.getColumnIndex(PharmacyTable.ADDRESS)),
                                    c.getString(c.getColumnIndex(PharmacyTable.LOGO)));

            c.close();
        }

        return pharmacy;
    }

    public static List<Pharmacy> getAllPharmacies(SQLiteDatabase db) {

        String selectQuery = "SELECT * FROM " + PharmacyTable.TABLE_NAME;

        //Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        List<Pharmacy> pharmacies = new ArrayList<Pharmacy>();

        if(c != null && c.moveToFirst()) {
            do {
                Pharmacy pharmacy = new Pharmacy(c.getString(c.getColumnIndex(PharmacyTable.ID)),
                        c.getString(c.getColumnIndex(PharmacyTable.NAME)),
                        c.getInt(c.getColumnIndex(PharmacyTable.PHONE_NUMBER)),
                        c.getString(c.getColumnIndex(PharmacyTable.DESCRIPTION)),
                        c.getInt(c.getColumnIndex(PharmacyTable.START_SCHEDULE)),
                        c.getInt(c.getColumnIndex(PharmacyTable.END_SCHEDULE)),
                        c.getDouble(c.getColumnIndex(PharmacyTable.LATITUDE)),
                        c.getDouble(c.getColumnIndex(PharmacyTable.LONGITUDE)),
                        c.getString(c.getColumnIndex(PharmacyTable.ADDRESS)),
                        c.getString(c.getColumnIndex(PharmacyTable.LOGO)));

                pharmacies.add(pharmacy);
            } while(c.moveToNext());
        }

        if(c != null)
            c.close();

        return pharmacies;
    }

    public static String getPharmacyName(SQLiteDatabase db, String pharmacyId) {

        String selectQuery = "SELECT " + PharmacyTable.NAME + " FROM " + PharmacyTable.TABLE_NAME + " WHERE " +
                PharmacyTable.ID + " = '" + pharmacyId + "'";

        Cursor c = db.rawQuery(selectQuery, null);

        String pharmacyName = null;

        if(c != null) {
            c.moveToFirst();

            pharmacyName = c.getString(c.getColumnIndex(PharmacyTable.NAME));

            c.close();
        }

        return pharmacyName;
    }
}
