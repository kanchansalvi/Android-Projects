package com.example.salvi.myideas;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.widget.Adapter;
import android.widget.ListView;

import com.example.salvi.myideas.View.IdeaAdapter;

import java.util.ArrayList;

import data.DatabaseHandler;
import model.MyIdea;

public class DisplayIdeas extends Activity {

    private ListView ideaList;
    private DatabaseHandler dba;
    private ArrayList<MyIdea> dbIdea = new ArrayList<MyIdea>();
    private IdeaAdapter ideaAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_ideas);

        ideaList = (ListView) findViewById(R.id.ideaList);

        refreshData();

    }

    private void refreshData() {
        //dbIdea.clear();

        dba =  new DatabaseHandler(getApplicationContext());

        ArrayList<MyIdea> tempList = dba.getIdeaList();

        for(int i=0; i<tempList.size(); i++){
            String title = tempList.get(i).getTitle();
            String dateText = tempList.get(i).getRecorddate();
            String content = tempList.get(i).getContent();
            int mId = tempList.get(i).getItemId();

            MyIdea tempIdea = new MyIdea();
            tempIdea.setTitle(title);
            tempIdea.setContent(content);
            tempIdea.setRecorddate(dateText);
            tempIdea.setItemId(mId);

            dbIdea.add(tempIdea);
        }

        dba.close();
        ideaAdapter = new IdeaAdapter(DisplayIdeas.this, R.layout.idea_row,dbIdea);
        ideaList.setAdapter(ideaAdapter);
        ideaAdapter.notifyDataSetChanged();

    }
}
