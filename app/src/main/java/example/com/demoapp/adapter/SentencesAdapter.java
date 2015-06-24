package example.com.demoapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
            holder.button1=(Button)convertView.findViewById(R.id.swipe_button1);
            holder.button2=(Button)convertView.findViewById(R.id.swipe_button2);
            holder.button3=(Button)convertView.findViewById(R.id.swipe_button3);
            convertView.setTag(holder);
        }else
            holder = (ViewHolder) convertView.getTag();
        holder.tvDisplayID.setText(""+listSentences.get(position).getId());
        holder.tvDisplayName.setText(listSentences.get(position).getName());

        holder.button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "Button 1 Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        holder.button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "Button 2 Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        holder.button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "Button 3 Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    private static class ViewHolder{
        TextView tvDisplayID, tvDisplayName;
        Button button1,button2,button3;
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
