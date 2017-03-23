package com.mkdika.swingworker.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Maikel Chandika <mkdika@gmail.com>
 *
 * This class is use to help out generate sample data for testing the Swing
 * Worker. Edit the instance variable TOTAL_GENERATED_DATA to change the total
 * of generated records.
 */
public class InsertSampleData {

    private static final int TOTAL_GENERATED_DATA = 100000;

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName(DbConn.JDBC_CLASS);
        Connection conn = DriverManager.getConnection(DbConn.JDBC_URL,
                DbConn.JDBC_USERNAME,
                DbConn.JDBC_PASSWORD);
        if (conn != null) {
            System.out.println("Connected to DB!\n");

            String insertSql = "INSERT INTO testdata "
                    + "(string1,string2,integer1,integer2,double1,double2,boolean1)"
                    + "VALUES (?,?,?,?,?,?,TRUE);";
            PreparedStatement pstatement = conn.prepareStatement(insertSql);

            for (int i = 0; i < TOTAL_GENERATED_DATA; i++) {
                pstatement.setString(1, "S-1-" + i);
                pstatement.setString(2, "S-2-" + i);
                pstatement.setInt(3, i);
                pstatement.setInt(4, i);
                pstatement.setDouble(5, (double) (i + 10));
                pstatement.setDouble(6, (double) (i + 10));
                pstatement.executeUpdate();
                System.out.println("Record-" + (i+1) + " Inserted.");
            }
            System.out.println("Done!");
            pstatement.close();
            conn.close();
        }
    }
}
