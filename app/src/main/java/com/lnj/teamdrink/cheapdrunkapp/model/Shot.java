package com.lnj.teamdrink.cheapdrunkapp.model;

/**
 * This is a Shot object, a sub-class of the
 * Beverage class. It could be considered a
 * sub-class of the Spirit class; however,
 *  will be listed as seperate for the Beta
 *  app.
 *
 * Created by Zach on 12/7/2015.
 */
public class Shot extends Beverage {

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
    public Shot(int id, String name, String type, double content, double price) {
        super(id, name, type, content, price);
    }
}
