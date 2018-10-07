package com.example.salvi.myideas.View;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.salvi.myideas.DisplayIdeas;
import com.example.salvi.myideas.IdeaDetails;
import com.example.salvi.myideas.R;

import java.util.ArrayList;

import model.MyIdea;

public class IdeaAdapter extends ArrayAdapter<MyIdea>{

    Activity activity;
    int layoutResource;
    //MyIdea idea;
    ArrayList<MyIdea> mData = new ArrayList<MyIdea>();

    public IdeaAdapter(Activity act, int resource, ArrayList<MyIdea>  data) {
        super(act, resource, data);
        activity = act;
        layoutResource = resource;
        mData = data;

        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Nullable
    @Override
    public MyIdea getItem(int position) {
        return mData.get(position);
    }

    public int getPosition(@Nullable MyIdea item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
        final ViewHolder holder ;

        if((row == null) || (row.getTag() == null)){

            LayoutInflater inflater = LayoutInflater.from(activity);
            row = inflater.inflate(layoutResource, null);

            holder = new ViewHolder();

            holder.mTitle =  row.findViewById(R.id.name);
            holder.mDate = row.findViewById(R.id.dateText);

            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        final ViewHolder finalholder = holder;
        holder.idea = getItem(position);
        holder.mTitle.setText(holder.idea.getTitle());
        holder.mDate.setText(holder.idea.getRecorddate());
      //  holder.mId.setText(holder.idea.getItemId());

        holder.mTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = finalholder.idea.getContent().toString();
                String dateText = finalholder.idea.getRecorddate().toString();
                String title  = finalholder.idea.getTitle().toString();
                int mId = finalholder.idea.getItemId();
                Log.v("Bundle ID Send ::", String.valueOf(mId));

                Intent ideaDetail = new Intent(getContext(), IdeaDetails.class);
                ideaDetail.putExtra("content", text);
                ideaDetail.putExtra("dateText", dateText);
                ideaDetail.putExtra("title", title);
                ideaDetail.putExtra("id", mId);
                getContext().startActivity(ideaDetail);

            }
        });
        return row;
    }

    class ViewHolder{

        MyIdea idea;
        int mId;
        TextView mTitle;
        TextView mContent;
        TextView mDate;
    }

}
