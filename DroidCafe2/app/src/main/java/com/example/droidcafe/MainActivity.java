package com.example.droidcafe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.droidcafe.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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

        mainBinding.imageDonut.setOnClickListener(this);
        mainBinding.textDonut.setOnClickListener(this);

        mainBinding.imageIceCream.setOnClickListener(this);
        mainBinding.textIceCream.setOnClickListener(this);

        mainBinding.imageFroyo.setOnClickListener(this);
        mainBinding.textFroyo.setOnClickListener(this);

        mainBinding.floatingActionButton.setOnClickListener(this);
    }

    void displayToast(String dessert) {
        Toast.makeText(getApplicationContext(), "You ordered a " + dessert, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.imageDonut || id == R.id.textDonut) {
            dessert = "Donut";
            displayToast(dessert);
        } else if (id == R.id.imageIceCream || id == R.id.textIceCream) {
            dessert = "Ice-cream";
            displayToast(dessert);
        } else if (id == R.id.imageFroyo || id == R.id.textFroyo) {
            dessert = "FroYo";
            displayToast(dessert);
        } else if (id == R.id.floatingActionButton) {
            if (dessert.equals("default")) {
                Toast.makeText(getApplicationContext(), "Select a dessert.", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(this, OrderActivity.class);
                intent.putExtra(INTENT_DESSERT_KEY, dessert);
                startActivity(intent);
            }
        }
    }
}