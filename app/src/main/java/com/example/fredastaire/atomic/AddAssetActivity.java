package com.example.fredastaire.atomic;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Fred Astaire on 12/10/2017.
 */

public class AddAssetActivity extends AppCompatActivity {

    private EditText addAMEditText;
    private Spinner addManAndModel;
    private EditText addLocationText;
    private EditText addUserIDText;
    private Button addAssetButton;
    private TextView inServiceDate;


    private int month;
    private int day;
    private int year;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_asset);

        setupViews();

        // put today's date as default in the calendar
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

    }

    public void changeDateClickHandler(View v) {
        Log.d("Search Assets", "I hear the Change Date Button");

        DatePickerDialog.OnDateSetListener datePickerListener;

        datePickerListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int yearOfYear, int monthOfYear, int dayOfMonth) {
                year = yearOfYear;
                month = monthOfYear;
                day = dayOfMonth;

                Calendar cal = new GregorianCalendar(year, month, day);
                Date serviceDate = cal.getTime();
                DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
                String cs = df.format(serviceDate);
                inServiceDate.setText(cs);
            }
        };


        DatePickerDialog dpd = new DatePickerDialog(this, datePickerListener, year, month, day);
        dpd.show();

    }

    public void handleAddClick(View v) {
        Asset asset;
        String amNumber = addAMEditText.getText().toString();
        String manAndModel = String.valueOf(addManAndModel.getSelectedItem());
        Date serviceDate = new GregorianCalendar(year, month, day).getTime();
        String location = addLocationText.getText().toString();
        String userID = addUserIDText.getText().toString();

        Log.d("Add Assets", "I hear the Add Button");

        AtomicApplication app = (AtomicApplication) getApplication();

        asset = new Asset(amNumber,manAndModel, serviceDate, location, userID);
        app.addAsset(asset);

        Toast.makeText(this, "Asset Added!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, ListingActivity.class);
        startActivity(intent);
    }

    private void setupViews() {
        addAMEditText = (EditText) findViewById(R.id.addAMEditText);
        addManAndModel = (Spinner) findViewById(R.id.model_spinner);
        inServiceDate = (TextView) findViewById(R.id.addInServiceDate);
        addLocationText = (EditText) findViewById(R.id.addLocationText);
        addUserIDText = (EditText) findViewById(R.id.addUserIDText);
        addAssetButton = (Button) findViewById(R.id.addAssetButton);
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
