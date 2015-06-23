package example.com.demoapp.activity;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

import example.com.demoapp.R;
import example.com.demoapp.adapter.SentencesAdapter;
import example.com.demoapp.database.DbHelper;
import example.com.demoapp.model.DisplaySentencesItem;


public class ListSentencesFragment extends Fragment {
    ListView listView;
    ArrayList<DisplaySentencesItem> listSubcategories;
    SentencesAdapter mSentencesAdapter;
    Context context;

    private DbHelper db ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_sentences, container, false);


        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView = (ListView) getView().findViewById(R.id.lvSentences);
        if (getArguments()!=null){
            int position = getArguments().getInt("position");
            System.out.println(position);
            switch (position){
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

                    listSubcategories = DbHelper.DisplayShoppingSentences1();
                    mSentencesAdapter = new SentencesAdapter(getActivity(), R.layout.activity_display_item, listSubcategories);
                    listView.setAdapter(mSentencesAdapter);
                    mSentencesAdapter.notifyDataSetChanged();
                    break;
            }

        }
    }

    public void showList1() throws IOException {

    }


}
