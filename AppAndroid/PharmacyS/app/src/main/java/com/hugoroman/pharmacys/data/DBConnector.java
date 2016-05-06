package com.hugoroman.pharmacys.data;

import android.content.Context;

import com.hugoroman.pharmacys.model.Inventory;
import com.hugoroman.pharmacys.model.Pharmacy;

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
}
