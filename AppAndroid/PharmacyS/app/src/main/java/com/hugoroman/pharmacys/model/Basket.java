package com.hugoroman.pharmacys.model;

import java.util.List;

public class Basket {

    private List<Product> products;

    public Basket(List<Product> products) {

        this.products = products;
    }

    public List<Product> getProducts() {
        return products;
    }
}
