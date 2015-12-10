package com.lnj.teamdrink.cheapdrunkapp.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lnj.teamdrink.cheapdrunkapp.model.Beverage;

import java.util.ArrayList;
import java.util.List;

/**
 * The DatabaseHelper class allows for access to the SQLite database
 * available to the application.
 *
 * Created by Zach on 12/7/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    /** Variable to keep track of the name of the database */
    public static final String DBNAME = "sample.sqlite";
    /** Variable to keep track of the location of the database */
    public static final String DBLOCATION = "/data/data/com.lnj.teamdrink.cheapdrunkapp/databases/";
    /** Context variable */
    private Context nContext;
    /** SQLiteDatabase object */
    private SQLiteDatabase nDatabase;

    /**
     * Constructor of the class which reads in a context object.
     *
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, 2);
        this.nContext = context;
    }

    /**
     * A method used to to open a connection to the
     * database.
     *
     */
    public void openDatabase() {
        String dbPath = nContext.getDatabasePath(DBNAME).getPath();
        if(nDatabase != null && nDatabase.isOpen()) {
            return;
        }

        nDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);

    }

    /**
     * A method used to close a connection to the
     * database.
     *
     */
    public void closeDatabase() {
        if(nDatabase != null) {
            nDatabase.close();
        }
    }

    /**
     * A method to return a list of beverages from
     * the database.
     *
     * @return List of beverages
     */
    public List getListDrinks() {
        Beverage beverage;
        List<Beverage> drinkList = new ArrayList<>();
        openDatabase();
        Cursor cursor = nDatabase.rawQuery("SELECT * FROM PRODUCT WHERE TYPE = 'S'", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            beverage = new Beverage(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getDouble(3), cursor.getDouble(4));
            drinkList.add(beverage);
            cursor.moveToNext();
        }

        cursor.close();
        closeDatabase();
        return drinkList;
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p/>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back1.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
