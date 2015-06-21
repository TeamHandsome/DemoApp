package example.com.demoapp.subCategory;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

import example.com.demoapp.R;
import example.com.demoapp.activity.MainActivity;
import example.com.demoapp.adapter.CategoriesAdapter;
import example.com.demoapp.adapter.SubCategoriesAdapter;
import example.com.demoapp.database.DbHelper;
import example.com.demoapp.model.SubCategoriesItem;

public class ShoppingSubActivity extends ActionBarActivity {
    SimpleCursorAdapter simpleCursorAdapter;
    ListView listView;
    ArrayList<SubCategoriesItem> listSubcategories;
    SubCategoriesAdapter mSubCategoriesAdapter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_sub);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //hien thi icon back

        listView = (ListView) findViewById(R.id.lvSubCategories);
        try {
            showList();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shopping, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.home){
            NavUtils.navigateUpFromSameTask(this); // khi click back icon se go back sourceAcitivy
        }

        return super.onOptionsItemSelected(item);
    }

    public void showList() throws IOException {
        DbHelper db = new DbHelper(this);

        try {
            db.createdatabase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }

        listSubcategories = DbHelper.getAllToListShopping();
        mSubCategoriesAdapter = new SubCategoriesAdapter(this, R.layout.activity_shopping_sub_item, listSubcategories);
        listView.setAdapter(mSubCategoriesAdapter);
    }
}
