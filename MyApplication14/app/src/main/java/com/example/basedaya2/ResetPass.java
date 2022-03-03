package com.example.basedaya2;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetPass extends AppCompatActivity {
private FirebaseAuth mAuth;
private EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pass);
        email=  findViewById(R.id.editTextTextEmailAddress);
mAuth=FirebaseAuth.getInstance();
    }
    public void reset(View view) {
        if (email.getText().length()>0) {
            mAuth.sendPasswordResetEmail(email.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "Email sent.");
                            }
                        }
                    });

        }else {
        Toast.makeText(this,"Enter your email",Toast.LENGTH_SHORT).show();
}}
}