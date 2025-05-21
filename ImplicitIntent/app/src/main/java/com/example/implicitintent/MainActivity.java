package com.example.implicitintent;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.implicitintent.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static ActivityMainBinding mainBinding;

    @SuppressLint("QueryPermissionsNeeded")
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

        mainBinding.buttonWebsite.setOnClickListener(view -> {
            String url = mainBinding.editTextWebsite.getText().toString();

            Uri uri = Uri.parse(url);

            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });

        mainBinding.buttonLocation.setOnClickListener(view -> {
            String location = mainBinding.editTextLocation.getText().toString();

            Uri uri = Uri.parse("geo:0,0?q=" + location);
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        });

        mainBinding.buttonShare.setOnClickListener(view -> {
            String text = mainBinding.editTextShare.getText().toString();

            ShareCompat.IntentBuilder
                    .from(this)
                    .setType("text/plain")
                    .setChooserTitle("Share Text")
                    .setText(text)
                    .startChooser();
        });
    }
}