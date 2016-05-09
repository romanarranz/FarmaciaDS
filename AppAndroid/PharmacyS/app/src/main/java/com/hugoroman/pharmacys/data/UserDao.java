package com.hugoroman.pharmacys.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hugoroman.pharmacys.data.PharmacySContract.UserTable;

public final class UserDao {

    public static String getUserName(SQLiteDatabase db, String userEmail) {

        String selectQuery = "SELECT " + UserTable.NAME + " FROM " + UserTable.TABLE_NAME + " WHERE " +
                UserTable.EMAIL + " = '" + userEmail + "'";

        Cursor c = db.rawQuery(selectQuery, null);

        if(c != null)
            c.moveToFirst();

        String userName = c.getString(c.getColumnIndex(UserTable.NAME));

        c.close();

        return userName;
    }
}
