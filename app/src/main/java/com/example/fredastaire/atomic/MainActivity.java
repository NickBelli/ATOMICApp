package com.example.fredastaire.atomic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Fred Astaire on 12/10/2017.
 */

public class MainActivity extends AppCompatActivity {

    private Button addAssetButton;
    private Button searchAssetButton;
    private Button viewAllAssets;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();
    }

    public void addAssetClickHandler(View v){
        Intent intent = new Intent(this, AddAssetActivity.class);
        startActivity(intent);
    }

    public void searchAssetClickHandler(View v){
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    public void viewAllClickHandler(View v){
        Intent intent = new Intent(this, ListingActivity.class);
        startActivity(intent);
    }

    private void setupViews() {
        addAssetButton = (Button) findViewById(R.id.addAssetButton);
        searchAssetButton = (Button) findViewById(R.id.searchAssetButton);
        viewAllAssets = (Button) findViewById(R.id.viewAllAssets);
    }
}
