package com.example.groupcfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SmallNotebookActivity extends AppCompatActivity {


    // reference to all buttons etc
    Button btn_add;
    EditText et_name, et_amount;
    ListView lv_nameList;

    ArrayAdapter budgetArrayAdapter;
    DatabaseHelper databaseHelper;
    List<BudgetModel> everyone;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_small_notebook);


    btn_add=findViewById(R.id.btn_add);
    et_name=findViewById(R.id.et_name);
    et_amount=findViewById(R.id.et_amount);
    lv_nameList=findViewById(R.id.lv_nameList);





        //necessary for showing in list view. //simple_list_item_1 most basic view
        showItemsOnListView();
        /*UPDATE LIST as soon we start ^ */

    btn_add.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            BudgetModel budgetModel;
            try {//using try catch here to catch if a dumb user enters nothing and clicks add button


                // -1 is a default value cuz why not?
                budgetModel = new BudgetModel(-1, et_name.getText().toString(), Integer.parseInt(et_amount.getText().toString()), "01/01/2000");
                Toast.makeText(SmallNotebookActivity.this, budgetModel.toString(), Toast.LENGTH_SHORT).show();
            }

            catch(Exception e)
            {
                Toast.makeText(SmallNotebookActivity.this, "Error creating item!", Toast.LENGTH_SHORT).show();
                //if it fails add this as default
                 budgetModel = new BudgetModel(-1, "error creating item!", 0, "01/01/2000");
            }

            DatabaseHelper databaseHelper = new DatabaseHelper(SmallNotebookActivity.this);
            boolean success = databaseHelper.addOne(budgetModel);

            Toast.makeText(SmallNotebookActivity.this, "Success = "+success, Toast.LENGTH_SHORT).show();
            /*UPDATE LIST as soon we add*/
            showItemsOnListView();
        }
    });

    lv_nameList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            BudgetModel clickedItem = (BudgetModel)parent.getItemAtPosition(position);
            databaseHelper.deleteOne(clickedItem);
            showItemsOnListView();
            Toast.makeText(SmallNotebookActivity.this, "Deleted ",Toast.LENGTH_SHORT).show();

        }
    });

    }

    private void showItemsOnListView() {

        //to show items in list view
        databaseHelper = new DatabaseHelper(SmallNotebookActivity.this);
        everyone = databaseHelper.getEveryone();
        //important to show item in list view, line #90
        budgetArrayAdapter = new ArrayAdapter<BudgetModel>(SmallNotebookActivity.this, android.R.layout.simple_list_item_1, everyone);
        lv_nameList.setAdapter(budgetArrayAdapter);


    }



    public void goTo_HomeActivity(View view) {
        Intent home_intent = new Intent(this, MainActivity.class);
        startActivity(home_intent);
    }

    public void goTo_BigNotebookActivity(View view) {
        Intent bignotebook_intent = new Intent(this, BigNotebookActivity.class);
        startActivity(bignotebook_intent);
    }


}