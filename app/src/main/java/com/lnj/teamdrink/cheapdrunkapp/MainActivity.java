package com.lnj.teamdrink.cheapdrunkapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lnj.teamdrink.cheapdrunkapp.database.DatabaseHelper;
import com.lnj.teamdrink.cheapdrunkapp.model.Beverage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /* Reference to the Budget Text Enter */
    private EditText budgetEnterView;

    /* Reference to the Results Text Area */
    private TextView resultsView;

    /* Reference to the Restaurant Spinner */
    private Spinner restaurantSelectorSpinner;

    /* Reference to the Drink Type Spinner */
    private Spinner drinkTypeSpinner;

    /* Variables to be used throughout the app to grab essential values */
    private String budgetText;
    private String selectedDrink;

    /* Reference to the Calculate & Reset Buttons */
    private Button cheapButton;
    private Button drunkButton;
    private Button resetButton;

    /* References to the list object, and the Database Helper */
    private List<Beverage> nDrinkList;
    private DatabaseHelper nDBHelper;

    /**
     * Main method of the MainActivity. It will instantiate the
     * main activity, and all other necessary objects to
     * display the app properly.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /** Initialize Variables to their fields */
        budgetEnterView = (EditText) findViewById(R.id.enterBudget);

        budgetEnterView.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                // we assign "theText" a value here
                budgetText = s.toString();
            }
        });

        addItemsToDrinkTypeSpinner();
        addItemsToRestaurantSelector();

        addListenerToDrinkTypeSpinner();
        addListenerToRestaurantSelectorSpinner();

        resultsView = (TextView) findViewById(R.id.results);
        cheapButton = (Button) findViewById(R.id.cheapButton);
        drunkButton = (Button) findViewById(R.id.drunkButton);
        resetButton = (Button) findViewById(R.id.resetButton);

        cheapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calculateCheapest();
            }
        });

        drunkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calculateDrunkest();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultsView.setText("");
                budgetEnterView.setText("");
            }
        });


        /** Database Testing */
        nDBHelper = new DatabaseHelper(this);

        File database = getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if(false == database.exists()) {
            nDBHelper.getReadableDatabase();
            if(copyDatabase(this)) {
                Toast.makeText(this, "Copy db success", Toast.LENGTH_SHORT).show();

            }
            else {
                Toast.makeText(this, "Copy data error", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        String type = "'SH'";
        nDrinkList = nDBHelper.getListDrinksByType(type);
        Integer size = nDrinkList.size();
        Log.w("MainActivity", size.toString());
        Toast.makeText(this, size.toString(), Toast.LENGTH_SHORT).show();

        /** Actual to follow here*/

    }

    /**
     * Method to calculate the cheapest drink purchase
     * a user can do.
     *
     */
    public void calculateCheapest() {

    }

    /**
     * Method that calculates the yields the drinks
     * with the largest possible alcohol content volume
     *
     */
    public void calculateDrunkest() {

    }

    /**
     * This method will add items to the drink spinner
     * and will build the layout.
     *
     */
    public void addItemsToDrinkTypeSpinner(){
        drinkTypeSpinner = (Spinner) findViewById(R.id.drinkType);
        ArrayAdapter<CharSequence> drinkTypeSpinnerAdapter =
                ArrayAdapter.createFromResource(this, R.array.cdDrinks,
                        android.R.layout.simple_spinner_item);

        drinkTypeSpinnerAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        drinkTypeSpinner.setAdapter(drinkTypeSpinnerAdapter);
    }

    /**
     * This method detects changes in the spinner, and saves the
     * value of the selected drink.
     *
     */
    public void addListenerToDrinkTypeSpinner(){
        drinkTypeSpinner = (Spinner) findViewById(R.id.drinkType);

        drinkTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelected = parent.getItemAtPosition(position).toString();
                selectedDrink = itemSelected;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Todo consider this later
            }
        });

    }

    /**
     * This method will add items to the restaurant spinner
     * and will build the layout.
     *
     */
    public void addItemsToRestaurantSelector(){
        restaurantSelectorSpinner = (Spinner) findViewById(R.id.restaurantSelector);
        ArrayAdapter<CharSequence> restaurantSelectorSpinnerAdapter =
                ArrayAdapter.createFromResource(this, R.array.cdRestaurants,
                        android.R.layout.simple_spinner_item);

        restaurantSelectorSpinnerAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        restaurantSelectorSpinner.setAdapter(restaurantSelectorSpinnerAdapter);
    }

    /**
     * This method detects changes in the spinner, and saves the
     * value of the selected restaurant.
     *
     */
    public void addListenerToRestaurantSelectorSpinner(){
        restaurantSelectorSpinner = (Spinner) findViewById(R.id.restaurantSelector);

        restaurantSelectorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String itemSelected = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Todo consider this as well
            }
        });
    }


    /**
     * This method copies the database into memory
     *
     * @param context
     * @return true or false if the operation was successful
     */
    private boolean copyDatabase(Context context) {
        try {
            InputStream inputStream = context.getAssets().open(DatabaseHelper.DBNAME);
            String outFileName = DatabaseHelper.DBLOCATION + DatabaseHelper.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length = 0;
            while((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("MainActivity", "DB Copied");
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * Create the menu object
     *
     * @param menu
     * @return true if the menu is created
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Listens for the items of the menu to be selected to call for
     * the associated activity.
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
