package com.example.groupcfinalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BigNotebookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_notebook);
    }

    public void goTo_SmallNotebookActivity(View view) {
        Intent smallnotebook_intent = new Intent(this, SmallNotebookActivity.class);
        startActivity(smallnotebook_intent);
    }

    public void goTo_ExpensesActivity(View view) {
        Intent expenses_intent = new Intent(this, ExpensesActivity.class);
        startActivity(expenses_intent);
    }
}