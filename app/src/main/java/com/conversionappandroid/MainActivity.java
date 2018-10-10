package com.conversionappandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    UnitsConverter.VolumeUnits fromVol;
    UnitsConverter.VolumeUnits toVol;

    UnitsConverter.LengthUnits fromLen;
    UnitsConverter.LengthUnits toLen;

    boolean isLength = true;


    /**************** UI References *******************/
    //Buttons
    Button calcButton = findViewById(R.id.CalcButton);
    Button clearButton = findViewById(R.id.ClearButton);
    Button modeButton = findViewById(R.id.CalcButton);

    //Text fields
    EditText fromField = findViewById(R.id.FromField);
    EditText toField = findViewById(R.id.ToField);

    //Labels
    TextView fromLabel = findViewById(R.id.FromLabel);
    TextView toLabel = findViewById(R.id.ToLabel);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


//    calcButton.setOnclickListener(v -> {
//        if(isLength) {
//            fromLen
//            UnitsConverter.convert();
//        }
//    });


}
