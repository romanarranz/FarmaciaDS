package com.hugoroman.pharmacys.data;

import android.content.Context;

import com.hugoroman.pharmacys.model.Basket;
import com.hugoroman.pharmacys.model.Inventory;
import com.hugoroman.pharmacys.model.Pharmacy;
import com.hugoroman.pharmacys.model.Product;
import com.hugoroman.pharmacys.model.Reservation;

import java.util.List;

public class DBConnector {

    private DBPharmacyS pharmacyS;

    public DBConnector(Context context) {

        this.pharmacyS = new DBPharmacyS(context);
    }

    public Pharmacy getPharmacy(String cif) {

        return pharmacyS.getPharmacy(cif);
    }

    public List<Pharmacy> getAllPharmacies() {

        return pharmacyS.getAllPharmacies();
    }

    public List<Inventory> getPharmacyInventory(String pharmacyId) {

        return pharmacyS.getPharmacyInventory(pharmacyId);
    }

    public String getProductCategoryName(int idProduct) {

        return pharmacyS.getProductCategoryName(idProduct);
    }

    public int getProductCategoryId(int idProduct) {

        return pharmacyS.getProductCategoryId(idProduct);
    }

    public List<Product> getAllProductsByCategoryId(int categoryId) {

        return pharmacyS.getAllProductsByCategoryId(categoryId);
    }

    public int getInventoryQuantity(String pharmacyCif, int productId) {

        return pharmacyS.getInventoryQuantity(pharmacyCif, productId);
    }

    public Basket getBasket() {

        return pharmacyS.getBasket();
    }

    public void addToBasket(String pharmacyCif, int productId, int quantity) {

        pharmacyS.addToBasket(pharmacyCif, productId, quantity);
    }

    public void removeFromBasket(String pharmacyCif, int productId) {

        pharmacyS.removeFromBasket(pharmacyCif, productId);
    }

    public Reservation getReservation() {

        return pharmacyS.getReservation();
    }

    public void addToReservation(String pharmacyCif, int productId, int quantity) {

        pharmacyS.addToReservation(pharmacyCif, productId, quantity);
    }

    public void removeFromReservation(String pharmacyCif, int productId) {

        pharmacyS.removeFromReservation(pharmacyCif, productId);
    }

    public Inventory getInventory(String pharmacyCif, int productId) {

        return pharmacyS.getInventory(pharmacyCif, productId);
    }
}
