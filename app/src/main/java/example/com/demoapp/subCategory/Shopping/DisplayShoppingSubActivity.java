package example.com.demoapp.subCategory.Shopping;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

public class DisplayShoppingSubActivity extends ActionBarActivity {
    SimpleCursorAdapter simpleCursorAdapter;
    ListView listView;
    ArrayList<SubCategoriesItem> listSubcategories;
    SubCategoriesAdapter mSubCategoriesAdapter;
    Context context;
    private DbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_sub);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //hien thi icon back

        initView();
    }
    public void initView (){
        listView = (ListView) findViewById(R.id.lvSubCategories);
        ////
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
        listSubcategories = DisplayShoppingSub();
        mSubCategoriesAdapter = new SubCategoriesAdapter(this, R.layout.activity_shopping_sub_item, listSubcategories);
        listView.setAdapter(mSubCategoriesAdapter);
        mSubCategoriesAdapter.notifyDataSetChanged();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int itemPosition = position;    //item index
                Intent intent = new Intent(getBaseContext(), DisplayShoppingSentencesActivity.class);
                intent.putExtra("position", itemPosition+1);  //gui position len Sentences
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

            }
        });
    }
    public static ArrayList<SubCategoriesItem> DisplayShoppingSub()
    {
        ArrayList<SubCategoriesItem> arrayList = null;

        SQLiteDatabase database = SQLiteDatabase.openDatabase(DbHelper.DB_PATH + DbHelper.DB_NAME, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = database.rawQuery("select * from subcategories where categories_id = 7", null);
        //kiểm tra cursor có dữ liệu không? Nếu có, thưc hiện lấy dữ liệu từ cursor cho vào
        //mảng arrayList
        if(cursor.moveToFirst())
        {
            arrayList = new ArrayList<SubCategoriesItem>();
            do
            {
                SubCategoriesItem item = new SubCategoriesItem();
                item.setId(cursor.getInt((cursor.getColumnIndex(DbHelper.DB_SUBCATEGORIES_ID))));
                item.setName(cursor.getString(cursor.getColumnIndex(DbHelper.DB_SUBCATEGORIES_NAME)));

                arrayList.add(item);
            }while(cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return arrayList;
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
}
