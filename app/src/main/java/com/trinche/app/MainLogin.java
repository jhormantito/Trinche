package com.trinche.app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainLogin extends AppCompatActivity {

    RelativeLayout Login, Signup;
    EditText Username, Password;
    Button Enter, Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();
        handler.postDelayed(runnable, 2000);

        SharedPreferences sharedPreferences = getSharedPreferences("login_preferences", Context.MODE_PRIVATE);
        if (!sharedPreferences.getString("username", "").equals("") && !sharedPreferences.getString("password", "").equals("")){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

        Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AUTENTICAR(Username.getText().toString(), Password.getText().toString());
            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainLogin.this, MainRegister.class);
                startActivity(intent);
            }
        });
    }

    private void AUTENTICAR (String username, String password) {
        if (username.equals("jhor") && password.equals("123")){
            PREFERENCES(username, password);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Usuario o contraseña invalido", Toast.LENGTH_SHORT).show();
        }
    }

    private void PREFERENCES (String username, String password) {
        SharedPreferences sharedPreferences = getSharedPreferences("login_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.apply();
    }

    private void init() {
        Login = (RelativeLayout) findViewById(R.id.relayLogin);
        Signup = (RelativeLayout) findViewById(R.id.relaySignup);
        Username = (EditText) findViewById(R.id.usernameET);
        Password = (EditText) findViewById(R.id.passwordET);
        Enter = (Button) findViewById(R.id.enterBTN);
        Register = (Button) findViewById(R.id.signupBTN);
    }

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Login.setVisibility(View.VISIBLE);
            Signup.setVisibility(View.VISIBLE);
        }
    };
}
