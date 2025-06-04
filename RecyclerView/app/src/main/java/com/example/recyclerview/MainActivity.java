package com.example.recyclerview;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.recyclerview.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ActivityMainBinding mainBinding;

    private ArrayList<String> wordList = new ArrayList<>();

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

        for (int i = 1; i <= 20; i++) {
            wordList.add("Word " + i);
        }

        WordAdapter adapter = new WordAdapter(this, wordList);
        mainBinding.recyclerView.setAdapter(adapter);
        mainBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mainBinding.addButton.setOnClickListener(view -> {
            int size = wordList.size();
            wordList.add("Word " + (size+1));
            adapter.notifyItemInserted(wordList.size());
            mainBinding.recyclerView.smoothScrollToPosition(wordList.size());
        });
    }
}