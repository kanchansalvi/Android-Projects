package com.example.salvi.myideas;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import data.DatabaseHandler;
import model.MyIdea;

public class MainActivity extends AppCompatActivity {

    private EditText title;
    private  EditText desc;
    public Button saveBtn;
    private DatabaseHandler dba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dba = new DatabaseHandler(MainActivity.this);

        title =  findViewById(R.id.ideaTitle);
         desc =  findViewById(R.id.ideaDescription);
        saveBtn =  findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToDB();
            }
        });
    }

    private  void saveToDB(){

        MyIdea idea = new MyIdea();

        idea.setTitle(title.getText().toString());
        idea.setContent(desc.getText().toString());

        dba.addIdeas(idea);
        dba.close();

        title.setText("");
        desc.setText("");

        Intent i = new Intent(this, DisplayIdeas.class);
        startActivity(i);
    }
}
