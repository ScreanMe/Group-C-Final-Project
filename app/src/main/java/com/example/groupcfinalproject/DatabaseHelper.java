package com.example.groupcfinalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;



//needs to implement father > SQLiteOpenHelper
public class DatabaseHelper extends SQLiteOpenHelper {

    //? use const = jump to line 29, if we dont create constant then we need to rewrite BUDGET_TABLE again
    public static final String BUDGET_TABLE = "BUDGET_TABLE";
    public static final String COLUMN_BUDGET_NAME = "BUDGET_NAME";
    public static final String COLUMN_BUDGET_AMOUNT = "BUDGET_AMOUNT";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_BUDGET_DATE = "BUDGET_DATE";


    //implementing constructor here as need to satify father side else it gives error on the line 13
    public DatabaseHelper(@Nullable Context context) {
        super(context, "budget.db", null, 2);

    }




    //oncreate has code to add item to db
    @Override
    public void onCreate(SQLiteDatabase db) {
        //main code
        String createTableStatement= "CREATE TABLE " + BUDGET_TABLE + "(" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_BUDGET_NAME + " TEXT, " + COLUMN_BUDGET_AMOUNT + " INT, " + COLUMN_BUDGET_DATE + " TEXT)";
        db.execSQL(createTableStatement);

    }

    //to update modify for changes later thought of

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


    }


    //lets add items finally
    public boolean addOne(BudgetModel budgetModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();//this class takes pairs of values

        cv.put(COLUMN_BUDGET_NAME, budgetModel.getName());
        cv.put(COLUMN_BUDGET_AMOUNT, budgetModel.getAmount());
        cv.put(COLUMN_BUDGET_DATE, budgetModel.getDate());


        long insert = db.insert(BUDGET_TABLE, null, cv);

        if (insert == -1) {
            return false;
        } else {

            return true;
        }
    }
    public boolean deleteOne(BudgetModel budgetModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM "+BUDGET_TABLE+" WHERE "+ COLUMN_ID+" = "+budgetModel.getId();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst())
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //lets list all items
    public List<BudgetModel> getEveryone()
    {
        List<BudgetModel> returnList = new ArrayList<>();
        //
        String queryString = " SELECT * FROM " + BUDGET_TABLE;

        SQLiteDatabase db= this.getReadableDatabase();

        //rawQ is used as its return cursor
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst())
        {
            //loop throught result then create new customers
            do{
                int budgetID = cursor.getInt(0);
                String budgetName = cursor.getString(1);
                int budgetAmount = cursor.getInt(2);
                String budgetDate = cursor.getString(3);

                BudgetModel newBudget= new BudgetModel(budgetID, budgetName, budgetAmount, budgetDate);
                returnList.add(newBudget);
            }while(cursor.moveToNext());
        }
        else {
            // if no items in the list, do nothing
        }
        //close up
        cursor.close();
        db.close();
        return returnList;
    }

    public List<BudgetModel> getSumOfDay()
    {
        List<BudgetModel> returnList = new ArrayList<>();
        //
        //String queryString = "SELECT "+ COLUMN_BUDGET_NAME + ",SUM(" + COLUMN_BUDGET_AMOUNT +") as sum FROM " + BUDGET_TABLE + " WHERE " + COLUMN_BUDGET_DATE + "= ?"
        //String queryString = " SELECT * FROM " + BUDGET_TABLE + "GROUP BY" + COLUMN_BUDGET_DATE;
        String queryString = " SELECT * FROM " + BUDGET_TABLE;

        SQLiteDatabase db= this.getReadableDatabase();

        //rawQ is used as its return cursor
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst())
        {
            //loop throught result then create new customers
            do{
                int budgetID = cursor.getInt(0);
                String budgetName = cursor.getString(1);
                //int budgetAmount = cursor.getInt(2);
                int budgetAmount = cursor.getInt(2);
                String budgetDate = cursor.getString(3);

                BudgetModel newBudget= new BudgetModel(budgetID, budgetName, budgetAmount, budgetDate);
                returnList.add(newBudget);
            }while(cursor.moveToNext());
        }
        else {
            // if no items in the list, do nothing

        }
        //close up
        cursor.close();
        db.close();
        return returnList;
    }
}
