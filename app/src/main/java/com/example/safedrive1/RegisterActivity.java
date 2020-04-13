package com.example.safedrive1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import Adapter.PaysAdapter;
import Sql.DatabaseHelper;
import model.PaysItem;
import model.User;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper db;
    Button mButtonRegister;
    EditText mNom, mPrenom, mPwd, mConfirmPwd, mTel, mEmail;
    User user;

    private ArrayList<PaysItem> mPaysList;
    private PaysAdapter mPaysAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initList();
        mNom.findViewById(R.id.registEditTextNom);
        mPrenom.findViewById(R.id.registEditTextPrenom);
        mEmail.findViewById(R.id.regisEditTextEmail);
        mPwd.findViewById(R.id.registEditTextmdp2);
        mConfirmPwd.findViewById(R.id.registEditTextConfirmMdp);
        mTel.findViewById(R.id.registEditPhoneNumber);

        Spinner spinnerPays = findViewById(R.id.spinnerPays);
        mPaysAdapter = new PaysAdapter(this, mPaysList);
        spinnerPays.setAdapter(mPaysAdapter);
        spinnerPays.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                PaysItem clickedItem = (PaysItem) parent.getItemAtPosition(position);
                String clickedPayNom = clickedItem.getNomPays();
                //Toast.makeText(RegisterActivity.this,clickedPayNom + "Selectionné",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String nom = mNom.getText().toString().trim();
                String prenom = mPrenom.getText().toString().trim();
                String pwd = mPwd.getText().toString().trim();
                String confirmPwd = mConfirmPwd.getText().toString().trim();
                String tel = mTel.getText().toString().trim();

                if(pwd.equals(confirmPwd)){
                    long val = db.AddUser(user);
                    if(val > 0){
                        Toast.makeText(RegisterActivity.this,"Enregistrement éffectué !", Toast.LENGTH_SHORT).show();
                        Intent Login = new Intent(RegisterActivity.this, Login.class);
                        startActivity(Login);
                    }
                    else {
                        Toast.makeText(RegisterActivity.this,"Erreur d'enregistrement !", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(RegisterActivity.this,"Les mots de passe sont incorrect !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initList() {
        mPaysList = new ArrayList<>();
        mPaysList.add(new PaysItem("Cameroun", R.drawable.drap_cameroun));
        mPaysList.add(new PaysItem("Canada", R.drawable.drap_canada));
        mPaysList.add(new PaysItem("Allemagne", R.drawable.drap_germany));
        mPaysList.add(new PaysItem("Italie", R.drawable.drap_italie));
        mPaysList.add(new PaysItem("USA", R.drawable.drap_usa));
    }

}
