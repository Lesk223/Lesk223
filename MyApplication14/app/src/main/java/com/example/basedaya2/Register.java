package com.example.basedaya2;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener {
    private TextInputLayout name, email, password;
    private FirebaseAuth mAuth;
    private Button register;
    FirebaseDatabase Userdatabase;
    DatabaseReference useData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
         init();
         inputText();
    }


    public void init() {
        mAuth = FirebaseAuth.getInstance();
        password = findViewById(R.id.PassRegister);
        name = findViewById(R.id.LoginRegister);
        email = findViewById(R.id.EmailRegister);
        register = findViewById(R.id.Register);
        Userdatabase = FirebaseDatabase.getInstance("https://clicker-768c1-default-rtdb.europe-west1.firebasedatabase.app");
        useData = Userdatabase.getReference();
        register.setOnClickListener(this);
    }
    private void inputText(){

        password.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0 ) {
                    password.setError("password is required");
                    password.setErrorEnabled(true);
                } else if (s.length() < 6  ) {
                    password.setError("password must be at least 6 character");
                    password.setErrorEnabled(true);
                } else {
                    password.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
    public void registerS(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            useData.child("user").child(mAuth.getUid()).child("Username").setValue(String.valueOf(name.getEditText()));
                            Toast.makeText(Register.this, "Sucsess", Toast.LENGTH_SHORT).show();
                            finish();
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
        if (name.getEditText().length()<9 &&email.getEditText().length() > 0 && password.getEditText().length() > 5) {
            registerS(email.getEditText().toString().trim(), password.getEditText().toString().trim());
        } else {
            String toastMessage = "Username or Password are not populated";
            Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }}