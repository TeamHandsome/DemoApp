package example.com.demoapp.subCategory.Shopping;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

import example.com.demoapp.R;
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
        ////
        DbHelper db = null;
        try {
            db = new DbHelper(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            db.createdatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        listSubcategories = DbHelper.DisplayShoppingSub();
        mSubCategoriesAdapter = new SubCategoriesAdapter(this, R.layout.activity_shopping_sub_item, listSubcategories);
        listView.setAdapter(mSubCategoriesAdapter);
        mSubCategoriesAdapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;    //item index
                Intent intent = new Intent(getBaseContext(), DisplayShoppingSentencesActivity.class);
                intent.putExtra("position", itemPosition+1);  //gui position le DisplayShopping
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });
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

    }
}
