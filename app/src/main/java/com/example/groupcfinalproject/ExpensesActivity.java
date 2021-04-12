package com.example.groupcfinalproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class ExpensesActivity extends AppCompatActivity {

    public String total;
    ListView lv_sumofday;

    ArrayAdapter budgetArrayAdapter;
    DatabaseHelper databaseHelper;
    List<sumOfDayClass> getsumofday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);


        lv_sumofday = findViewById(R.id.sumOfDaysList);

        showItemsOnListView();

    }

    private void showItemsOnListView() {

        //to show items in list view
        databaseHelper = new DatabaseHelper(ExpensesActivity.this);
        BudgetModel budgetModel = new BudgetModel();
        getsumofday = databaseHelper.getSumOfDay();
        //important to show item in list view, line #90
        budgetArrayAdapter = new ArrayAdapter<sumOfDayClass>(ExpensesActivity.this, android.R.layout.simple_list_item_1, getsumofday);
        lv_sumofday.setAdapter(budgetArrayAdapter);


    }


    public void goTo_BigNotebookActivity(View view) {
        Intent bignotebook_intent = new Intent(this, BigNotebookActivity.class);
        startActivity(bignotebook_intent);
    }

    public void clear(View view) {
        databaseHelper = new DatabaseHelper(ExpensesActivity.this);
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        databaseHelper.deleteAll();
                        showItemsOnListView();
                        Toast.makeText(ExpensesActivity.this, "All Data Cleared", Toast.LENGTH_SHORT).show();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        dialog.dismiss();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(ExpensesActivity.this);
        builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }

}