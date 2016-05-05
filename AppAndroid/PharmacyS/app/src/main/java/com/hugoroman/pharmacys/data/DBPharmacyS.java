package com.hugoroman.pharmacys.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

import com.hugoroman.pharmacys.data.PharmacySContract.PharmacyTable;
import com.hugoroman.pharmacys.model.Pharmacy;

public class DBPharmacyS extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "PHARMACYS";
    public static final int DATABASE_VERSION = 1;

    // Variables staticas finales para la creación de la base de datos
    private static final String CREATE_PHARMACY = "CREATE TABLE " + PharmacyTable.TABLE_NAME +" (" +
                                                    PharmacyTable.CIF_ID + " varchar(9) NOT NULL PRIMARY KEY," +
                                                    PharmacyTable.NAME + " varchar(120) NOT NULL," +
                                                    PharmacyTable.PHONE_NUMBER + " int(11) NOT NULL," +
                                                    PharmacyTable.DESCRIPTION + " varchar(500) DEFAULT NULL," +
                                                    PharmacyTable.START_SCHEDULE + " int(11) DEFAULT '0'," +
                                                    PharmacyTable.END_SCHEDULE + " int(11) DEFAULT '0'" +
                                                    ")";
    private static final String CREATE_INITIAL_PHARMACY_DATA = "INSERT INTO " + PharmacyTable.TABLE_NAME + " VALUES ('73890889B','farmaciaa',891308843,'',10,22)," +
                                                                " ('89899878H','FARMACIA PEPELUIS',987678392,NULL,10,22)," +
                                                                " ('90889932G','FARMACIA ISABEL',987654321,'farmacia situada en el centro de granada en la calle valencia 14 asesasdsa',11,21)," +
                                                                " ('90890889B','farmaciaa',891308843,'',10,22)," +
                                                                " ('91829193B','FARMACIA CENTRO',957892736,'Farmacia situada en el centro de granada.',10,21)," +
                                                                " ('97989898G','farmaciapepis',999999999,'alkjdlksadlkasndkl',28,38739)," +
                                                                " ('98371937A','FARMACIA SANTA MARIA',987381821,'',10,22);";

    public DBPharmacyS(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public Pharmacy getPharmacy(String cif) {

        return PharmacyDao.getPharmacy(this.getReadableDatabase(), cif);
    }

    public List<Pharmacy> getAllPharmacies() {

        return PharmacyDao.getAllPharmacies(this.getReadableDatabase());
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);

        // Habilitar el uso de llaves externas en la base de datos. Asegurar consistencia de datos
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_PHARMACY);
        db.execSQL(CREATE_INITIAL_PHARMACY_DATA);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        onUpgrade(db, oldVersion, newVersion);
    }
}
