package com.hugoroman.pharmacys.data;

import android.content.Context;

import java.util.List;

import com.hugoroman.pharmacys.model.Pharmacy;

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
}
