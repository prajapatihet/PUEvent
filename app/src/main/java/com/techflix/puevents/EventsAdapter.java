package com.techflix.puevents;

import android.content.Context;
import android.content.Intent;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.Viewholder> {

    private final Context context;
    private final ArrayList<EventsModel> eventsModelArrayList;

    public EventsAdapter(Context context, ArrayList<EventsModel> eventsModelArrayList) {
        this.context = context;
        this.eventsModelArrayList = eventsModelArrayList;
    }

    @NonNull
    @Override
    public EventsAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsAdapter.Viewholder holder, int position) {
        EventsModel model = eventsModelArrayList.get(position);
        holder.eve_image.setImageResource(model.getEvent_image());
        holder.eve_name.setText(model.getEvent_name());
        holder.eve_des.setText(model.getEvent_description());
        holder.eve_eli.setText(model.getEvent_eligibility());
        holder.eve_mode.setText(model.getEvent_mode());
        holder.eve_location.setText(model.getEvent_location());
        holder.eve_date_time.setText(model.getEvent_date_time());
        holder.arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.hiddenLayout.getVisibility() == View.VISIBLE){
                    // TransitionManager.beginDelayedTransition(holder.cardView,new AutoTransition());
                    holder.hiddenLayout.setVisibility(View.GONE);
                    holder.arrow.setImageResource(R.drawable.arrow_down);
                }else {
                    // TransitionManager.beginDelayedTransition(holder.cardView, new AutoTransition());
                    holder.hiddenLayout.setVisibility(View.VISIBLE);
                    holder.arrow.setImageResource(R.drawable.arrow_up);
                }
            }
        });
        holder.rsvpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Clicked RSVP", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(context,EventRegistrationActivity.class);
                i.putExtra("event-name",model.getEvent_name());
                context.startActivity(i);
            }
        });

        holder.whatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
                whatsappIntent.setPackage("com.whatsapp");
                whatsappIntent.putExtra(Intent.EXTRA_TEXT,"*_Name of Event:_* \n"+model.getEvent_name()+"\n"+"*_Event Description:_* \n"+model.getEvent_description()+"\n"+"*_Event Eligibility:_* \n"+model.getEvent_eligibility()+"\n"+"*_Event Date and Time:_* \n"+model.getEvent_date_time()+"\n"+"*_Mode of Event:_* \n"+model.getEvent_mode()+"\n"+"*_Location:_* \n"+model.getEvent_location());
                try {
                    context.startActivity(whatsappIntent);
                }catch (android.content.ActivityNotFoundException ex){
                    Toast.makeText(context, "No Activity to handle", Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventsModelArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        private final ImageView eve_image;
        private final TextView eve_name;
        private final TextView eve_des;
        private final TextView eve_eli;
        private final ImageButton arrow;
        private final CardView cardView;
        private final LinearLayout hiddenLayout;
        private final Button rsvpBtn;
        private final TextView eve_mode;
        private final TextView eve_location;
        private final TextView eve_date_time;
        private final ImageView whatsapp;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            eve_image = itemView.findViewById(R.id.eventImage);
            eve_name = itemView.findViewById(R.id.event_name);
            eve_des = itemView.findViewById(R.id.event_description);
            eve_eli = itemView.findViewById(R.id.event_eligibility);
            arrow = itemView.findViewById(R.id.arrow_button);
            cardView = itemView.findViewById(R.id.base_cardview);
            hiddenLayout = itemView.findViewById(R.id.hidden_view);
            rsvpBtn = itemView.findViewById(R.id.rsvpBtn);
            eve_mode = itemView.findViewById(R.id.event_mode);
            eve_location = itemView.findViewById(R.id.event_location);
            eve_date_time = itemView.findViewById(R.id.event_date_time);
            whatsapp = itemView.findViewById(R.id.whatsappShare);
        }
    }
}
