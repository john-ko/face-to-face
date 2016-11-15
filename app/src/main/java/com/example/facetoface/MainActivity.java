package com.example.facetoface;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent(this, Main2Activity.class);
        String message = "Bye";
        intent.putExtra("MESSAGE", message);
        startActivity(intent);
    }

    public void loadCamera(View view) {
        Bundle mBundle = new Bundle();
        Intent intent = new Intent(this, FaceTrackerActivity.class);
        intent.putExtras(mBundle);
        String message = "hello";
        intent.putExtra("MESSAGE", message);

        startActivity(intent);
    }

    public void loadAnalytics(View view) {
        System.out.println("test");
        Intent intent = new Intent(this, AnalyticActivity.class);
        String message = "Bye";
        intent.putExtra("MESSAGE", message);
        startActivity(intent);
    }
}
