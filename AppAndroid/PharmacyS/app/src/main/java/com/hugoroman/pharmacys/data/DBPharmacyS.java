package com.hugoroman.pharmacys.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hugoroman.pharmacys.data.PharmacySContract.BasketTable;
import com.hugoroman.pharmacys.data.PharmacySContract.CategoryTable;
import com.hugoroman.pharmacys.data.PharmacySContract.InventoryTable;
import com.hugoroman.pharmacys.data.PharmacySContract.PharmacyTable;
import com.hugoroman.pharmacys.data.PharmacySContract.ProductTable;
import com.hugoroman.pharmacys.data.PharmacySContract.ReservationTable;
import com.hugoroman.pharmacys.model.Basket;
import com.hugoroman.pharmacys.model.Inventory;
import com.hugoroman.pharmacys.model.Pharmacy;
import com.hugoroman.pharmacys.model.Product;
import com.hugoroman.pharmacys.model.Reservation;

import java.util.GregorianCalendar;
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
                                                    PharmacyTable.END_SCHEDULE + " int(11) DEFAULT '0'," +
                                                    PharmacyTable.LATITUDE + " DOUBLE NOT NULL DEFAULT '0'," +
                                                    PharmacyTable.LONGITUDE + " DOUBLE NOT NULL DEFAULT '0'," +
                                                    PharmacyTable.ADDRESS + " varchar(200) NOT NULL DEFAULT ''," +
                                                    PharmacyTable.LOGO + " varchar(200) DEFAULT ''" +
                                                    ");";

    private static final String CREATE_CATEGORY = "CREATE TABLE " + CategoryTable.TABLE_NAME + " (" +
                                                    CategoryTable.ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                                                    CategoryTable.NAME + " varchar(20) NOT NULL," +
                                                    CategoryTable.IMG + " INTEGER DEFAULT 0" +
                                                    ");";

    private static final String CREATE_PRODUCT = "CREATE TABLE " + ProductTable.TABLE_NAME + " (" +
                                                    ProductTable.ID + "  INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
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

    public static final String CREATE_BASKET = "CREATE TABLE " + BasketTable.TABLE_NAME + " ( " +
                                                BasketTable.PHARMACY_ID + " varchar(9) REFERENCES " + PharmacyTable.TABLE_NAME + "(" + PharmacyTable.CIF_ID + ")" + " NOT NULL," +
                                                BasketTable.PRODUCT_ID + " int(11) REFERENCES " + ProductTable.TABLE_NAME + "(" + ProductTable.ID + ")" + " NOT NULL," +
                                                BasketTable.QUANTITY + " int(5) NOT NULL," +
                                                "PRIMARY KEY("+ BasketTable.PHARMACY_ID + "," + BasketTable.PRODUCT_ID +")" +
                                                ");";

    public static final String CREATE_RESERVATION = "CREATE TABLE " + ReservationTable.TABLE_NAME + " ( " +
                                                    ReservationTable.PHARMACY_ID + " varchar(9) REFERENCES " + PharmacyTable.TABLE_NAME + "(" + PharmacyTable.CIF_ID + ")" + " NOT NULL," +
                                                    ReservationTable.PRODUCT_ID + " int(11) REFERENCES " + ProductTable.TABLE_NAME + "(" + ProductTable.ID + ")" + " NOT NULL," +
                                                    ReservationTable.QUANTITY + " int(5) NOT NULL," +
                                                    "PRIMARY KEY("+ ReservationTable.PHARMACY_ID + "," + ReservationTable.PRODUCT_ID +")" +
                                                    ");";

    // Variables estáticas para la inserción inicial de la base de datos -> PRUEBAS
    private static final String CREATE_INITIAL_PHARMACY_DATA = "INSERT INTO " + PharmacyTable.TABLE_NAME + " VALUES ('73890889B','farmaciaa',891308843,'',10,22, 37.1356, -3.5583, 'DIRECCIÓN 1', 'http://farmaciamariadelcarmen.es/wp-content/uploads/2016/04/farmacia_cover_L.jpg')," +
                                                                " ('89899878H','FARMACIA PEPELUIS',987678392,NULL,10,22, 37.1946133, 37.1946133, 'DIRECCIÓN 2', 'http://www.farmaciamerino.com/files/0001/merino8835058v683902y5927p12936/web.system/assets/contents/articles/farmacias_de_guardia.jpg')," +
                                                                " ('90889932G','FARMACIA ISABEL',987654321,'farmacia situada en el centro de granada en la calle valencia 14 asesasdsa',11,21, 0, 0, 'DIRECCIÓN', 'http://farmaciamariadelcarmen.es/wp-content/uploads/2016/04/farmacia_cover_L.jpg')," +
                                                                " ('90890889B','farmaciaa',891308843,'',10,22, 0, 0, 'DIRECCIÓN', 'http://farmaciamariadelcarmen.es/wp-content/uploads/2016/04/farmacia_cover_L.jpg')," +
                                                                " ('91829193B','FARMACIA CENTRO',957892736,'Farmacia situada en el centro de granada.',10,21, 0, 0, 'DIRECCIÓN', 'http://farmaciamariadelcarmen.es/wp-content/uploads/2016/04/farmacia_cover_L.jpg')," +
                                                                " ('97989898G','farmaciapepis',999999999,'alkjdlksadlkasndkl',28,38739, 0, 0, 'DIRECCIÓN', 'http://farmaciamariadelcarmen.es/wp-content/uploads/2016/04/farmacia_cover_L.jpg')," +
                                                                " ('98371937A','FARMACIA SANTA MARIA',987381821,'',10,22, 0, 0, 'DIRECCIÓN', 'http://farmaciamariadelcarmen.es/wp-content/uploads/2016/04/farmacia_cover_L.jpg')" +
                                                                ";";

    private static final String CREATE_INITIAL_CATEGORY = "INSERT INTO " + CategoryTable.TABLE_NAME + " VALUES (1, 'BABY', 2130837579), (NULL, 'COSMETIC', 2130837623);";

    private static final String CREATE_INITIAL_PRODUCT = "INSERT INTO " + ProductTable.TABLE_NAME + " VALUES (1, 1, 'PACIFIER', 'BABY PACIFIER', 'SUAVINEX', 'u', NULL, 1, '12d181BA', NULL)," +
                                                        "(NULL, 2, 'FACE CREAM', 'FACE CREAM FOR BEAUTY', 'NIVEA', 'g', " + new GregorianCalendar(2017, 0, 15).getTimeInMillis() +" , 100, '12d181BA', NULL);";

    private static final String CREATE_INITIAL_INVENTORY = "INSERT INTO " + InventoryTable.TABLE_NAME + " VALUES ('73890889B', 1, 3.20, 10), ('73890889B', 2, 5.90, 0);";

    private static final String CREATE_INITIAL_BASKET = "INSERT INTO " + BasketTable.TABLE_NAME + " VALUES ('73890889B', 2, 5);";

    private static final String CREATE_INITIAL_RESERVATION = "INSERT INTO " + ReservationTable.TABLE_NAME + " VALUES ('73890889B', 1, 2);";


    private static final String DELETE_TABLES = "DROP TABLE IF EXISTS " + BasketTable.TABLE_NAME + ";" +
                                                "DROP TABLE IF EXISTS " + InventoryTable.TABLE_NAME + "; " +
                                                "DROP TABLE IF EXISTS " + PharmacyTable.TABLE_NAME + "; " +
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

    public int getProductCategoryPhoto(int idProduct) {

        return ProductDao.getProductCategoryPhoto(this.getReadableDatabase(), idProduct);
    }

    public int getProductCategoryId(int idProduct) {

        return ProductDao.getProductCategoryId(this.getReadableDatabase(), idProduct);
    }

    public List<Product> getAllProductsByCategoryId(int categoryId) {

        return ProductDao.getAllProductsByCategoryId(this.getReadableDatabase(), categoryId);
    }

    public int getInventoryQuantity(String pharmacyCif, int productId) {

        return InventoryDao.getInventoryQuantity(this.getReadableDatabase(), pharmacyCif, productId);
    }

    public Basket getBasket() {

        return BasketDao.getBasket(this.getReadableDatabase());
    }

    public void addToBasket(String pharmacyCif, int productId, int quantity) {

        BasketDao.addToBasket(this.getWritableDatabase(), pharmacyCif, productId, quantity);
    }

    public void removeFromBasket(String pharmacyCif, int productId) {

        BasketDao.removeFromBasket(this.getWritableDatabase(), pharmacyCif, productId);
    }

    public Reservation getReservation() {

        return ReservationDao.getReservation(this.getReadableDatabase());
    }

    public void addToReservation(String pharmacyCif, int productId, int quantity) {

        ReservationDao.addToReservation(this.getWritableDatabase(), pharmacyCif, productId, quantity);
    }

    public void removeFromReservation(String pharmacyCif, int productId) {

        ReservationDao.removeFromReservation(this.getWritableDatabase(), pharmacyCif, productId);
    }

    public Inventory getInventory(String pharmacyCif, int productId) {

        return InventoryDao.getInventory(this.getReadableDatabase(), pharmacyCif, productId);
    }

    public String getCategoryName(int categoryId) {

        return ProductDao.getCategoryName(this.getReadableDatabase(), categoryId);
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
        db.execSQL(CREATE_BASKET);
        db.execSQL(CREATE_RESERVATION);

        db.execSQL(CREATE_INITIAL_PHARMACY_DATA);
        db.execSQL(CREATE_INITIAL_CATEGORY);
        db.execSQL(CREATE_INITIAL_PRODUCT);
        db.execSQL(CREATE_INITIAL_INVENTORY);
        db.execSQL(CREATE_INITIAL_BASKET);
        db.execSQL(CREATE_INITIAL_RESERVATION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
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
