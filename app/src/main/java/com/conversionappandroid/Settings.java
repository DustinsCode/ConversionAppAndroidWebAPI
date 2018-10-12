package com.conversionappandroid;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        Spinner fromSpin = (Spinner) findViewById(R.id.fromSpinner);
        UnitsConverter.LengthUnits[] lengthArray = UnitsConverter.LengthUnits.values();
        UnitsConverter.VolumeUnits[] volumeArray = UnitsConverter.VolumeUnits.values();

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<UnitsConverter.LengthUnits> lengthAdapter = new ArrayAdapter<UnitsConverter.LengthUnits>(this, R.layout.support_simple_spinner_dropdown_item, lengthArray);
// Specify the layout to use when the list of choices appears
        lengthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        fromSpin.setAdapter(lengthAdapter);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
