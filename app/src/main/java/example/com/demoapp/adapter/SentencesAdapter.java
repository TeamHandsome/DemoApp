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
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.ArraySwipeAdapter;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.swipe.implments.SwipeItemMangerImpl;
import com.daimajia.swipe.interfaces.SwipeItemMangerInterface;

import java.util.ArrayList;
import java.util.List;

import example.com.demoapp.R;
import example.com.demoapp.model.DisplaySentencesItem;
import example.com.demoapp.model.SubCategoriesItem;

/**
 * Created by Long on 6/21/2015.
 */
public class SentencesAdapter extends ArraySwipeAdapter<DisplaySentencesItem> {

    Activity context;
    int idLayoutResource;
    ArrayList<DisplaySentencesItem> listSentences;

    private static class ViewHolder{
        TextView tvDisplayID, tvDisplayName;
        ImageButton btDelete, btFavorite;
        SwipeLayout swipeLayout;
    }
    public SentencesAdapter(Activity context, int idLayoutResource, ArrayList<DisplaySentencesItem> listSentences) {
        super(context, idLayoutResource , listSentences);
        this.context=context;
        this.idLayoutResource=idLayoutResource;
        this.listSentences=listSentences;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        View v =super.getView(position, convertView, parent);
        ViewHolder holder = null;
        if(convertView == null){
            holder = new ViewHolder();
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(idLayoutResource,parent,false);
            //holder.tvDisplayID = (TextView) convertView.findViewById(R.id.tvDisplayId);
            holder.swipeLayout = (SwipeLayout) convertView.findViewById(R.id.swipe);
            holder.tvDisplayName = (TextView) convertView.findViewById(R.id.tvDisplayName);
            holder.btDelete = (ImageButton) convertView.findViewById(R.id.btDelete);
            //holder.btFavorite = (ImageButton) convertView.findViewById(R.id.btFavorite);

            holder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);        //set kiểu swipe
            holder.swipeLayout.addDrag(SwipeLayout.DragEdge.Left, convertView.findViewById(R.id.drag_ledge)); //add swipe left
//            holder.swipeLayout.addSwipeListener(new SimpleSwipeListener() {
//                @Override
//                public void onOpen(SwipeLayout layout) {
//                    YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.drag_right));
//                }
//            });
            final ViewHolder finalHolder = holder;  // final
            holder.btDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(v.getContext(), "Deleted " + finalHolder.tvDisplayName.getText().toString()+"!", Toast.LENGTH_SHORT).show();
                }
            });
            holder.btFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(), "Favorite " + finalHolder.tvDisplayName.getText().toString()+"!", Toast.LENGTH_SHORT).show();
                }
            });

            convertView.setTag(holder);
        }else
            holder = (ViewHolder) convertView.getTag();

        holder.tvDisplayName.setText(listSentences.get(position).getName());


        return convertView;
    }

    @Override
    public int getSwipeLayoutResourceId(int i) {
        return R.id.swipe;
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
