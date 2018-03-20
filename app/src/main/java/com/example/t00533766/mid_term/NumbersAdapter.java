package com.example.t00533766.mid_term;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by T00533766 on 3/19/2018.
 */

public class NumbersAdapter extends RecyclerView.Adapter {

    private ArrayList<NumbersData> arrayList;
    private Context context;


    public NumbersAdapter(ArrayList arrayList, Context context){
        this.arrayList = arrayList;
        this.context = context;
    }


    public void setArrayList(ArrayList arrayList) {
        this.arrayList = arrayList;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);

        return new NumberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        NumberViewHolder numberViewHolder = (NumberViewHolder) holder;
        numberViewHolder.bind(position);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    class NumberViewHolder extends RecyclerView.ViewHolder {

        private TextView numberTextView;
        private TextView numberDataTextView;
        private TextView idTextView;

        public NumberViewHolder(View itemView) {
            super(itemView);

            numberTextView = itemView.findViewById(R.id.number);
            numberDataTextView = itemView.findViewById(R.id.number_text);
            idTextView = itemView.findViewById(R.id.number_id);
        }

        public void bind(int pos) {
            numberTextView.setText(arrayList.get(pos).getNumber());
            numberDataTextView.setText(arrayList.get(pos).getText());
            idTextView.setText(arrayList.get(pos).getNumber_id() + 1 + "");

        }

    }


}
