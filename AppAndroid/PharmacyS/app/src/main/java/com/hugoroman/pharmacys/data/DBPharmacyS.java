package com.hugoroman.pharmacys.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hugoroman.pharmacys.data.PharmacySContract.CategoryTable;
import com.hugoroman.pharmacys.data.PharmacySContract.InventoryTable;
import com.hugoroman.pharmacys.data.PharmacySContract.PharmacyTable;
import com.hugoroman.pharmacys.data.PharmacySContract.ProductTable;
import com.hugoroman.pharmacys.model.Inventory;
import com.hugoroman.pharmacys.model.Pharmacy;
import com.hugoroman.pharmacys.model.Product;

import java.util.List;

public class DBPharmacyS extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "PHARMACYS";
    public static final int DATABASE_VERSION = 1;

    // Variables staticas finales para la creación de la base de datos
    private static final String CREATE_PHARMACY = "CREATE TABLE " + PharmacyTable.TABLE_NAME + " (" +
                                                    PharmacyTable.CIF_ID + " varchar(9) NOT NULL PRIMARY KEY," +
                                                    PharmacyTable.NAME + " varchar(120) NOT NULL," +
                                                    PharmacyTable.PHONE_NUMBER + " int(11) NOT NULL," +
                                                    PharmacyTable.DESCRIPTION + " varchar(500) DEFAULT NULL," +
                                                    PharmacyTable.START_SCHEDULE + " int(11) DEFAULT '0'," +
                                                    PharmacyTable.END_SCHEDULE + " int(11) DEFAULT '0'" +
                                                    ");";

    private static final String CREATE_CATEGORY = "CREATE TABLE " + CategoryTable.TABLE_NAME + " (" +
                                                    CategoryTable.ID + " int(11) NOT NULL PRIMARY KEY," +
                                                    CategoryTable.NAME + " varchar(20) NOT NULL" +
                                                    ");";

    private static final String CREATE_PRODUCT = "CREATE TABLE " + ProductTable.TABLE_NAME + " (" +
                                                    ProductTable.ID + "  int(11) NOT NULL PRIMARY KEY," +
                                                    ProductTable.CATEGORY + " int(11) REFERENCES " + CategoryTable.TABLE_NAME + " (" + CategoryTable.ID + ")" + " NOT NULL," +
                                                    ProductTable.NAME + " varchar(160) NOT NULL," +
                                                    ProductTable.DESCRIPTION + " varchar(600) NOT NULL," +
                                                    ProductTable.LABORATORY + " varchar(80) NOT NULL," +
                                                    ProductTable.UNITS + " varchar(5) NOT NULL," +
                                                    ProductTable.EXPIRATION_DATE + " date NULLABLE," +
                                                    ProductTable.SIZE + " int(11) NOT NULL," +
                                                    ProductTable.LOT + " varchar(45) NOT NULL," +
                                                    ProductTable.URL_IMAGE + " varchar(500) DEFAULT 'http://10.211.55.6/img/products/img_no_aviable.png'" +
                                                    ");";

    private static final String CREATE_INVENTORY = "CREATE TABLE " + InventoryTable.TABLE_NAME + "( " +
                                                    InventoryTable.PHARMACY_ID + " varchar(9) REFERENCES " + PharmacyTable.TABLE_NAME + "(" + PharmacyTable.CIF_ID + ")" + " NOT NULL," +
                                                    InventoryTable.PRODUCT_ID + " int(11) REFERENCES " + ProductTable.TABLE_NAME + "(" + ProductTable.ID + ")" + " NOT NULL," +
                                                    InventoryTable.PRICE + " decimal(3,2) NOT NULL DEFAULT '0.00',"+
                                                    InventoryTable.QUANTITY + " int(11) NOT NULL DEFAULT '0'," +
                                                    "PRIMARY KEY("+ InventoryTable.PHARMACY_ID + "," + InventoryTable.PRODUCT_ID +")" +
                                                    ");";

    // Variables estáticas para la inserción inicial de la base de datos - PRUEBAS
    private static final String CREATE_INITIAL_PHARMACY_DATA = "INSERT INTO " + PharmacyTable.TABLE_NAME + " VALUES ('73890889B','farmaciaa',891308843,'',10,22)," +
                                                                " ('89899878H','FARMACIA PEPELUIS',987678392,NULL,10,22)," +
                                                                " ('90889932G','FARMACIA ISABEL',987654321,'farmacia situada en el centro de granada en la calle valencia 14 asesasdsa',11,21)," +
                                                                " ('90890889B','farmaciaa',891308843,'',10,22)," +
                                                                " ('91829193B','FARMACIA CENTRO',957892736,'Farmacia situada en el centro de granada.',10,21)," +
                                                                " ('97989898G','farmaciapepis',999999999,'alkjdlksadlkasndkl',28,38739)," +
                                                                " ('98371937A','FARMACIA SANTA MARIA',987381821,'',10,22)" +
                                                                ";";

    private static final String CREATE_INITIAL_CATEGORY = "INSERT INTO " + CategoryTable.TABLE_NAME + " VALUES (1, 'BABY'), (2, 'BEAUTY');";

    private static final String CREATE_INITIAL_PRODUCT = "INSERT INTO " + ProductTable.TABLE_NAME + " VALUES (1, 1, 'PACIFIER', 'BABY PACIFIER', 'SUAVINEX', 'u', NULL, 1, '12d181BA', NULL)," +
                                                        "(2, 2, 'FACE CREAM', 'FACE CREAM FOR BEAUTY', 'NIVEA', 'g', NULL, 100, '12d181BA', NULL);";

    private static final String CREATE_INITIAL_INVENTORY = "INSERT INTO " + InventoryTable.TABLE_NAME + " VALUES ('73890889B', 1, 3.20, 10), ('73890889B', 2, 5.90, 20);";


    private static final String DELETE_TABLES = "DROP TABLE IF EXISTS " + PharmacyTable.TABLE_NAME + "; " +
                                                "DROP TABLE IF EXISTS " + InventoryTable.TABLE_NAME + "; " +
                                                "DROP TABLE IF EXISTS " + ProductTable.TABLE_NAME + "; " +
                                                "DROP TABLE IF EXISTS " + CategoryTable.TABLE_NAME + ";";

    public DBPharmacyS(Context context) {



        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public Pharmacy getPharmacy(String cif) {

        return PharmacyDao.getPharmacy(this.getReadableDatabase(), cif);
    }

    public List<Pharmacy> getAllPharmacies() {

        return PharmacyDao.getAllPharmacies(this.getReadableDatabase());
    }

    public List<Inventory> getPharmacyInventory(String pharmacyId) {

        return InventoryDao.getPharmacyInventory(this.getReadableDatabase(), pharmacyId);
    }

    public String getProductCategoryName(int idProduct) {

        return ProductDao.getProductCategoryName(this.getReadableDatabase(), idProduct);
    }

    public int getProductCategoryId(int idProduct) {

        return ProductDao.getProductCategoryId(this.getReadableDatabase(), idProduct);
    }

    public List<Product> getAllProductsByCategoryId(int categoryId) {

        return ProductDao.getAllProductsByCategoryId(this.getReadableDatabase(), categoryId);
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
        db.execSQL(CREATE_CATEGORY);
        db.execSQL(CREATE_PRODUCT);
        db.execSQL(CREATE_INVENTORY);

        db.execSQL(CREATE_INITIAL_PHARMACY_DATA);
        db.execSQL(CREATE_INITIAL_CATEGORY);
        db.execSQL(CREATE_INITIAL_PRODUCT);
        db.execSQL(CREATE_INITIAL_INVENTORY);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        onUpgrade(db, oldVersion, newVersion);
    }
}
