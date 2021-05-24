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
        UserDB userDb = new UserDB();

        // make sure no name conflicts
        try {
            userDb.destroyDBTable();
        } catch (Exception ex) {
        }
        
        
        OrderProductDB orderProductDb = new OrderProductDB();

        // make sure no name conflicts
        try {
            orderProductDb.destroyDBTable();
        } catch (Exception ex) {
        }
        
        // the database object to access the actual database
        ProductDB productDb = new ProductDB();

        // make sure no name conflicts
        try {
            productDb.destroyDBTable();
        } catch (Exception ex) {
        }
        
        OrderDB orderDb = new OrderDB();

        // make sure no name conflicts
        try {
            orderDb.destroyDBTable();
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
                "adam@secure.com.au", "ee79976c9380d5e337fc1c095ece8c8f22f91f306ceeb161fa51fecede2c4ba1", "ED-APP-ADMIN", true);
        User user002 = new User("Bill", "2345678901", "2 Paul Street, Hawthorn",
                "bill@secure.com.au", "33A7D3DA476A32AC237B3F603A1BE62FAD00299E0D4B5A8DB8D913104EDEC629", "ED-APP-ADMIN", true);
        User user003 = new User("Ceci", "3456789012", "3 Mary Street, Hawthorn",
                "ceci@secure.com.au", "AFB47E00531153E93808589E43D02C11F6398C5BC877F7924CEBCA8211C8DD18", "ED-APP-USERS", true);
        User user004 = new User("Dave", "4567890123", "4 Pete Street, Hawthorn",
                "dave@secure.com.au", "B3C4B40750A97212E8981E4AC494D1EC77053F1EAF4E0934C276B74FC4F87C48", "ED-APP-USERS", true);

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
