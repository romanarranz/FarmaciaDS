package com.hugoroman.pharmacys.data;

import android.content.Context;

import com.hugoroman.pharmacys.model.Basket;
import com.hugoroman.pharmacys.model.Inventory;
import com.hugoroman.pharmacys.model.Order;
import com.hugoroman.pharmacys.model.Pharmacy;
import com.hugoroman.pharmacys.model.Product;
import com.hugoroman.pharmacys.model.Reservation;
import com.hugoroman.pharmacys.model.User;

import java.util.List;

public class DBConnector {

    private DBPharmacyS pharmacyS;

    public DBConnector(Context context) {

        this.pharmacyS = new DBPharmacyS(context);
    }

    public Pharmacy getPharmacy(String pharmacyId) {

        return pharmacyS.getPharmacy(pharmacyId);
    }

    public List<Pharmacy> getAllPharmacies() {

        return pharmacyS.getAllPharmacies();
    }

    public String getPharmacyName(String pharmacyId) {

        return pharmacyS.getPharmacyName(pharmacyId);
    }

    public List<Inventory> getPharmacyInventory(String pharmacyId) {

        return pharmacyS.getPharmacyInventory(pharmacyId);
    }

    public String getProductCategoryName(int idProduct) {

        return pharmacyS.getProductCategoryName(idProduct);
    }

    public int getProductCategoryPhoto(int idProduct) {

        return pharmacyS.getProductCategoryPhoto(idProduct);
    }

    public int getProductCategoryId(int idProduct) {

        return pharmacyS.getProductCategoryId(idProduct);
    }

    public List<Product> getAllProductsByCategoryId(int categoryId) {

        return pharmacyS.getAllProductsByCategoryId(categoryId);
    }

    public int getInventoryQuantity(String pharmacyId, int productId) {

        return pharmacyS.getInventoryQuantity(pharmacyId, productId);
    }

    public Basket getBasket() {

        return pharmacyS.getBasket();
    }

    public void addToBasket(String pharmacyId, int productId, int quantity) {

        pharmacyS.addToBasket(pharmacyId, productId, quantity);
    }

    public void removeFromBasket(String pharmacyId, int productId) {

        pharmacyS.removeFromBasket(pharmacyId, productId);
    }

    public Reservation getReservation() {

        return pharmacyS.getReservation();
    }

    public void addToReservation(String pharmacyId, int productId, int quantity) {

        pharmacyS.addToReservation(pharmacyId, productId, quantity);
    }

    public void removeFromReservation(String pharmacyId, int productId) {

        pharmacyS.removeFromReservation(pharmacyId, productId);
    }

    public Inventory getInventory(String pharmacyId, int productId) {

        return pharmacyS.getInventory(pharmacyId, productId);
    }

    public String getCategoryName(int categoryId) {

        return pharmacyS.getCategoryName(categoryId);
    }

    public User getUser(String userEmail) {

        return pharmacyS.getUser(userEmail);
    }

    public List<Order> getAllOrders(String userEmail) {

        return pharmacyS.getAllOrders(userEmail);
    }

    public List<List<String>> getOrderInfo(int orderId) {

        return pharmacyS.getOrderInfo(orderId);
    }

    public String getOrderPharmacyId(int orderId) {

        return pharmacyS.getOrderPharmacyId(orderId);
    }

    public List<Product> getAllOrderProducts(int orderId) {

        return pharmacyS.getAllOrderProducts(orderId);
    }

    public float getProductPrice(String pharmacyId, int productId) {

        return pharmacyS.getProductPrice(pharmacyId, productId);
    }

    public Basket getPharmacyBasket(String pharmacyId) {

        return pharmacyS.getPharmacyBasket(pharmacyId);
    }

    public void addToOrder(String userEmail, String pharmacyId, long date, float price, List<Product> products, List<Integer> quantities) {

        pharmacyS.addToOrder(userEmail, pharmacyId, date, price, products, quantities);
    }
}