package com.hugoroman.pharmacys.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hugoroman.pharmacys.data.PharmacySContract.PharmacyTable;
import com.hugoroman.pharmacys.model.Pharmacy;

import java.util.ArrayList;
import java.util.List;

// Clase que realiza las operaciones con la base de datos referentes a las farmacias solamente
public final class PharmacyDao {

    public static Pharmacy getPharmacy(SQLiteDatabase db, String cif) {

        // Coge los datos de la base de datos y los mete en un objeto Pharmacy y lo devuelve
        String selectQuery = "SELECT * FROM " + PharmacyTable.TABLE_NAME + " WHERE "
                + PharmacyTable._ID + " = " + cif;

        //Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if(c != null)
            c.moveToFirst();

        Pharmacy pharmacy = new Pharmacy(c.getString(c.getColumnIndex(PharmacyTable.CIF_ID)),
                                        c.getString(c.getColumnIndex(PharmacyTable.NAME)),
                                        c.getInt(c.getColumnIndex(PharmacyTable.PHONE_NUMBER)),
                                        c.getString(c.getColumnIndex(PharmacyTable.DESCRIPTION)),
                                        c.getInt(c.getColumnIndex(PharmacyTable.START_SCHEDULE)),
                                        c.getInt(c.getColumnIndex(PharmacyTable.END_SCHEDULE)));

        return pharmacy;
    }

    public static List<Pharmacy> getAllPharmacies(SQLiteDatabase db) {

        String selectQuery = "SELECT * FROM " + PharmacyTable.TABLE_NAME;

        //Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        List<Pharmacy> pharmacies = new ArrayList<Pharmacy>();

        if(c != null && c.moveToFirst()) {
            do {
                Pharmacy pharmacy = new Pharmacy(c.getString(c.getColumnIndex(PharmacyTable.CIF_ID)),
                        c.getString(c.getColumnIndex(PharmacyTable.NAME)),
                        c.getInt(c.getColumnIndex(PharmacyTable.PHONE_NUMBER)),
                        c.getString(c.getColumnIndex(PharmacyTable.DESCRIPTION)),
                        c.getInt(c.getColumnIndex(PharmacyTable.START_SCHEDULE)),
                        c.getInt(c.getColumnIndex(PharmacyTable.END_SCHEDULE)));

                pharmacies.add(pharmacy);
            } while(c.moveToNext());
        }

        return pharmacies;
    }
}
