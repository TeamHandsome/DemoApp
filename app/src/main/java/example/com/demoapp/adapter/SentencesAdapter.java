package example.com.demoapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import example.com.demoapp.R;
import example.com.demoapp.model.DisplaySentencesItem;
import example.com.demoapp.model.SubCategoriesItem;

/**
 * Created by Long on 6/21/2015.
 */
public class SentencesAdapter extends BaseAdapter {

    Activity context;
    int idLayoutResource;
    ArrayList<DisplaySentencesItem> listSentences;

    public SentencesAdapter(Activity context, int idLayoutResource, ArrayList<DisplaySentencesItem> listSentences) {
        this.context = context;
        this.idLayoutResource = idLayoutResource;
        this.listSentences = listSentences;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(idLayoutResource,parent,false);
            holder.tvDisplayID = (TextView) convertView.findViewById(R.id.tvDisplayId);
            holder.tvDisplayName = (TextView) convertView.findViewById(R.id.tvDisplayName);
            convertView.setTag(holder);
        }else
            holder = (ViewHolder) convertView.getTag();
        holder.tvDisplayID.setText(""+listSentences.get(position).getId());
        holder.tvDisplayName.setText(listSentences.get(position).getName());

        return convertView;
    }

    private static class ViewHolder{
        TextView tvDisplayID, tvDisplayName;
    }

    @Override
    public int getCount() {
        if (listSentences != null) {
            return listSentences.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return listSentences.get(i);
    }

    @Override
    public long getItemId(int i) {
        return listSentences.get(i).hashCode();
    }

}
