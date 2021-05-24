/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author jerem
 */
public class Order implements Serializable {

    private final Date date;

    public Order(int orderId, Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
}
