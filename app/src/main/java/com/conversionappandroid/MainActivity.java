package com.conversionappandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    UnitsConverter.VolumeUnits fromVol = UnitsConverter.VolumeUnits.Gallons;
    UnitsConverter.VolumeUnits toVol = UnitsConverter.VolumeUnits.Liters;

    UnitsConverter.LengthUnits fromLen = UnitsConverter.LengthUnits.Yards;
    UnitsConverter.LengthUnits toLen = UnitsConverter.LengthUnits.Meters;

    boolean isLength = true;

    public static final int FROMSELECTION = 1;
    public static final int TOSELECTION = 1;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.settingsButton) {
            Intent intent = new Intent(MainActivity.this, Settings.class);

            //Makes current mode known for selection in Settings
            intent.putExtra("isLength", isLength);

            //TODO: Find a way to get both from and to variables to be acknowledged?
            startActivityForResult(intent, FROMSELECTION);
            //startActivityForResult(intent, TOSELECTION);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**************** UI References *******************/
        //Buttons
        Button calcButton = findViewById(R.id.CalcButton);
        Button clearButton = findViewById(R.id.ClearButton);
        Button modeButton = findViewById(R.id.ModeButton);

        //Text fields
        EditText fromField = findViewById(R.id.FromField);
        EditText toField = findViewById(R.id.ToField);

        //Labels
        TextView fromLabel = findViewById(R.id.FromLabel);
        TextView toLabel = findViewById(R.id.ToLabel);



        fromField.setOnClickListener(v ->{
            toField.setText("");
        });

        toField.setOnClickListener(v ->{
            fromField.setText("");
        });

        clearButton.setOnClickListener(v ->{
            hideSoftKeyBoard();
            fromField.setText("");
            toField.setText("");
        });

        modeButton.setOnClickListener(v ->{
            hideSoftKeyBoard();
            if(isLength) {
                isLength = false;
                fromLabel.setText(fromVol.toString());
                toLabel.setText(toVol.toString());
            }else{
                isLength = true;
                fromLabel.setText(fromLen.toString());
                toLabel.setText(toLen.toString());
            }
        });

        calcButton.setOnClickListener(v -> {
            hideSoftKeyBoard();
            double inVal;
            double newVal;
            if(isLength) {
                if(fromField.getText().toString().equals("") && !toField.getText().toString().equals("")){
                    inVal = Double.parseDouble(toField.getText().toString());
                    newVal = UnitsConverter.convert(inVal, toLen, fromLen);
                    fromField.setText(String.valueOf(newVal));
                    System.out.println(newVal);
                }else  if(toField.getText().toString().equals("") && !fromField.getText().toString().equals("")){
                    inVal = Double.parseDouble(fromField.getText().toString());
                    newVal = UnitsConverter.convert(inVal, fromLen, toLen);
                    toField.setText(String.valueOf(newVal));
                    System.out.println(newVal);
                }
            }else{
                if(fromField.getText().toString().equals("") && !toField.getText().toString().equals("")) {
                    inVal = Double.parseDouble(toField.getText().toString());
                    newVal = UnitsConverter.convert(inVal, toVol, fromVol);
                    fromField.setText(String.valueOf(newVal));
                    System.out.println(newVal);
                }else  if(toField.getText().toString().equals("") && !fromField.getText().toString().equals("")){
                    inVal = Double.parseDouble(fromField.getText().toString());
                    newVal = UnitsConverter.convert(inVal, fromVol, toVol);
                    toField.setText(String.valueOf(newVal));
                    System.out.println(newVal);
                }
            }

        });


    }


    /**
     * Method to hide the keyboard on a button press
     *
     * obtained from: https://stackoverflow.com/questions/3553779/android-dismiss-keyboard
     */
    private void hideSoftKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        if(imm.isAcceptingText()) { // verify if the soft keyboard is open
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }


}
