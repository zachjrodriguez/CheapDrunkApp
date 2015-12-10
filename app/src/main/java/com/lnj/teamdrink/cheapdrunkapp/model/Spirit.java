package com.lnj.teamdrink.cheapdrunkapp.model;

/**
 * This is a Spirit object, or mixed drink
 * beverage. It is a sub-class of Beverage.
 *
 * Created by Zach on 12/7/2015.
 */
public class Spirit extends Beverage {

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
    public Spirit(int id, String name, String type, double content, double price) {
        super(id, name, type, content, price);
    }
}
