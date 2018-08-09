package com.arturofilio.bo_g;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.arturofilio.bo_g.Utils.DatabaseHelper;
import com.rengwuxian.materialedittext.MaterialEditText;

public class EnterBudget extends AppCompatActivity {

    private EditText mEditText;
    private DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_budget);

        mEditText = (MaterialEditText) findViewById(R.id.monthBudget);
        mDatabaseHelper = new DatabaseHelper(this);

        Cursor data = mDatabaseHelper.getPayment();

    }
}
