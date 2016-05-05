package com.hugoroman.pharmacys.data;

import android.provider.BaseColumns;

/**
 * Clase para la creación de la base de datos. Esquema de la base de datos
 */

public final class PharmacySContract {

    public PharmacySContract() {}

    // Clase para la tabla de farmacias con los campos de dicha
    public static abstract class PharmacyTable implements BaseColumns {

        public static final String TABLE_NAME = "PHARMACY";
        public static final String CIF_ID = "CIF";
        public static final String NAME = "NAME";
        public static final String PHONE_NUMBER = "PHONE_NUMBER";
        public static final String DESCRIPTION = "DESCRIPTION";
        public static final String START_SCHEDULE = "START_SCHEDULE";
        public static final String END_SCHEDULE = "END_SCHEDULE";
    }
}
