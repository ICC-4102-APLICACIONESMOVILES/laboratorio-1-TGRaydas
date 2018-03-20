package com.example.tgraydas.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.widget.Toast;

import static android.provider.AlarmClock.EXTRA_MESSAGE;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPref = this.getPreferences(this.MODE_PRIVATE);
        int defaultValue = 0;
        long highScore = sharedPref.getInt("active" , defaultValue);
        if (highScore == 1){
            TextView email = findViewById(R.id.email);
            TextView password = findViewById(R.id.password);
            Button btn = findViewById(R.id.log_out);
            btn.setVisibility(View.VISIBLE);
            email.setText(sharedPref.getString("email", ""));
            password.setText(sharedPref.getString("password", ""));

        }
    }

    public void sendMessage(View view){
        SharedPreferences sharedPref = this.getPreferences(this.MODE_PRIVATE);
        int defaultValue = 0;
        long highScore = sharedPref.getInt("active" , defaultValue);
        if (highScore == 0) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra(EXTRA_MESSAGE, "Sent");
            startActivityForResult(intent, 1);
        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "Usted ya esta logeado", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    public void WriteSharedPreferences(String email, String password){
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("email", email);
        editor.putString("password", password);
        editor.putInt("active", 1);
        editor.commit();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        String info = data.getExtras().getString("Logeado");
        String[] info1 = info.split(";");
        TextView email = findViewById(R.id.email);
        TextView password = findViewById(R.id.password);
        email.setText(info1[1]);
        password.setText(info1[0]);
        WriteSharedPreferences(info1[0], info1[1]);
        String hola = "";
    }
    @Override
    protected void onResume(){
        super.onResume();
        SharedPreferences sharedPref = this.getPreferences(this.MODE_PRIVATE);
        int defaultValue = 0;
        long highScore = sharedPref.getInt("active" , defaultValue);
        if (highScore == 1){
            Button btn = findViewById(R.id.log_out);
            btn.setVisibility(View.VISIBLE);
        }
    }

    public void LogOutManage(View view){
        SharedPreferences sharedPref = this.getPreferences(this.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("active", 0);
        editor.commit();
        Button btn = findViewById(R.id.log_out);
        btn.setVisibility(View.INVISIBLE);
        TextView email = findViewById(R.id.email);
        TextView password = findViewById(R.id.password);
        email.setText("");
        password.setText("");

    }
}
