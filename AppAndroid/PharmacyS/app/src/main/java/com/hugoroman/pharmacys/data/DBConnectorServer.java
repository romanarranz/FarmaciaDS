package com.hugoroman.pharmacys.data;

/**
 * Created by hugomaldonado on 4/5/16.
 */
public class DBConnectorServer {
    private static DBConnectorServer ourInstance = new DBConnectorServer();

    private DBConnectorServer() {

    }

    public static DBConnectorServer getInstance() {

        return ourInstance;
    }
}
