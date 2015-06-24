package example.com.demoapp.subCategory.Shopping;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.fortysevendeg.swipelistview.BaseSwipeListViewListener;
import com.fortysevendeg.swipelistview.SwipeListView;

import java.io.IOException;
import java.util.ArrayList;

import example.com.demoapp.R;
import example.com.demoapp.adapter.SentencesAdapter;
import example.com.demoapp.database.DbHelper;
import example.com.demoapp.model.DisplaySentencesItem;


public class ListSentencesShoppingFragment extends Fragment {
    SwipeListView swipelistView;
    ArrayList<DisplaySentencesItem> listSubcategories;
    SentencesAdapter mSentencesAdapter;

    private DbHelper db ;

    public static ListSentencesShoppingFragment newInstance() {
        ListSentencesShoppingFragment instance = new ListSentencesShoppingFragment();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_sentences_shopping, container, false);
        swipelistView = (SwipeListView) view.findViewById(R.id.lvSentences);
        initView(view);
        SwipeListViewListener();
        return view;
    }

    private void initView(View v) {

        Log.i("pos", DisplayShoppingSentencesActivity.pos + "");
        //DisplayShoppingSentencesActivity.pos
        switch (DisplayShoppingSentencesActivity.pos) {
            case 1:
                try {
                    db = new DbHelper(getActivity());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    db.createdatabase();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                listSubcategories = DisplayShoppingSentences1();
                mSentencesAdapter = new SentencesAdapter(getActivity(), R.layout.custom_row, listSubcategories);
                swipelistView.setAdapter(mSentencesAdapter);
                break;
        }
    }

    public void SwipeListViewListener(){
        swipelistView.setSwipeListViewListener(new BaseSwipeListViewListener() {
            @Override
            public void onOpened(int position, boolean toRight) {
            }

            @Override
            public void onClosed(int position, boolean fromRight) {
            }

            @Override
            public void onListChanged() {
            }

            @Override
            public void onMove(int position, float x) {
            }

            @Override
            public void onStartOpen(int position, int action, boolean right) {
                Log.d("swipe", String.format("onStartOpen %d - action %d", position, action));
            }

            @Override
            public void onStartClose(int position, boolean right) {
                Log.d("swipe", String.format("onStartClose %d", position));
            }

//            @Override
//            public void onClickFrontView(int position) {
//                Log.d("swipe", String.format("onClickFrontView %d", position));
//
//                swipelistView.openAnimate(position); //when you touch front view it will open
//
//            }

            @Override
            public void onClickBackView(int position) {
                Log.d("swipe", String.format("onClickBackView %d", position));

                swipelistView.closeAnimate(position);//when you touch back view it will close
            }

            @Override
            public void onDismiss(int[] reverseSortedPositions) {

            }

        });
        swipelistView.setSwipeMode(SwipeListView.SWIPE_MODE_BOTH); // there are five swiping modes
        swipelistView.setSwipeActionLeft(SwipeListView.SWIPE_ACTION_REVEAL); //there are four swipe actions
        swipelistView.setSwipeActionRight(SwipeListView.SWIPE_ACTION_REVEAL);
        swipelistView.setOffsetLeft(convertDpToPixel(300f)); // left side offset
        swipelistView.setOffsetRight(convertDpToPixel(300f)); // right side offset
        swipelistView.setAnimationTime(500); // Animation time
        swipelistView.setSwipeOpenOnLongPress(false); // enable or disable SwipeOpenOnLongPress

        swipelistView.setAdapter(mSentencesAdapter);

        mSentencesAdapter.notifyDataSetChanged();
    }
    public int convertDpToPixel(float dp) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return (int) px;
    }
    public static ArrayList<DisplaySentencesItem> DisplayShoppingSentences1(){
        ArrayList<DisplaySentencesItem> arrayList = null;

        SQLiteDatabase database = SQLiteDatabase.openDatabase(DbHelper.DB_PATH + DbHelper.DB_NAME, null, SQLiteDatabase.OPEN_READONLY);
        Cursor cursor = database.rawQuery("SELECT * FROM sentences INNER JOIN subcategories" +
                " ON sentences.subcategories_id = subcategories._id" +
                " WHERE sentences.subcategories_id='1'", null);
        if(cursor.moveToFirst())
        {
            arrayList = new ArrayList<DisplaySentencesItem>();
            do
            {
                DisplaySentencesItem item = new DisplaySentencesItem();
                item.setId(cursor.getInt((cursor.getColumnIndex(DbHelper.DB_SENTENCES_ID))));
                item.setName(cursor.getString(cursor.getColumnIndex(DbHelper.DB_SENTENCES_JP)));

                arrayList.add(item);
            }while(cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return arrayList;
    }

}
