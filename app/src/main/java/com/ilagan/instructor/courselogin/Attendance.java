package com.ilagan.instructor.courselogin;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Miggy on 9/8/2014.
 */
public class Attendance extends ActionBarActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        // Get the message from the intent
        Intent intent = getIntent();
        String headerText = intent.getStringExtra(MainActivity.HEADEROUTPUT);

        // Create the text view
        TextView headerTextView = (TextView)findViewById(R.id.headerTextView);
        headerTextView.setText(headerText);

       // init();
    }

    public void init(){

        TableLayout attendanceTable = (TableLayout) findViewById(R.id.attendanceTableLayout);
        CheckBox checkBox;
        TextView tv;
        ImageButton addBtn;
        for (int i = 0; i <2; i++) {

            TableRow row= new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            checkBox = new CheckBox(this);
            tv = new TextView(this);
            addBtn = new ImageButton(this);
            addBtn.setImageResource(R.drawable.student_pic);
            addBtn.setMaxWidth(10);
            addBtn.setMaxHeight(10);
            checkBox.setText("Mark: ");
            tv.setText("Student "+i);
            row.addView(checkBox);
            row.addView(tv);
            row.addView(addBtn);
            attendanceTable.addView(row,i);
        }
    }
}
