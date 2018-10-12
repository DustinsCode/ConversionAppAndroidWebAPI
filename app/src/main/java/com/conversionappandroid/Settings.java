package com.conversionappandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Settings extends AppCompatActivity {

    private String fromSelection = "Yards";
    private String toSelection = "Meters";
    private boolean isLength;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent payload = getIntent();

        if(payload.hasExtra("isLength")){
            isLength = payload.getBooleanExtra("isLength", true);
        }

        Spinner fromSpin = (Spinner) findViewById(R.id.fromSpinner);
        Spinner toSpin = (Spinner) findViewById(R.id.toSpinner);


        ArrayAdapter<CharSequence> lengthAdapter = ArrayAdapter.createFromResource(this,
                R.array.Lengths, android.R.layout.simple_spinner_item);
        lengthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> volumeAdapter = ArrayAdapter.createFromResource(this,
                R.array.Volumes, android.R.layout.simple_spinner_item);
        lengthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        if(isLength){
            fromSpin.setAdapter(lengthAdapter);
            toSpin.setAdapter(lengthAdapter);
        }else{
            fromSpin.setAdapter(volumeAdapter);
            toSpin.setAdapter(volumeAdapter);
        }

        fromSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l){
                fromSelection = (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView){
            }
        });



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent();
                intent.putExtra("from", fromSelection);
                setResult(MainActivity.FROMSELECTION, intent);
                intent.putExtra("to", toSelection);
                finish();
            }
        });
    }

}
