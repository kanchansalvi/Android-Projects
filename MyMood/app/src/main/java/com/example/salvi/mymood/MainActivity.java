package com.example.salvi.mymood;
/* Description  : App to scan your mood today.
 * Checks       : Animation part is not working as expected.
 * Ideas        : create another app same to this. eg. Love calculator
 * Origin       : Udemy course source code
 *                https://www.udemy.com/complete-android-developer-course/learn/v4/t/lecture/2392612?start=0*/

import android.app.ActionBar;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ImageView thumbPrint;
    private TextView result;
    private AnimationDrawable thumbAnimation;
    private String[] moodResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a list of moods to display

        moodResults = new String[] {
                "Someone is cranky",
                "You are my Sunshine",
                "No comments",
                "HAppy Camper",
                "Not your day :(",
                "Smile - It's good for you",
                "In the Clouds",
                "Pensive !",
                "Sad",
                "Excited!!"
        };

        thumbPrint = (ImageView) findViewById(R.id.thumbPrint);
        thumbPrint.setBackgroundResource(R.drawable.thumb_animation);
        thumbAnimation = (AnimationDrawable) thumbPrint.getBackground();

        result = (TextView) findViewById(R.id.resultText);

        thumbPrint.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //Start animation (check : Not working)
                thumbAnimation.start();
                showResult();


                return  true;
            }
        });


    }

    public void showResult(){

        //Keep continously running to capture the pressing of user

        Runnable mrunnable = new Runnable() {
            @Override
            public void run() {

                // Select random mood from list of moods.
                int rand = (int) (Math.random() * moodResults.length);
                result.setText(moodResults[rand]);
                //Stop Animation
                thumbAnimation.stop();
            }
        };

        Handler mhandler = new Handler();
        mhandler.postDelayed(mrunnable, 1000);


    }
}

