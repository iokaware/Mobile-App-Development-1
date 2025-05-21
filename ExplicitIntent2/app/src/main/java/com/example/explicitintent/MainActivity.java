package com.example.explicitintent;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.explicitintent.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static ActivityMainBinding mainBinding;

    public static final String SEND_INTENT_MESSAGE_KEY = "message";
    public static final int REQUEST_TEXT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        if (savedInstanceState != null) {
            if (savedInstanceState.getBoolean("visibility")) {
                mainBinding.textReply.setVisibility(VISIBLE);
                mainBinding.textReply.setText(savedInstanceState.getString("reply"));
            }
        }

        mainBinding.buttonSend.setOnClickListener(view -> {
            if (!mainBinding.editTextMessage.getText().toString().isEmpty()) {
                String message = mainBinding.editTextMessage.getText().toString();

                Intent intent = new Intent(this, SecondActivity.class);
                intent.putExtra(SEND_INTENT_MESSAGE_KEY, message);
//                startActivity(intent);
                startActivityForResult(intent, REQUEST_TEXT);
            }
        });

        Log.d("MainActivity", "OnCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("MainActivity", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("MainActivity", "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("MainActivity", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("MainActivity", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("MainActivity", "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("MainActivity", "onRestart");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_TEXT) {
            if (requestCode == SecondActivity.RESULT_OK) {
                String reply = data.getStringExtra(SecondActivity.REPLY_INTENT_REPLY_KEY);
                mainBinding.textReply.setVisibility(VISIBLE);
                mainBinding.textReply.setText(reply);
            }
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if (mainBinding.textReply.getVisibility() == VISIBLE) {
            outState.putBoolean("visibility", true);
            outState.putString("reply", mainBinding.textReply.getText().toString());
        }
    }
}