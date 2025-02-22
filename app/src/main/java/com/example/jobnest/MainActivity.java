package com.example.jobnest;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;
    //Variables
    Animation topAnim,middleAnim,bottomAnim1,bottomAnim2,bottomAnim3;
    ImageView image1, image2;
    TextView text1,text2,text3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //Animation
        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        //middleAnim= AnimationUtils.loadAnimation(this,R.anim.middle_animation);
        bottomAnim1= AnimationUtils.loadAnimation(this,R.anim.bottom_animation1);
        bottomAnim2= AnimationUtils.loadAnimation(this,R.anim.bottom_animation2);
        bottomAnim3= AnimationUtils.loadAnimation(this,R.anim.bottom_animation3);

        //Hooks
        image1= findViewById(R.id.imageView);
        image2= findViewById(R.id.imageView2);
        text1= findViewById(R.id.tagLine1);
        text2= findViewById(R.id.tagLine2);
        text3= findViewById(R.id.tagLine3);

        image1.setAnimation(topAnim);
        image2.setAnimation(topAnim);
        text1.setAnimation(bottomAnim1);
        text2.setAnimation(bottomAnim2);
        text3.setAnimation(bottomAnim3);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,Welcomepage.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);



    }
}