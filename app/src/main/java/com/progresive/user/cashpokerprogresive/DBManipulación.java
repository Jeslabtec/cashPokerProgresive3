package com.progresive.user.cashpokerprogresive;

import java.sql.*;

/**
 * Created by JuanEsteban on 15/05/2016.
 */
public class DBManipulación extends Thread {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/";

    //  Database credentials
    static final String USER = "root";
    static final String PASS = "JE\"\"1152189289";

    Connection coneccion=null;
    Statement stmt = null;


    public DBManipulación() throws Exception {
            conectar(coneccion,stmt);
    }

    private void conectar(Connection coneccion,Statement stmt) throws Exception {
        Class.forName(JDBC_DRIVER);
        coneccion = DriverManager.getConnection(DB_URL, USER, PASS);
    }


    @Override
    public void run()
    {

    }


}
