package example.com.demoapp.subCategory.Shopping;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import com.daimajia.swipe.util.Attributes;

import example.com.demoapp.R;
import example.com.demoapp.adapter.SentencesAdapter;
import example.com.demoapp.database.DbHelper;
import example.com.demoapp.model.DisplaySentencesItem;


public class ListSentencesShoppingFragment extends Fragment {
    ListView listView;
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
        listView = (ListView) view.findViewById(R.id.lvSentences);
        initView(view);

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
                listSubcategories = DisplayShoppingSentences1();   //gán dữ liệu từ database vào mảng ArrayList
                mSentencesAdapter = new SentencesAdapter(getActivity(), R.layout.custom_row, listSubcategories); //gán qua Adapter
                listView.setAdapter(mSentencesAdapter);  //từ Adapter lên listview
                mSentencesAdapter.setMode(Attributes.Mode.Single);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Toast.makeText(getActivity(),"Click on: "+(position+1),Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }
    }

    // Lay data from database
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
               // item.setId(cursor.getInt((cursor.getColumnIndex(DbHelper.DB_SENTENCES_ID))));
                item.setName(cursor.getString(cursor.getColumnIndex(DbHelper.DB_SENTENCES_JP)));

                arrayList.add(item);
            }while(cursor.moveToNext());
        }
        cursor.close();
        database.close();
        return arrayList;
    }

}
