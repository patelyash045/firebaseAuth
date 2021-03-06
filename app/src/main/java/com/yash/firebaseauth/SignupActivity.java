package com.yash.firebaseauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {

    private EditText EtEmail;
    private EditText EtPass;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        EtEmail = findViewById(R.id.EtEmail);
        EtPass = findViewById(R.id.EtPass);
        Button btnSignup = findViewById(R.id.btnSignup);
        CheckBox showPass = findViewById(R.id.show_password);
        auth = FirebaseAuth.getInstance();

        showPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked){
                    EtPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    EtPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email1 = EtEmail.getText().toString();
                String pass1 = EtPass.getText().toString();

                if(TextUtils.isEmpty(email1) || TextUtils.isEmpty(pass1)){
                    Toast.makeText(SignupActivity.this,"Enter Email and Password bath",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    regis(email1,pass1);
                }

                EtEmail.setText("");
                EtPass.setText("");
            }
        });

    }
    private void regis(String email,String password){


        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(SignupActivity.this,"Successfully Registered",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(SignupActivity.this,"Failed",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}