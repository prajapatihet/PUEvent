package com.techflix.puevents;

import android.widget.Button;
import android.widget.TextView;

public class EventsModel {
    private int event_image;
    private String event_name;
    private String event_description;
    private String event_eligibility;
    private Button event_rsvp;
    private String event_location;
    private String event_date_time;
    private String event_mode;

    public String getEvent_location() {
        return event_location;
    }

    public void setEvent_location(String event_location) {
        this.event_location = event_location;
    }

    public String getEvent_date_time() {
        return event_date_time;
    }

    public void setEvent_date_time(String event_date_time) {
        this.event_date_time = event_date_time;
    }

    public EventsModel(int event_image, String event_name, String event_description, String event_eligibility, String event_location, String event_date_time, String event_mode) {
        this.event_image = event_image;
        this.event_name = event_name;
        this.event_description = event_description;
        this.event_eligibility = event_eligibility;
        this.event_location = event_location;
        this.event_date_time = event_date_time;
        this.event_mode = event_mode;
    }

    public String getEvent_mode() {
        return event_mode;
    }

    public void setEvent_mode(String event_mode) {
        this.event_mode = event_mode;
    }

    public int getEvent_image() {
        return event_image;
    }

    public void setEvent_image(int event_image) {
        this.event_image = event_image;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_description() {
        return event_description;
    }

    public void setEvent_description(String event_description) {
        this.event_description = event_description;
    }

    public String getEvent_eligibility() {
        return event_eligibility;
    }

    public void setEvent_eligibility(String event_eligibility) {
        this.event_eligibility = event_eligibility;
    }

    public Button getEvent_rsvp() {
        return event_rsvp;
    }

    public void setEvent_rsvp(Button event_rsvp) {
        this.event_rsvp = event_rsvp;
    }
}
