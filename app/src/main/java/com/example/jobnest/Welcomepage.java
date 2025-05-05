package com.example.jobnest;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.jobnest.components.AuthPreferences;

public class Welcomepage extends AppCompatActivity {
    private AuthPreferences authPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_welcomepage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        authPreferences = new AuthPreferences(this);

//    Find Job Seeker and Recruiter Cards

        CardView jobSeekerCard = findViewById(R.id.jobSeeker_card);
        CardView recruiterCard = findViewById(R.id.recruiter_card);

// Click Listener for Job Seeker Card

        jobSeekerCard.setOnClickListener(view -> {
            authPreferences.saveUserRole("job_seeker");
            Intent intent = new Intent(Welcomepage.this, LogInPage.class);
            startActivity(intent);
        });

        // Click Listener for Job Seeker Card
        recruiterCard.setOnClickListener(view -> {
            authPreferences.saveUserRole("recruiter");
            Intent intent = new Intent(Welcomepage.this, LogInPage.class);
            startActivity(intent);
        });

    }
}