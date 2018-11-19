package com.conversionappandroid;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.conversionappandroid.dummy.HistoryContent;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.joda.time.DateTime;

public class MainActivity extends AppCompatActivity {

    UnitsConverter.VolumeUnits fromVol = UnitsConverter.VolumeUnits.Gallons;
    UnitsConverter.VolumeUnits toVol = UnitsConverter.VolumeUnits.Liters;

    UnitsConverter.LengthUnits fromLen = UnitsConverter.LengthUnits.Yards;
    UnitsConverter.LengthUnits toLen = UnitsConverter.LengthUnits.Meters;

    boolean isLength = true;

    public static final int SETTINGS_REQUEST = 1;
    public static final int HISTORY_RESULT = 2;

    //text fields
    EditText fromField;
    EditText toField;
    //Labels
    TextView fromLabel;
    TextView toLabel;

    //Firebase stuff
    DatabaseReference topRef;

    @Override
    public void onResume(){
        super.onResume();
        topRef = FirebaseDatabase.getInstance().getReference();
    }

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

            startActivityForResult(intent, SETTINGS_REQUEST);

            return true;
        }else if(item.getItemId() == R.id.action_history) {
            Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
            startActivityForResult(intent, HISTORY_RESULT );
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){

        if(resultCode == SETTINGS_REQUEST){
            //Change labels
            String from = data.getStringExtra("from");
            String to = data.getStringExtra("to");

            TextView fromLabel = findViewById(R.id.FromLabel);
            TextView toLabel = findViewById(R.id.ToLabel);
            fromLabel.setText(from);
            toLabel.setText(to);

            if(isLength){
                fromLen = UnitsConverter.LengthUnits.valueOf(from);
                toLen = UnitsConverter.LengthUnits.valueOf(to);
            }else{
                fromVol = UnitsConverter.VolumeUnits.valueOf(from);
                toVol = UnitsConverter.VolumeUnits.valueOf(to);
            }
        }else if (resultCode == HISTORY_RESULT) {
            String[] vals = data.getStringArrayExtra("item");
            this.fromField.setText(vals[0]);
            this.toField.setText(vals[1]);

            this.isLength = (vals[2].equals("Length"));
            if(isLength){
                fromLen = UnitsConverter.LengthUnits.valueOf(vals[3]);
                toLen = UnitsConverter.LengthUnits.valueOf(vals[4]);
            }else{
                fromVol = UnitsConverter.VolumeUnits.valueOf(vals[3]);
                toVol = UnitsConverter.VolumeUnits.valueOf(vals[4]);
            }
            this.fromLabel.setText(vals[3]);
            this.toLabel.setText(vals[4]);
//            this.title.setText(mode.toString() + " Converter");

        }
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
        this.fromField = findViewById(R.id.FromField);
        this.toField = findViewById(R.id.ToField);

        //Labels
        this.fromLabel = findViewById(R.id.FromLabel);
        this.toLabel = findViewById(R.id.ToLabel);



        fromField.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if(hasFocus) {
                    toField.setText("");
                }
            }
        });

        toField.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus){
                if(hasFocus) {
                    fromField.setText("");
                }
            }
        });

        fromField.setOnClickListener(v ->{
            System.out.println("from");
            toField.setText("");
        });

        toField.setOnClickListener(v ->{
            System.out.println("to");
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
            double inVal=0;
            double newVal=0;
            if(isLength) {
                String mode = "Length";
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
                // remember the calculation.
                HistoryContent.HistoryItem item = new HistoryContent.HistoryItem(inVal, newVal, mode,
                        toLen.toString(), fromLen.toString(), DateTime.now());
                HistoryContent.addItem(item);
            }else{
                String mode = "Volume";
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
                // remember the calculation.
                HistoryContent.HistoryItem item = new HistoryContent.HistoryItem(inVal, newVal, mode,
                        toVol.toString(), fromVol.toString(), DateTime.now());
                HistoryContent.addItem(item);
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
