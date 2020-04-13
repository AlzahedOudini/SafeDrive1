package com.example.safedrive1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Sql.DatabaseHelper;

public class Login extends AppCompatActivity {

    DatabaseHelper db;
    Button mBtnConnect;
    EditText mTextEmail;
    EditText mTextID;
    EditText mTextMdp;
    TextView mLinkForRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        db = new DatabaseHelper(this);

        initView();


       mLinkForRegister.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent RegisterActivity = new Intent(Login.this, com.example.safedrive1.RegisterActivity.class);
               startActivity(RegisterActivity);
           }
       });

        mBtnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String myEmail = mTextEmail.getText().toString().trim();
                String myPwd = mTextMdp.getText().toString().trim();

                Boolean res = db.chekUser(myEmail, myPwd);
                if(res == true){
                    Toast.makeText(Login.this,"Connexion correcte !", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(Login.this,"Erreur de connexion !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView(){
        mBtnConnect = findViewById(R.id.btn_connect);
        mTextEmail = findViewById(R.id.connectEditTextEmail);
        mTextMdp = findViewById(R.id.connectEditTextmdp);
        mTextID = findViewById(R.id.connectEditTextEmail);
        mLinkForRegister = findViewById(R.id.textViewLinkForRegister);
    }
}
