package com.example.basedaya2;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignActivity extends AppCompatActivity  {
    private FirebaseAuth mAuth;
    private static final String TAG="SignActivity1";
    private TextView Signup;
    private Button button;
    private TextInputLayout Pass,Email;
    FirebaseDatabase Userdatabase;
    DatabaseReference useData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        span();
        inputText();
        loginClick();
    }
    private void init() {
        setContentView(R.layout.newsign);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mAuth = FirebaseAuth.getInstance();
        Email=(TextInputLayout) findViewById(R.id.LoginRegister);
       Pass=(TextInputLayout) findViewById(R.id.PassRegister);
        button = findViewById(R.id.button3);
        Signup = findViewById(R.id.textView3);
        Userdatabase = FirebaseDatabase.getInstance("https://clicker-768c1-default-rtdb.europe-west1.firebasedatabase.app");
        useData = Userdatabase.getReference();
    }
    private void inputText(){

         Pass.getEditText().addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence s, int start, int count, int after) {

             }

             @Override
             public void onTextChanged(CharSequence s, int start, int before, int count) {
                 if (s.length() == 0 ) {
                     Pass.setError("password is required");
                     Pass.setErrorEnabled(true);
                 } else if (s.length() < 6 ) {
                     Pass.setError("password must be at least 6 character");
                     Pass.setErrorEnabled(true);
                 } else {
                     Pass.setErrorEnabled(false);
                 }
             }

             @Override
             public void afterTextChanged(Editable s) {

             }
         });
    }
    public void loginClick(){
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Email.getEditText().length()>0 && Pass.getEditText().length()>5) {
                    loginSignUp(Email.getEditText().toString().trim(), Pass.getEditText().toString().trim());
                } else {
                    String toastMessage = "Username or Password are not populated";
                    Toast.makeText(getApplicationContext(), toastMessage, Toast.LENGTH_SHORT).show();}}});
    }
    private void loginSignUp(String email,String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            // createUser(user);
                            Intent intent=new Intent(SignActivity.this,MainActivity.class);
                            intent.putExtra("uID",user.getUid());
                            intent.putExtra("email",user.getEmail());
                            startActivity(intent);
                            // updateUI(user);


                        } else {
                            // If sign in fails, display a message to the uer.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(SignActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });}


    //  useData.child("user").push().setValue(user);



    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            currentUser.reload()  ;

        }
    }

    public void Enter(View view) {
        Intent intent=new Intent(SignActivity.this,Register.class);
        startActivity(intent);

    }
    public void span()

    {
        SpannableStringBuilder spannable = new SpannableStringBuilder(" don't have an account? Sign up");
        spannable.setSpan(
                new ForegroundColorSpan(0xFFFFD700),
                24, // start
                31, // end
                Spannable.SPAN_EXCLUSIVE_INCLUSIVE
        );
        Signup.setText(spannable);
    }



    public void resetBut(View view) {
        Intent intent=new Intent(SignActivity.this, ResetPass.class);
        startActivity(intent);

    }




}