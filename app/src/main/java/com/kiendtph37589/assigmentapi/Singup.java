package com.kiendtph37589.assigmentapi;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Singup extends AppCompatActivity {
    TextView tv_Login;
    ImageView img_back;
    EditText edt_email,edt_password;
    Button btn_singUp;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_singup);
        img_back = findViewById(R.id.img_back);
        tv_Login = findViewById(R.id.tv_Login);

        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        btn_singUp = findViewById(R.id.btn_singUp);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Singup.this,Login.class));
            }
        });
        tv_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Singup.this, Login.class));
            }
        });
        btn_singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password;
                email = String.valueOf(edt_email.getText());
                password = String.valueOf(edt_password.getText());

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(Singup.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;

                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(Singup.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(Singup.this, "Registration successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(Singup.this,Login.class));
                                    finish();
                                }
                                else {
                                    Toast.makeText(Singup.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}