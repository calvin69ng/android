package com.example.tutorial3android;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GameNamePriceAdapter extends RecyclerView.Adapter<GameNamePriceAdapter.GameNamePriceViewHolder> {

    private List<game_data> gameDataList;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public GameNamePriceAdapter(List<game_data> gameDataList, OnItemClickListener onItemClickListener) {
        this.gameDataList = new ArrayList<>(gameDataList);
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public GameNamePriceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_game, parent, false);
        return new GameNamePriceViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GameNamePriceViewHolder holder, int position) {
        game_data gameData = gameDataList.get(position);
        holder.gameNumberTextView.setText(String.valueOf(position + 1));
        holder.gameNameTextView.setText(gameData.getName());
        holder.gamePriceTextView.setText(String.valueOf(gameData.getPrice()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return gameDataList.size();
    }

    public void updateList(List<game_data> newList) {
        if (newList != null) {
            int oldSize = gameDataList.size();

            if (!newList.isEmpty()) {
                gameDataList.clear();
                gameDataList.addAll(newList);
            }

            int newSize = gameDataList.size();
            if (oldSize > newSize) {
                notifyItemRangeRemoved(newSize, oldSize - newSize);
            } else if (oldSize < newSize) {
                notifyItemRangeInserted(oldSize, newSize - oldSize);
            }
        }
    }

    public static class GameNamePriceViewHolder extends RecyclerView.ViewHolder {
        TextView gameNumberTextView;
        TextView gameNameTextView;
        TextView gamePriceTextView;
        ImageButton deleteButton;

        public GameNamePriceViewHolder(@NonNull View itemView) {
            super(itemView);
            gameNumberTextView = itemView.findViewById(R.id.gameNumberTextView);
            gameNameTextView = itemView.findViewById(R.id.gameNameTextView);
            gamePriceTextView = itemView.findViewById(R.id.gamePriceTextView);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }
}
