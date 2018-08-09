package com.arturofilio.bo_g;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.arturofilio.bo_g.Utils.DatabaseHelper;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.text.DecimalFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    // Variables;
    private Button btnCalculate;
    private Button mbtnEdit;
    private TextView mFinalDate;
    private TextView mTodayDate;
    private TextView mMonthlyBudget;
    private TextView mDailyExp;

    DatabaseHelper mDatabaseHelper;
    //Set Widgets;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mbtnEdit = (Button) findViewById(R.id.btnEdit);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        mFinalDate = (TextView) findViewById(R.id.endDate);
        mTodayDate = (TextView) findViewById(R.id.toDate);
        mMonthlyBudget = (TextView) findViewById(R.id.monthly_budget);
        mDailyExp = (TextView) findViewById(R.id.dailyExp);

        mDatabaseHelper = new DatabaseHelper(this);

        /**
         * IF statement to check if there a payment in the database, if so display it otherwise not.
         */
//        if(mBudgetInput.payment.lenght() != 0) {
//            //get info form db
//
//            // use the data from db to calculate dayExp.
//
//        } else {
//            // don't return anything
//        }

        setTodayDate();
        setFinalDate();


        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Have to redo

//                Double monthlyBudget = Double.parseDouble(mBudgetInput.getText().toString());
//                if(mBudgetInput.length() != 0) {
////                    addData(monthlyBudget);
//
//                    Calendar cal = Calendar.getInstance();
//                    double dayS = cal.get(Calendar.DAY_OF_MONTH);
//                    double dayEnd = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
//                    double remaining = dayEnd - dayS;
//                    addData(monthlyBudget);
//
//                    DecimalFormat df = new DecimalFormat("0.0#");
//
//
//                    String dailyExp = df.format(monthlyBudget/remaining);
//
//                    mDailyExp.setText((String.valueOf("Daily Exp. = $" + dailyExp)));
//
//
//                } else {
//                    Toast.makeText(MainActivity.this, "You must put something in the textField", Toast.LENGTH_SHORT).show();
//                }


            }
        });

        mbtnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EnterBudget.class);
                startActivity(intent);
            }
        });
    }

    public void addData(Double newEntry) {
        boolean insertData = mDatabaseHelper.addBudget(newEntry);

        if(insertData) {
            Toast.makeText(this, "Data Successfully Inserted!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Oops! Something went wrong!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * this will set today's date so the user can see the amount of days left;
     */
    private void setTodayDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        month = month + 1;
        String date = month + "/" + day + "/" + year;
        mTodayDate.setText(date);
    }

    /**
     * this will set the final date so the user can see till when the budget is going to be
     * calculated for.
     */
    private void setFinalDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        month = month + 1;
        String date = month + "/" + day + "/" + year;
        mFinalDate.setText(date);
    }

    /**
     * here the Daily Expenditure will be calculated with database vaules and the method
     * will be called every time the method "OnCreate" is called that way the daily budget
     * will be always updated.
     */
    private void calculateDailyExp() {
        Calendar cal = Calendar.getInstance();
        double dayS = cal.get(Calendar.DAY_OF_MONTH);
        double dayEnd = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        double remaining = dayEnd - dayS;

        DecimalFormat df = new DecimalFormat("0.0#");
        //
    }

}