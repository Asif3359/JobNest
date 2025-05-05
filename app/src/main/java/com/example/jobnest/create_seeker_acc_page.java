package com.example.jobnest;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.jobnest.components.AuthPreferences;

public class create_seeker_acc_page extends AppCompatActivity {

    private EditText etFirstName, etLastName, etEmail, etPhoneNumber, etPassword, etConfirmPassword;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create_seeker_acc_page);
        dbHelper = new DBHelper(this);

        etFirstName = findViewById(R.id.firstName);
        etLastName = findViewById(R.id.lastName);
        etEmail = findViewById(R.id.email);
        etPhoneNumber = findViewById(R.id.phone);
        etPassword = findViewById(R.id.password);
        etConfirmPassword = findViewById(R.id.confirmPassword);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button signUpButton = findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(v -> {
            // Get user input
            String firstName = etFirstName.getText().toString().trim();
            String lastName = etLastName.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String phone = etPhoneNumber.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String confirmPassword = etConfirmPassword.getText().toString().trim();

            // Validate inputs
            if (validateInputs(firstName, lastName, email, phone, password, confirmPassword)) {
                // Get user role
                AuthPreferences authPreferences = new AuthPreferences(this);
                String userRole = authPreferences.getUserRole();

                if (userRole != null && userRole.equals("job_seeker")) {
                    // Insert job seeker data into database
                    boolean isSuccess = dbHelper.insertDataSeekerUser(
                            firstName, lastName, email, phone, password, userRole);

                    if (isSuccess) {
                        Log.d("SignUpActivity", "Job seeker account created successfully");
                        Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show();

                        // Navigate to job seeker dashboard
                        Intent intent = new Intent(this, LogInPage.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(this, "Account creation failed. Email may already exist.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Invalid user role", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(v -> {
            // Example: Navigate to a "SignUpPage" activity
            Intent intent = new Intent(create_seeker_acc_page.this, LogInPage.class);
            startActivity(intent);

        });
    }
    private boolean validateInputs(String firstName, String lastName, String email,
                                   String phone, String password, String confirmPassword) {
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() ||
                phone.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Enter a valid email address");
            return false;
        }

        if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError("Passwords don't match");
            return false;
        }

        if (password.length() < 6) {
            etPassword.setError("Password must be at least 6 characters");
            return false;
        }

        return true;
    }
}