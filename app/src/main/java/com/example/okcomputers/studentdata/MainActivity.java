package com.example.okcomputers.studentdata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button gotosecondActivity = (Button)findViewById(R.id.Click_here);

        gotosecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go =new Intent(MainActivity.this,SecondActivity.class);
                startActivity(go);
            }
        });
    }
}
