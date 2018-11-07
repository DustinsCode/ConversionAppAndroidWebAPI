package com.conversionappandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.conversionappandroid.dummy.HistoryContent;

public class HistoryActivity extends AppCompatActivity
                implements HistoryFragment.OnListFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    @Override
    public void onListFragmentInteraction(HistoryContent.HistoryItem item) {
        Intent intent = new Intent();
        String[] vals = {item.fromVal.toString(), item.toVal.toString(), item.mode, item.fromUnits, item.toUnits};
        intent.putExtra("item", vals);
        setResult(MainActivity.HISTORY_RESULT,intent);
        finish();
    }
}
