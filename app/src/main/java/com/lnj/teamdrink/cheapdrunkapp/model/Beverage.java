package com.lnj.teamdrink.cheapdrunkapp.model;

/**
 * This class represents a beverage object.
 * It acts as a model for the data being read
 * out of the localized database.
 *
 * Created by Zach on 12/7/2015.
 */
public class Beverage {

    /** The id of the beverage, as it is in the database. */
    private int id;
    /** The name of the beverage. */
    private String name;
    /** The type of the beverage. */
    private String type;
    /** The alcohol content of the beverage. */
    private double content;
    /** The price of the beverage. */
    private double price;

    /**
     * The primary constructor of the Beverage class, it requires
     * the passing in of ALL variables.
     *
     * @param id : int
     * @param name : String
     * @param type : String
     * @param content : double
     * @param price : double
     */
    public Beverage(int id, String name, String type, double content, double price) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.content = content;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getContent() {
        return content;
    }

    public double getPrice() {
        return price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setContent(double content) {
        this.content = content;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
