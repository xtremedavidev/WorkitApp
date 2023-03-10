package com.example.workit;

import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class dayAdapter extends RecyclerView.Adapter<dayAdapter.ViewHolder>  {

    private dayexerciseList dayexerciseList;
    private ArrayList<Integer> days;

    public dayAdapter() {
        days = new ArrayList<>();


        for (int i = 1; i <= 30; i++){

           days.add(i);
       }


    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.days_card_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


            String daytxt = String.valueOf((days.get(position)));

            SharedPrefManager sharedPrefManager = SharedPrefManager.getInstance(holder.itemView.getContext());

            holder.setDaytxt("Day " + daytxt);
            holder.setDatecompleted(sharedPrefManager.getDateCompleted(days.get(position)));
            holder.setiscompleted(sharedPrefManager.isDayCompleted(days.get(position)));




    }

    @Override
    public int getItemCount() {
        return days.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView daytxt, daymin, datecompleted;
        private CardView cardView;
        private  dayexerciseList dayexerciseList;
        private ImageView imageView;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.card_day);
            daytxt = itemView.findViewById(R.id.dayname);
            daymin = itemView.findViewById(R.id.day_time);
            datecompleted = itemView.findViewById(R.id.day_date);
            imageView = itemView.findViewById(R.id.checkedimg);

            dayexerciseList = new dayexerciseList();


//            cardView.setOnClickListener(v ->{
//
//
//
//                Intent i = new Intent(itemView.getContext(), MainActivity.class);
//
//                i.putExtra("excerciselist", dayexerciseList);
//
//                itemView.getContext().startActivity(i);
//
//            });




        }



        public void setDaytxt(String daytxt1) {

            daytxt.setText(daytxt1);
            this.daytxt = daytxt;
        }

        public void setDaymin(String daymin1) {
            daymin.setText(daymin1);
            this.daymin = daymin;
        }

        public void setiscompleted(boolean completed){

            if (completed){
                imageView.setBackgroundResource(R.drawable.ic_baseline_check_circle_24);


            }else {
                imageView.setBackgroundResource(R.drawable.ic_baseline_do_not_disturb_alt_24);

            }

        }
        public void setDatecompleted(String datecompleted1) {
            datecompleted.setText(datecompleted1);
            this.datecompleted = datecompleted;
        }

    }
}
