package com.example.okcomputers.studentdata;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {


    EditText firstname,secondname,editid;
    Button insertbtn,searchbtn,updatebtn,deletebtn,viewbtn;
    boolean fine = true;
    SqlitedbHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        firstname = (EditText)findViewById(R.id.FirstName);
        secondname = (EditText)findViewById(R.id.SecondName);
        editid = (EditText)findViewById(R.id.editId);

        insertbtn =(Button) findViewById(R.id.insert_Btn);
        searchbtn =(Button)  findViewById(R.id.Search_btn);
        updatebtn =(Button)  findViewById(R.id.Update_btn);
        deletebtn =(Button)  findViewById(R.id.delte_btn);
        viewbtn = (Button) findViewById(R.id.View_btn);
        db= new SqlitedbHelper(this);

        insertbtn.setOnClickListener(this);
        viewbtn.setOnClickListener(this);
        searchbtn.setOnClickListener(this);
        deletebtn.setOnClickListener(this);
        updatebtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
                case R.id.insert_Btn:

                    try {
                        String fname = firstname.getText().toString();
                        String lname = secondname.getText().toString();
                        db.InsertData(fname, lname);
                        firstname.setText("");
                        secondname.setText("");

                    }
                    catch (Exception e)
                    {
                        fine = false;
                        Toast.makeText(getApplicationContext(),"Error inserting data",Toast.LENGTH_LONG).show();

                    }
                    finally {
                        if (fine) {
                            Toast.makeText(getApplicationContext()," inserted data Successfully",Toast.LENGTH_LONG).show();
                        }
                    }

            break;
            case R.id.View_btn:
            Intent i = new Intent(SecondActivity.this,ViewActivity.class);
            startActivity(i);
            break;

            case R.id.Search_btn:
                try {
                     String id = editid.getText().toString();
                     long l = Long.parseLong(id);
                     String fsname = db.getStudentFirstName(l);
                     String lstname = db.getStudentLastName(l);
                     firstname.setText(fsname);
                     secondname.setText(lstname);
                }
                catch (Exception e)
                {
                    fine = false;
                    Toast.makeText(getApplicationContext(),"Error Searching data",Toast.LENGTH_LONG).show();
                }
                finally {
                    if (fine) {
                        Toast.makeText(getApplicationContext()," Searched data Successfully",Toast.LENGTH_LONG).show();
                    }
                }
                break;

            case R.id.Update_btn:
                try {
                    String fname = firstname.getText().toString();
                    String lname = secondname.getText().toString();
                    String id = editid.getText().toString();
                    long l = Long.parseLong(id);
                    db.updateStudent(l,fname,lname);
                }
                catch (Exception e)
                {
                    fine = false;
                    Toast.makeText(getApplicationContext(),"Error Updating data",Toast.LENGTH_LONG).show();
                }
                finally {
                    if (fine) {
                        Toast.makeText(getApplicationContext()," Updated data Successfully",Toast.LENGTH_LONG).show();
                    }
                }
                break;
            case R.id.delte_btn:
                try {
                    String id = editid.getText().toString();
                    long l = Long.parseLong(id);
                    db.DeleteStudent(l);

                }
                catch (Exception e)
                {
                    String error = e.toString();
                    Dialog d = new Dialog(this);
                    d.setTitle("Error detected in inserting database");
                    TextView tv = new TextView(this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();
                }
                finally {
                    if (fine) {
                        Dialog d = new Dialog(this);
                        d.setTitle("Successfully done");
                        TextView tv = new TextView(this);
                        tv.setText("Data Searched Successfully");
                        d.setContentView(tv);
                        d.show();
                    }
                }
                break;
        }

    }
}
