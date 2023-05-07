package com.techflix.puevents;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class EventRegistrationActivity extends AppCompatActivity {

    TextView eventName;
    Button attendeeSubmitBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_registration);

        eventName = (TextView) findViewById(R.id.attendee_event_name);
        eventName.setText(getIntent().getStringExtra("event-name"));
        attendeeSubmitBtn = findViewById(R.id.attendee_submit);

        attendeeSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EventRegistrationActivity.this, "Your Response recorded...", Toast.LENGTH_LONG).show();
            }
        });
    }
}