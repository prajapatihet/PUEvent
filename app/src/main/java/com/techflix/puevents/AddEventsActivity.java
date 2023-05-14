package com.techflix.puevents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class AddEventsActivity extends AppCompatActivity {
    ImageView eve_image;
    private EditText setEventNameEdt,setEventDetailsEdt,setEventParticipateEdt,setEventLocationEdt,setEventDateTimeEdt,setEventModeEdt;
    private Button setEventBtn;
    private String setEventName,setEventDetails,setEventParticipate,setEventLocation,setEventDateTime,setEventMode;

    private FirebaseFirestore db;

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_events);
        eve_image = findViewById(R.id.setEventImage);
        tv = findViewById(R.id.tv);
        TextPaint paint = tv.getPaint();
        float width = paint.measureText("");
        Shader textShader = new LinearGradient(0, 0, width, tv.getTextSize(),
                new int[]{
                        Color.parseColor("#F97C3C"),
                        Color.parseColor("#FDB54E"),
                        Color.parseColor("#64B678"),
                        Color.parseColor("#478AEA"),
                        Color.parseColor("#8446CC"),
                }, null, Shader.TileMode.CLAMP);
        tv.getPaint().setShader(textShader);
        eve_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i,3);
            }
        });


        // database

        db = FirebaseFirestore.getInstance();

        setEventNameEdt = findViewById(R.id.setEventName);
        setEventDetailsEdt = findViewById(R.id.setEventDetails);
        setEventParticipateEdt = findViewById(R.id.setEventParticipate);
        setEventLocationEdt = findViewById(R.id.setEventLocation);
        setEventDateTimeEdt = findViewById(R.id.setEventDateTime);
        setEventModeEdt = findViewById(R.id.setEventMode);

        setEventBtn = findViewById(R.id.setEventBtn);

        setEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEventName = setEventNameEdt.getText().toString();
                setEventDetails = setEventDetailsEdt.getText().toString();
                setEventParticipate = setEventParticipateEdt.getText().toString();
                setEventLocation = setEventLocationEdt.getText().toString();
                setEventDateTime = setEventDateTimeEdt.getText().toString();
                setEventMode = setEventModeEdt.getText().toString();


                if (TextUtils.isEmpty(setEventName)){
                    setEventNameEdt.setError("Enter");
                } else if (TextUtils.isEmpty(setEventDetails)) {
                    setEventDetailsEdt.setError("Enter");
                } else if (TextUtils.isEmpty(setEventParticipate)) {
                    setEventParticipateEdt.setError("Enter");
                } else if (TextUtils.isEmpty(setEventLocation)) {
                    setEventLocationEdt.setError("Enter");
                } else if (TextUtils.isEmpty(setEventDateTime)) {
                    setEventDateTimeEdt.setError("Enter");
                } else if (TextUtils.isEmpty(setEventMode)) {
                    setEventModeEdt.setError("Enter");
                }else {
                    addDataToFireStore(setEventName,setEventDetails,setEventParticipate,setEventLocation,setEventDateTime,setEventMode);
                }
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null){
            Uri selectedImage = data.getData();
            eve_image = findViewById(R.id.setEventImage);
            eve_image.setImageURI(selectedImage);
        }
    }

    private void addDataToFireStore(String setEventName, String setEventDetails,String setEventParticipate,String setEventLocation,String setEventDateTime,String setEventMode){
        CollectionReference reference = db.collection("Events");

        EventsModel events = new EventsModel(setEventName,setEventDetails,setEventParticipate,setEventLocation,setEventDateTime,setEventMode);
        reference.add(events).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(AddEventsActivity.this, "Event Added", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddEventsActivity.this, "Failed..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}