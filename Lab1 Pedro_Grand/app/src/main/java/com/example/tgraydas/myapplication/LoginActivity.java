package com.example.tgraydas.myapplication;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.


    }
    public boolean CheckInfo (String email, String password){
        String[] check_email = email.split("@");
        Integer len = check_email.length;
        if (len == 2 && password.length() > 5){
            return true;
        }
        else {
            return false;
        }
    }

    public void Login (View view){
        TextView tv = findViewById(R.id.email);
        TextView password_view = findViewById(R.id.password);
        Boolean checkInfo = CheckInfo(tv.getText().toString(), password_view.getText().toString());
        if (checkInfo == true){
            Toast toast = Toast.makeText(getApplicationContext(), "Ingreso los Datos Correctos", Toast.LENGTH_SHORT);
            toast.show();
            Intent intent = getIntent();
            String data = password_view.getText().toString() + ";" + tv.getText().toString();
            intent.putExtra("Logeado", data);
            setResult(RESULT_OK, intent);
            finish();
        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "Los Datos no son Correctos", Toast.LENGTH_SHORT);
            toast.show();
        }
    }



}


