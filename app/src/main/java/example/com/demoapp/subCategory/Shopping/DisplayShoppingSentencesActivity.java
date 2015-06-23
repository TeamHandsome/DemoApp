package example.com.demoapp.subCategory.Shopping;

import android.support.v4.app.FragmentTransaction;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

import example.com.demoapp.R;
import example.com.demoapp.activity.ListSentencesFragment;
import example.com.demoapp.adapter.SentencesAdapter;
import example.com.demoapp.adapter.ViewPagerShopping;
import example.com.demoapp.database.DbHelper;
import example.com.demoapp.model.DisplaySentencesItem;
import example.com.demoapp.tabs.SlidingTabLayout;

public class DisplayShoppingSentencesActivity extends ActionBarActivity {
    private ViewPager pager;
    private ViewPagerShopping adapter;
    private SlidingTabLayout tabs;
    CharSequence Titles[]={"文章","画像"};
    int Numboftabs =2;

    private DbHelper mDbHelper;
    private SQLiteDatabase dataBase;
    ArrayList<DisplaySentencesItem> listSubcategories;
    SentencesAdapter mSubCategoriesAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_shopping);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //hien thi icon back
        //Declare Tabs
        SlidingTab();
        pager.setCurrentItem(0);
        ///////////
//        listView = (ListView) findViewById(R.id.lvSentences);
       // Lấy bundle đc gửi từ ShoppingSubActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Bundle bundle = new Bundle();
            int value = extras.getInt("position");
            bundle.putInt("position",value);
            ListSentencesFragment objfrag = new ListSentencesFragment();
            objfrag.setArguments(bundle);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.pager, objfrag);
            transaction.commit();
        }


//        //Trong sub categories có bao nhiêu
//        switch (value){
//            case 1:
//                try {
//                    showList1();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                break;
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display, menu);
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
//        if (id == R.id.home){
//            NavUtils.navigateUpFromSameTask(this); // khi click back icon se go back sourceAcitivy
//        }

        return super.onOptionsItemSelected(item);
    }
    public void SlidingTab(){
        // // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter =  new ViewPagerShopping(getSupportFragmentManager(),Titles,Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);
    }
    public void showList1() throws IOException {
        DbHelper db = new DbHelper(this);

        try {
            db.createdatabase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }

        listSubcategories = DbHelper.DisplayShoppingSentences1();
        mSubCategoriesAdapter = new SentencesAdapter(this, R.layout.activity_display_item, listSubcategories);
        listView.setAdapter(mSubCategoriesAdapter);
    }
}
