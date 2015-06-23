package example.com.demoapp.activity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import example.com.demoapp.R;
import example.com.demoapp.adapter.SentencesAdapter;
import example.com.demoapp.database.DbHelper;
import example.com.demoapp.model.DisplaySentencesItem;
import example.com.demoapp.subCategory.Shopping.DisplayShoppingSentencesActivity;


public class ListSentencesFragment extends Fragment {
    ListView listView;
    ArrayList<DisplaySentencesItem> listSubcategories;
    SentencesAdapter mSentencesAdapter;
    int pos = 0;

    private DbHelper db ;

    public static ListSentencesFragment newInstance() {
        ListSentencesFragment instance = new ListSentencesFragment();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_sentences, container, false);
        initView(view);
        return view;
    }

    private void initView(View v) {
        listView = (ListView) v.findViewById(R.id.lvSentences);
        Log.i("pos",DisplayShoppingSentencesActivity.pos+"");
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
                listSubcategories = db.DisplayShoppingSentences1();
                mSentencesAdapter = new SentencesAdapter(getActivity(), R.layout.activity_display_item, listSubcategories);
                listView.setAdapter(mSentencesAdapter);
                break;
        }
    }

}
