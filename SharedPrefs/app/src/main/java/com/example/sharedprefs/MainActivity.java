package com.example.sharedprefs;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.sharedprefs.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static ActivityMainBinding mainBinding;

    private int count;
    private int color;

    private final String COUNT_KEY = "count";
    private final String COLOR_KEY = "color";

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private String packageName = "com.example.sharedprefs";

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

        preferences = getSharedPreferences(packageName, MODE_PRIVATE);
        editor = preferences.edit();

        count = preferences.getInt(COUNT_KEY, 0);
        color = preferences.getInt(COLOR_KEY, getColor(R.color.default_background));

        mainBinding.countTextview.setText(String.valueOf(count));
        mainBinding.countTextview.setBackgroundColor(color);

        mainBinding.countButton.setOnClickListener(view -> {
            count++;
            mainBinding.countTextview.setText(String.valueOf(count));
        });

        mainBinding.blackBackgroundTextview.setOnClickListener(view -> {
            color = getColor(R.color.black);
            mainBinding.countTextview.setBackgroundColor(color);
        });
        mainBinding.redBackgroundTextview.setOnClickListener(view -> {
            color = getColor(R.color.red_background);
            mainBinding.countTextview.setBackgroundColor(color);
        });
        mainBinding.blueBackgroundTextview.setOnClickListener(view -> {
            color = getColor(R.color.blue_background);
            mainBinding.countTextview.setBackgroundColor(color);
        });
        mainBinding.greenBackgroundTextview.setOnClickListener(view -> {
            color = getColor(R.color.green_background);
            mainBinding.countTextview.setBackgroundColor(color);
        });

        mainBinding.resetButton.setOnClickListener(view -> {
            count = 0;
            color = getColor(R.color.default_background);

            mainBinding.countTextview.setText("0");
            mainBinding.countTextview.setBackgroundColor(getColor(R.color.default_background));
        });
    }

    @Override
    protected void onPause() {
        super.onPause();

        editor.putInt(COUNT_KEY, count);
        editor.putInt(COLOR_KEY, color);
        editor.apply();
    }
}