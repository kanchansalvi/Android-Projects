package com.example.salvi.myideas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import data.DatabaseHandler;

public class IdeaDetails extends AppCompatActivity {

    private TextView title, detail, date;
    private Button deleteBtn, editBtn;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idea_details);

        title = findViewById(R.id.detailsTitle);
        detail = findViewById(R.id.detailsTextView);
        date = findViewById(R.id.detailsDateView);
        deleteBtn = findViewById(R.id.deleteBtn);
        editBtn = findViewById(R.id.editBtn);

        Bundle extras = getIntent().getExtras();

        if(extras != null) {
            title.setText(extras.getString("title"));
            date.setText(extras.getString("dateText"));
            detail.setText(extras.getString("content"));

             id = extras.getInt("id");
        }

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DatabaseHandler dba = new DatabaseHandler(getApplicationContext());
                    dba.deleteIdea(id);

                    Toast.makeText(getApplicationContext(), "Your Idea is Deleted", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(IdeaDetails.this, DisplayIdeas.class));
                }
            });
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent editIdea = new Intent(IdeaDetails.this, MainActivity.class);
                editIdea.putExtra("title", title.getText());
                editIdea.putExtra("content", detail.getText());
                editIdea.putExtra("id", id);
                startActivity(editIdea);
            }
        });


    }
}
