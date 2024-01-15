package com.example.tutorial3android;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tutorial3android.data.Income;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.text.SimpleDateFormat;

public class IncomeAdapter extends RecyclerView.Adapter<IncomeAdapter.IncomeViewHolder> {

    private List<Income> incomeDataList = new ArrayList<>();

    public void setIncomeDataList(List<Income> incomeDataList) {
        this.incomeDataList = incomeDataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public IncomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
        return new IncomeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull IncomeViewHolder holder, int position) {
        Income incomeData = incomeDataList.get(position);
        holder.bind(incomeData);
    }

    @Override
    public int getItemCount() {
        return incomeDataList.size();
    }

    static class IncomeViewHolder extends RecyclerView.ViewHolder {

        private final TextView amountTextView;
        private final TextView timeTextView;

        public IncomeViewHolder(@NonNull View itemView) {
            super(itemView);
            amountTextView = itemView.findViewById(R.id.messageTextView);
            timeTextView = itemView.findViewById(R.id.dateTextView);
        }

        public void bind(Income Income) {
            amountTextView.setText(String.format(Locale.getDefault(), "$%.2f", Income.getTotal()));

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
            String formattedTime = dateFormat.format(Income.getTime());
            timeTextView.setText(formattedTime);
        }
    }
}
