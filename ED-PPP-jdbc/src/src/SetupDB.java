/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.util.ArrayList;

/**
 *
 * @author jerem
 */
public class SetupDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SetupDB mySetupDB = new SetupDB();

        mySetupDB.clearAllDbs();

        mySetupDB.setupUsers();
        mySetupDB.setupProducts();
        mySetupDB.setupOrders();
    }

    public void clearAllDbs() {
        OrderProductDB orderProductDb = new OrderProductDB();

        // make sure no name conflicts
        try {
            orderProductDb.destroyDBTable();
        } catch (Exception ex) {
        }
        
        OrderDB orderDb = new OrderDB();

        // make sure no name conflicts
        try {
            orderDb.destroyDBTable();
        } catch (Exception ex) {
        }
        
        UserDB userDb = new UserDB();

        // make sure no name conflicts
        try {
            userDb.destroyDBTable();
        } catch (Exception ex) {
        }

        // the database object to access the actual database
        ProductDB productDb = new ProductDB();

        // make sure no name conflicts
        try {
            productDb.destroyDBTable();
        } catch (Exception ex) {
        }
    }

    public void setupUsers() {
        // the database object to access the actual database
        UserDB userDb = new UserDB();

        // create the database table
        System.out.println("Creating user database");
        userDb.createDBTable();

        System.out.println("Add several static records in the database table");

        // prepare data
        User user001 = new User("Adam", "1234567890", "1 John Street, Hawthorn",
                "adam@secure.com.au", "9F86D081884C7D659A2FEAA0C55AD015A3BF4F1B2B0B822CD15D6C15B0F00A08", "ADMIN", true);
        User user002 = new User("Bill", "2345678901", "2 Paul Street, Hawthorn",
                "bill@secure.com.au", "9F86D081884C7D659A2FEAA0C55AD015A3BF4F1B2B0B822CD15D6C15B0F00A08", "ADMIN", true);
        User user003 = new User("Ceci", "3456789012", "3 Mary Street, Hawthorn",
                "ceci@secure.com.au", "9F86D081884C7D659A2FEAA0C55AD015A3BF4F1B2B0B822CD15D6C15B0F00A08", "USER", true);
        User user004 = new User("Dave", "4567890123", "4 Pete Street, Hawthorn",
                "dave@secure.com.au", "9F86D081884C7D659A2FEAA0C55AD015A3BF4F1B2B0B822CD15D6C15B0F00A08", "USER", true);

        // prepare list
        ArrayList<User> userList = new ArrayList<>();
        userList.add(user001);
        userList.add(user002);
        userList.add(user003);
        userList.add(user004);

        // add data to db
        userDb.addRecords(userList);
    }

    public void setupProducts() {
        // the database object to access the actual database
        ProductDB productDb = new ProductDB();

        // create the database table
        System.out.println("Create an empty database table Product");
        productDb.createDBTable();
    }

    public void setupOrders() {
        // the database object to access the actual database
        OrderDB orderDb = new OrderDB();

        // create the database table
        System.out.println("Create an empty database table Orders");
        orderDb.createDBTable();

        // the database object to access the actual database
        OrderProductDB orderProductDb = new OrderProductDB();

        // make sure no name conflicts
        try {
            orderProductDb.destroyDBTable();
        } catch (Exception ex) {
        }

        // create the database table
        System.out.println("Create an empty database table Order Products");
        orderProductDb.createDBTable();
    }
}
