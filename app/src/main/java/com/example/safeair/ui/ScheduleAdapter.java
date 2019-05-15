package com.example.safeair.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.safeair.R;
import com.example.safeair.data.model.ScheduleObject;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleAdapterViewHolder> {

    //List object that holds schedules
    private List<ScheduleObject> mFlights;

    //Create an instance of the click handling interface
    private final ItemClickListener mItemClickListener;

    //Interface for click handling
    public interface ItemClickListener {
        void onItemClickListener(ScheduleObject scheduleObject);
    }

    //Class constructor that creates a ScheduleAdapter
    public ScheduleAdapter(List<ScheduleObject> flightObjects, ItemClickListener itemClickListener) {
        mFlights = flightObjects;
        mItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ScheduleAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        int layout = R.layout.list_item_flight_schedule;
        View view = LayoutInflater.from(context).inflate(layout, viewGroup, false);
        return new ScheduleAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleAdapterViewHolder scheduleAdapterViewHolder, int i) {
        ScheduleObject scheduleObject = mFlights.get(i);
        scheduleAdapterViewHolder.duration.setText(scheduleObject.getTotalJourney().getDuration());
        scheduleAdapterViewHolder.originAirport.setText(scheduleObject.getFlights().get(i)
                .getDepartureFlight().getAirportCode());
        scheduleAdapterViewHolder.departureTime.setText(scheduleObject.getFlights().get(i)
                .getDepartureFlight().getScheduledTimeLocal().getDateTime().substring(11));
        scheduleAdapterViewHolder.departureDate.setText(scheduleObject.getFlights().get(i)
                .getDepartureFlight().getScheduledTimeLocal().getDateTime().substring(0, 11));
        scheduleAdapterViewHolder.arrivalAirport.setText(scheduleObject.getFlights().get(i)
                .getArrivalFlight().getAirportCode());
        scheduleAdapterViewHolder.arrivalTime.setText(scheduleObject.getFlights().get(i)
                .getArrivalFlight().getScheduledTimeLocal().getDateTime().substring(11));
        scheduleAdapterViewHolder.arrivalDate.setText(scheduleObject.getFlights().get(i)
                .getArrivalFlight().getScheduledTimeLocal().getDateTime().substring(0, 11));
    }

    @Override
    public int getItemCount() {
        if (null == mFlights) return 0;
        return mFlights.size();
    }

    /**
     * When data changes, this method updates the list of flight schedules
     * and notifies the adapter to use the new values on it
     */
    public void setFlights(List<ScheduleObject> scheduleObjects) {
        mFlights = scheduleObjects;
        notifyDataSetChanged();
    }

    //ViewHolder class
    class ScheduleAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //UI elements
        TextView duration;
        TextView originAirport;
        TextView departureTime;
        TextView departureDate;
        TextView arrivalAirport;
        TextView arrivalTime;
        TextView arrivalDate;

        //ViewHolder default constructor
        ScheduleAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            //Bind views
            duration = itemView.findViewById(R.id.tv_journey_duration);
            originAirport = itemView.findViewById(R.id.tv_originAirport);
            departureTime = itemView.findViewById(R.id.tv_departure_time);
            departureDate = itemView.findViewById(R.id.tv_departureDate);
            arrivalAirport = itemView.findViewById(R.id.tv_arrivalAirport);
            arrivalTime = itemView.findViewById(R.id.tv_arrivalTime);
            arrivalDate = itemView.findViewById(R.id.tv_arrivalDate);
            //Set click listener on arrivalDate button
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            mItemClickListener.onItemClickListener(mFlights.get(position));
        }


    }
}
