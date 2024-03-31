package com.kiendtph37589.assigmentapi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    TextView tv_creat,tv_forgot_password;
    Button btn_Signin;
    EditText edt_email,edt_password;
    CheckBox cb_remember;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
    tv_creat = findViewById(R.id.tv_creat);
    tv_forgot_password = findViewById(R.id.tv_forgot_password);
    btn_Signin = findViewById(R.id.btn_Signin);
    edt_email = findViewById(R.id.edt_email);
    edt_password = findViewById(R.id.edt_password);
    cb_remember = findViewById(R.id.cb_remember);

    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
   String checkbox = preferences.getString("remember","");
    if (checkbox.equals("true")){
        Intent intent = new Intent(Login.this,MainActivity.class);
        startActivity(intent);
    } else if (checkbox.equals("false")) {
        Toast.makeText(this, "Please Sign In", Toast.LENGTH_SHORT).show();
    }

        tv_creat.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(Login.this,Singup.class));
        }
    });
    btn_Signin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           String email,password;
           email = String.valueOf(edt_email.getText());
           password = String.valueOf(edt_password.getText());

           if (TextUtils.isEmpty(email)){
               Toast.makeText(Login.this, "Enter email", Toast.LENGTH_SHORT).show();
               return;
           }
           if (TextUtils.isEmpty(password)){
               Toast.makeText(Login.this, "Enter password", Toast.LENGTH_SHORT).show();
               return;
           }
           firebaseAuth.signInWithEmailAndPassword(email,password)
                   .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()){
                               Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                               startActivity(new Intent(Login.this,MainActivity.class));
                               finish();
                           }
                           else {
                               Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                           }
                       }
                   });
        }

    });
    cb_remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (buttonView.isChecked()){
                SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember", "true");
                editor.apply();
                Toast.makeText(Login.this, "Checked", Toast.LENGTH_SHORT).show();
            }else if (!buttonView.isChecked()){
                SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember", "false");
                editor.apply();
                Toast.makeText(Login.this, "Unchecked", Toast.LENGTH_SHORT).show();
            }
        }
    });
    tv_forgot_password.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
            View view = getLayoutInflater().inflate(R.layout.dialog_forgot_password,null);
            EditText edt_mail = view.findViewById(R.id.edt_forgot_password);

            builder.setView(view);
            builder.setCancelable(false);
            AlertDialog dialog = builder.create();

            view.findViewById(R.id.btn_forgot_password).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String mail = edt_mail.getText().toString();

                    if (TextUtils.isEmpty(mail) && !Patterns.EMAIL_ADDRESS.matcher(mail).matches()){
                        Toast.makeText(Login.this, "Enter your registered email id", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    firebaseAuth.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(Login.this, "Check your email", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }else {
                                Toast.makeText(Login.this, "Unable to send, failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });
            view.findViewById(R.id.btn_back).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            if (dialog.getWindow() != null){
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
            }
            dialog.show();
        }
    });
//    private void showDialog(){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        LayoutInflater inflater = getLayoutInflater();
//        builder.setCancelable(false);
//        View view = inflater.inflate(R.layout.dialog_forgot_password,null);
//        builder.setView(view);
//
//        AlertDialog alertDialog = builder.create();
//        alertDialog.show();
//        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//
//        // Xu li chuc nang
//        EditText edt_forgot = view.findViewById(R.id.edt_forgot_password);
//        Button btn_forgot_pass = view.findViewById(R.id.btn_forgot_password);
//        Button btn_back = view.findViewById(R.id.btn_back);
//
//        btn_forgot_pass.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String email = edt_forgot.getText().toString();
//                if (!TextUtils.isEmpty(email)){
//
//                }
//            }
//        });
    }
}