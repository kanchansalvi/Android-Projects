package com.example.salvi.mynotes;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText notes;
    private Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        notes = (EditText) findViewById(R.id.noteText);
        saveBtn = (Button) findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                if(notes.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Please enter text", Toast.LENGTH_SHORT).show();
                }
                else {
                    String mynotes = notes.getText().toString();

                    try {
                        writeToFile(mynotes);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        if(readFromFile() != null){
            notes.setText(readFromFile());
        }

    }

    public void writeToFile(String myData) throws IOException {

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("notes.txt", Context.MODE_PRIVATE));
        outputStreamWriter.write(myData);
        outputStreamWriter.close();
    }


    private String readFromFile() {

        String oldNote = "";

        try{
            InputStream inputStream = openFileInput("notes.txt");

            if(inputStream != null){
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String temp = "";
                StringBuilder stringBuilder = new StringBuilder();

                while((temp = bufferedReader.readLine()) != null){
                    stringBuilder.append(temp);
                }
                inputStream.close();
                oldNote = stringBuilder.toString();

            }
        }catch(FileNotFoundException e){
            Log.v("MYActivity","File Not Found" +e.toString());
        }
        catch (IOException ioe){
            ioe.printStackTrace();
        }

        return oldNote;
    }
}
