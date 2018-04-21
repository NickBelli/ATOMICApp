package com.example.fredastaire.atomic;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class SearchActivity extends AppCompatActivity {

    private EditText amNumberSearchEditText;
    private EditText userSearchText;
    private Button searchButton;
    //private TextView refreshDate;

    private int month;
    private int day;
    private int year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_assets);

        setupViews();

//        // put today's date on the screen
//        Calendar calendar = Calendar.getInstance();
//        year = calendar.get(Calendar.YEAR);
//        month = calendar.get(Calendar.MONTH);
//        day = calendar.get(Calendar.DAY_OF_MONTH);
//
//        Date today = new Date();
//        //Research this later!!
//        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
//        String cs = df.format(today);
//        refreshDate.setText(cs);

    }
//    public void changeDateClickHandler(View v) {
//        Log.d("Search Assets", "I hear the Change Date Button");
//
//        DatePickerDialog.OnDateSetListener datePickerListener;
//
//        datePickerListener = new DatePickerDialog.OnDateSetListener() {
//
//            @Override
//            public void onDateSet(DatePicker view, int yearOfYear, int monthOfYear, int dayOfMonth) {
//                year = yearOfYear;
//                month = monthOfYear;
//                day = dayOfMonth;
//
//                Calendar cal = new GregorianCalendar(year, month, day);
//                Date thisRefreshDate = cal.getTime();
//                DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
//                String cs = df.format(thisRefreshDate);
//                refreshDate.setText(cs);
//            }
//        };
//
//
//        DatePickerDialog dpd = new DatePickerDialog(this, datePickerListener, year, month, day);
//        dpd.show();
//
//    }

    public void handleSearchClick(View v) {

        Log.d("Search Assets", "I hear the Search Button");

        Intent intent = new Intent(this, ListingActivity.class);
        startActivity(intent);
    }

    private void setupViews() {
        amNumberSearchEditText = (EditText) findViewById(R.id.amSearchEditText);
        userSearchText = (EditText) findViewById(R.id.userIDSearchText);
        searchButton = (Button) findViewById(R.id.searchAssets);
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
