package com.example.fredastaire.atomic;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Fred Astaire on 12/5/2017.
 */

public class ListingActivity extends Activity {

    private ArrayList<Asset> allAssets;
    private Bundle savedInstanceState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        setContentView(R.layout.asset_listing);

        AtomicApplication app = (AtomicApplication) getApplication();

        allAssets = app.getAllAssets();

        ArrayAdapter<Asset> adapter = new ArrayAdapter<Asset>(this,
                R.layout.asset_row,
                allAssets
        );

        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                verifyDeleteRow(position);
                Log.d("DEBUG", "I hear the item selection: " + position);

            }

        });
    }

    private void verifyDeleteRow(final int position) {
        // pop up a dialog to confirm delete row
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete this Series?")
                .setMessage("Do you want to delete this data? \n"
                        + allAssets.get(position))
                .setIcon(R.mipmap.atomic_launcher_foreground)
                .setCancelable(false)
                .setNegativeButton("No, go back",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.cancel();
                            }
                        })
                .setPositiveButton("Delete",

                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Asset toDelete = allAssets.get(position);

                                AtomicApplication app = (AtomicApplication) getApplication();
                                app.deleteAsset(toDelete);
                                onCreate(savedInstanceState);
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_home:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.action_add_asset:
                intent = new Intent(this, AddAssetActivity.class);
                startActivity(intent);
                break;
            case R.id.action_search:
                intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
                break;
            case R.id.action_view_all:
                intent = new Intent(this, ListingActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
