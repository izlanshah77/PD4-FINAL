package com.example.pd4;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUp extends AppCompatActivity {

    EditText etUser, etName, etPass;
    Button btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etUser = findViewById(R.id.etNewUsername);
        etName = findViewById(R.id.etFullName);
        etPass = findViewById(R.id.etNewPassword);

        btnSignUp = findViewById(R.id.SignUpConfirm);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_users = database.getReference("Users");

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final ProgressDialog dialog = new ProgressDialog(SignUp.this);
                dialog.setMessage("Creating Account...");
                dialog.show();

                table_users.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        if(dataSnapshot.child(etUser.getText().toString()).exists()){
                            dialog.dismiss();
                            Toast.makeText(SignUp.this, "That Username is already taken", Toast.LENGTH_LONG).show();
                        }else{
                            dialog.dismiss();
                            User user = new User(etName.getText().toString(), etPass.getText().toString());
                            table_users.child(etUser.getText().toString()).setValue(user);
                            Toast.makeText(SignUp.this, "Signed Up Successfully", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
