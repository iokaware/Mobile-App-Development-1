package com.example.explicitintent;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.explicitintent.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity {

    public static ActivitySecondBinding secondBinding;

    public static final String REPLY_INTENT_REPLY_KEY = "reply";
    public static final int RESULT_OK = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        secondBinding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(secondBinding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.SEND_INTENT_MESSAGE_KEY);
        secondBinding.textMessage.setText(message);

        secondBinding.buttonReply.setOnClickListener(view -> {
            if (!secondBinding.editTextReply.getText().toString().isEmpty()) {
                String reply = secondBinding.editTextReply.getText().toString();

                Intent intentReply = new Intent();
                intentReply.putExtra(REPLY_INTENT_REPLY_KEY, reply);
                setResult(RESULT_OK, intentReply);
                finish();
            }
        });
    }
}