/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.Serializable;

/**
 *
 * @author jerem
 */
public class Product implements Serializable {

    private final String name;
    private final String description;
    private final float price;
    private final String image;
    private final String category;
    private final boolean active;

    public String getCategory() {
        return category;
    }
    

    public Product(int productId, String name, String description, float price, String image, String category, boolean active) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.active = active;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public boolean isActive() {
        return active;
    }
}
