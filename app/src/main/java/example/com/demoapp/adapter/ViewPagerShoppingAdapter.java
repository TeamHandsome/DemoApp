package example.com.demoapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import example.com.demoapp.subCategory.Shopping.ListImagesShoppingFragment;
import example.com.demoapp.subCategory.Shopping.ListSentencesShoppingFragment;

/**
 * Created by Long on 6/14/2015.
 */
public class ViewPagerShoppingAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[]; // This will Store the Titles of the Tabs which are Going to be passed when ViewPagerShoppingAdapter is created
    int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerShoppingAdapter is created


    // Build a Constructor and assign the passed Values to appropriate values in the class
    public ViewPagerShoppingAdapter(FragmentManager fm, CharSequence[] mTitles, int mNumbOfTabsumb) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfTabs = mNumbOfTabsumb;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if(position == 0) // if the position is 0 we are returning the First tab
        {
            ListSentencesShoppingFragment tab1 = new ListSentencesShoppingFragment();
            return tab1;
        }
        else             // As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        {
            ListImagesShoppingFragment tab2 = new ListImagesShoppingFragment();
            return tab2;
        }


    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}

