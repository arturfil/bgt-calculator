package com.arturofilio.bo_g;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.rengwuxian.materialedittext.MaterialEditText;

import java.text.DecimalFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    // Variables;
    EditText mBudgetInput;
    private Button btnCalculate;
    private TextView mFinalDate;
    private TextView mTodayDate;
    private TextView mDailyExp;
    private int remainingDays;
    private int monthlyBudget;

    //Set Widgets;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        mFinalDate = (TextView) findViewById(R.id.endDate);
        mTodayDate = (TextView) findViewById(R.id.toDate);
        mBudgetInput = (MaterialEditText) findViewById(R.id.monthBudget);
        mDailyExp = (TextView) findViewById(R.id.dailyExp);

        setTodayDate();
        setFinalDate();


        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal = Calendar.getInstance();
                double dayS = cal.get(Calendar.DAY_OF_MONTH);
                double dayEnd = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
                double remaining = dayEnd - dayS;

                DecimalFormat df = new DecimalFormat("0.0#");
                double monthlyBudget = Double.parseDouble(mBudgetInput.getText().toString());

                String dailyExp = df.format(monthlyBudget/remaining);

                mDailyExp.setText((String.valueOf("Daily Exp. = $" + dailyExp)));
            }
        });
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