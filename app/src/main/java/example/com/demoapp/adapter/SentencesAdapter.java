package example.com.demoapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import example.com.demoapp.R;
import example.com.demoapp.model.DisplaySentencesItem;
import example.com.demoapp.model.SubCategoriesItem;

/**
 * Created by Long on 6/21/2015.
 */
public class SentencesAdapter extends ArrayAdapter<DisplaySentencesItem>{

    Context context;
    int idLayoutResource;
    ArrayList<DisplaySentencesItem> listSentences;

    public SentencesAdapter(Context context, int idLayoutResource,
                                ArrayList<DisplaySentencesItem> listSentences) {
        super(context, idLayoutResource, listSentences);
        this.context = context;
        this.idLayoutResource = idLayoutResource;
        this.listSentences = listSentences;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(idLayoutResource, null);
            holder.tvDisplayID = (TextView) convertView.findViewById(R.id.tvDisplayId);
            holder.tvDisplayName = (TextView) convertView.findViewById(R.id.tvDisplayName);
            convertView.setTag(holder);
        }else
            holder = (ViewHolder) convertView.getTag();
        holder.tvDisplayID.setText(""+listSentences.get(position).getId());
        holder.tvDisplayName.setText(listSentences.get(position).getName());

        return convertView;
    }

    private class ViewHolder{
        TextView tvDisplayID, tvDisplayName;
    }
}
