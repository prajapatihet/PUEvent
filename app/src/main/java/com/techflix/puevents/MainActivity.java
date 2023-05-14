package com.techflix.puevents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView eventRV;
    private ArrayList<EventsModel> eventsModelArrayList;
    private EventsAdapter eventsAdapter;
    private FirebaseFirestore db;
    ProgressBar loadingPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eventRV = findViewById(R.id.idRVEvents);
        loadingPB = findViewById(R.id.idProgressBar);

        db = FirebaseFirestore.getInstance();

        eventsModelArrayList = new ArrayList<>();
        eventRV.setHasFixedSize(true);
        eventRV.setLayoutManager(new LinearLayoutManager(this));

        eventsAdapter = new EventsAdapter(this,eventsModelArrayList);

        eventRV.setAdapter(eventsAdapter);

        db.collection("Events").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        if (!queryDocumentSnapshots.isEmpty()){
                            loadingPB.setVisibility(View.GONE);
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list){
                                EventsModel e = d.toObject(EventsModel.class);
                                eventsModelArrayList.add(e);
                            }
                            eventsAdapter.notifyDataSetChanged();
                        }else {
                            Toast.makeText(MainActivity.this, "No data Found", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Fail to get data", Toast.LENGTH_SHORT).show();
                    }
                });


//        ArrayList<EventsModel> eventsModelArrayList = new ArrayList<EventsModel>();
//        eventsModelArrayList.add(new EventsModel(R.drawable.event1,"How to Build Products in Hackathon","In Shreyan Mehta's speaker session, participants will learn about how to build products in a Hackathon. The session will cover the process of idea generation, rapid prototyping, and testing within the limited timeframe of a hackathon event. Participants will also learn about the importance of teamwork, effective communication, and time management when building products in a hackathon. The session will include real-life examples and tips on how to succeed in hackathons. By the end of the session, attendees will have a better understanding of the process of building products in a hackathon and how to effectively participate in such events.","AnyOne can Participate","PU","Sat ,Dec 3,2023, 10:00 AM","Virtual"));
//        eventsModelArrayList.add(new EventsModel(R.drawable.event2,"Building innovative solutions using APIs","In Ali Mustufa's speaker session, participants will learn about building innovative solutions using APIs (Application Programming Interfaces). The session will cover the basics of APIs, including how to use them to access data and functionality from third-party services. Participants will also learn about the benefits of using APIs, such as increased efficiency and scalability. The session will include examples of how APIs can be used to build innovative solutions across a range of industries. By the end of the session, attendees will have a better understanding of how to leverage APIs to create innovative solutions.","Only 3rd year Student can participate","PIT","Mon ,May 7,2023, 13:00 PM","On-Site"));
//        eventsModelArrayList.add(new EventsModel(R.drawable.event3,"Git, GitHub and Open Source","Vraj Desai's speaker session will introduce Git, Github, and Open Source, explaining their importance in software development. Participants will learn how to use Git for collaboration and code management, and how to contribute to open source projects on Github. The session will also cover the benefits of open source development, including knowledge-sharing and global collaboration.","Anyone can Participate","Parul University","Sat ,Dec 3,2023, 10:00 AM","Virtual"));
//        eventsModelArrayList.add(new EventsModel(R.drawable.event1,"How to Build Products in Hackathon","In Shreyan Mehta's speaker session, participants will learn about how to build products in a Hackathon. The session will cover the process of idea generation, rapid prototyping, and testing within the limited timeframe of a hackathon event. Participants will also learn about the importance of teamwork, effective communication, and time management when building products in a hackathon. The session will include real-life examples and tips on how to succeed in hackathons. By the end of the session, attendees will have a better understanding of the process of building products in a hackathon and how to effectively participate in such events.","AnyOne can Participate","PIET","Fri ,Nov 10,2023, 11:00 AM","Hybrid"));
//        eventsModelArrayList.add(new EventsModel(R.drawable.event2,"Building innovative solutions using APIs","In Ali Mustufa's speaker session, participants will learn about building innovative solutions using APIs (Application Programming Interfaces). The session will cover the basics of APIs, including how to use them to access data and functionality from third-party services. Participants will also learn about the benefits of using APIs, such as increased efficiency and scalability. The session will include examples of how APIs can be used to build innovative solutions across a range of industries. By the end of the session, attendees will have a better understanding of how to leverage APIs to create innovative solutions.","Only 3rd year Student can participate","PIAS","Tue ,Dec 3,2023, 12:00 PM","Virtual"));
//        eventsModelArrayList.add(new EventsModel(R.drawable.event3,"Git, GitHub and Open Source","Vraj Desai's speaker session will introduce Git, Github, and Open Source, explaining their importance in software development. Participants will learn how to use Git for collaboration and code management, and how to contribute to open source projects on Github. The session will also cover the benefits of open source development, including knowledge-sharing and global collaboration.","Anyone can participate","Home","Wed ,APR 6,2023, 15:00 PM","On-Site"));
//
//        EventsAdapter eventsAdapter = new EventsAdapter(this,eventsModelArrayList);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
//        eventRV.setLayoutManager(linearLayoutManager);
//        eventRV.setAdapter(eventsAdapter);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_admin_login:
                startActivity(new Intent(MainActivity.this,AdminLoginActivity.class));
                Toast.makeText(this, "Redirecting...", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}