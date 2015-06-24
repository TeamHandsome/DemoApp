package example.com.demoapp.subCategory.Shopping;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import example.com.demoapp.R;
import example.com.demoapp.adapter.ViewPagerShoppingAdapter;
import example.com.demoapp.tabs.SlidingTabLayout;

public class DisplayShoppingSentencesActivity extends ActionBarActivity {
    private ViewPager pager;
    private ViewPagerShoppingAdapter adapter;
    private SlidingTabLayout tabs;
    CharSequence Titles[]={"文章","画像"};
    int Numboftabs =2;

    public static int pos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_shopping_tab);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SlidingTab();
        pager.setCurrentItem(0);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //khong truyen du lieu qua bundle nua,truyen vao var pos static o tren,class ben kia goi qua static do
            pos = extras.getInt("position");
            ListSentencesShoppingFragment objfrag = ListSentencesShoppingFragment.newInstance();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.pager, objfrag);
            transaction.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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
        // // Creating The ViewPagerShoppingAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter =  new ViewPagerShoppingAdapter(getSupportFragmentManager(),Titles,Numboftabs);

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
}
