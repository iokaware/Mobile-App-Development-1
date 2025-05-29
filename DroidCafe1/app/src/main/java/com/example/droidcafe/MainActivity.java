package com.example.droidcafe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.droidcafe.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public static ActivityMainBinding mainBinding;

    public static final String INTENT_DESSERT_KEY = "dessert_key";

    public String dessert = "default";

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

        mainBinding.imageDonut.setOnClickListener(view -> {
            dessert = "Donut";
            displayToast(dessert);
        });
        mainBinding.textDonut.setOnClickListener(view ->
                mainBinding.imageDonut.performClick()
        );

        mainBinding.imageIceCream.setOnClickListener(view -> {
            dessert = "Ice-cream";
            displayToast(dessert);
        });
        mainBinding.textIceCream.setOnClickListener(view ->
                mainBinding.imageIceCream.performClick()
        );

        mainBinding.imageFroyo.setOnClickListener(view -> {
            dessert = "FroYo";
            displayToast(dessert);
        });
        mainBinding.textFroyo.setOnClickListener(view ->
                mainBinding.imageFroyo.performClick()
        );

        mainBinding.floatingActionButton.setOnClickListener(view -> {
            if (dessert.equals("default")) {
                Toast.makeText(getApplicationContext(), "Select a dessert.", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, OrderActivity.class);
                intent.putExtra(INTENT_DESSERT_KEY, dessert);
                startActivity(intent);
            }
        });
    }

    void displayToast(String dessert) {
        Toast.makeText(getApplicationContext(), "You ordered a " + dessert, Toast.LENGTH_SHORT).show();
    }
}