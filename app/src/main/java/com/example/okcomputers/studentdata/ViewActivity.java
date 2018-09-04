package com.example.okcomputers.studentdata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity {

    TextView txtview;
    SqlitedbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        txtview = (TextView)findViewById(R.id.result);
        db = new SqlitedbHelper(this);
        String data = db.getData();
        txtview.setText(data);

    }
}
