package com.example.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.databinding.WordItemBinding;

import java.util.ArrayList;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.WordViewHolder> {

    private Context context;
    private ArrayList<String> wordList;

    public WordAdapter(Context context, ArrayList<String> wordList) {
        this.context = context;
        this.wordList = wordList;
    }

    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        WordItemBinding itemBinding = WordItemBinding
                .inflate(LayoutInflater.from(context), parent, false);
        return new WordViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull WordViewHolder holder, int position) {
        String word = wordList.get(position);
        holder.itemBinding.wordTextview.setText(word);

        holder.itemBinding.wordTextview.setOnClickListener(view -> {
            wordList.set(position, "Clicked! " + word);
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return wordList.size();
    }

    public static class WordViewHolder extends RecyclerView.ViewHolder {

        private final WordItemBinding itemBinding;

        public WordViewHolder(WordItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.itemBinding = itemBinding;
        }
    }
}
