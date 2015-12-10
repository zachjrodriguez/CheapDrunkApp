package com.lnj.teamdrink.cheapdrunkapp.model;

/**
 * This is a Beer object. It is a sub-class of
 * Beverage.
 *
 * Created by Zach on 12/7/2015.
 */
public class Beer extends Beverage {

    /**
     * The primary constructor of the Beverage class, it requires
     * the passing in of ALL variables.
     *
     * @param id      : int
     * @param name    : String
     * @param type    : String
     * @param content : double
     * @param price   : double
     */
    public Beer(int id, String name, String type, double content, double price) {
        super(id, name, type, content, price);
    }
}
