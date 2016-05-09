package com.hugoroman.pharmacys.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hugoroman.pharmacys.data.PharmacySContract.UserTable;
import com.hugoroman.pharmacys.model.User;

public final class UserDao {

    public static User getUser(SQLiteDatabase db, String userEmail) {

        String selectQuery = "SELECT * FROM " + UserTable.TABLE_NAME + " WHERE " +
                UserTable.EMAIL + " = '" + userEmail + "'";

        Cursor c = db.rawQuery(selectQuery, null);

        User user = null;

        if(c != null) {
            c.moveToFirst();

            user = new User(c.getString(c.getColumnIndex(UserTable.EMAIL)),
                    c.getString(c.getColumnIndex(UserTable.NAME)),
                    c.getString(c.getColumnIndex(UserTable.SURNAME)));

            c.close();
        }

        return user;
    }
}
