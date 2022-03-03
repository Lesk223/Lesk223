package com.example.basedaya2;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener {
    private EditText name, email, password;
    private FirebaseAuth mAuth;
    private Button register;
    FirebaseDatabase Userdatabase;
    DatabaseReference useData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        init();
    }

    public void init() {
        mAuth = FirebaseAuth.getInstance();
        password = findViewById(R.id.PasswordRegisterAct);
        name = findViewById(R.id.editTextTextPersonNameRegisterAct);
        email = findViewById(R.id.loginRegisterAct);
        register = findViewById(R.id.Register);
        Userdatabase = FirebaseDatabase.getInstance("https://clicker-768c1-default-rtdb.europe-west1.firebasedatabase.app");
        useData = Userdatabase.getReference();
        register.setOnClickListener(this);
    }

    public void registerS(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(Register.this, "Sucsess", Toast.LENGTH_SHORT).show();
                            // updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Register.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if (email.getText().length() > 0 && password.getText().length() > 0) {
            registerS(email.getText().toString().trim(), password.getText().toString().trim());
        } else {
            String toastMessage = "Username or Password are not populated";
            Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
        }
    }}