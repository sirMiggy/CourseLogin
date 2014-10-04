package com.ilagan.instructor.courselogin;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Date;
import java.text.DateFormat;


public class MainActivity extends Activity {


    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main);

        final DrawerLayout mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        Button navDrawButton = (Button) findViewById(R.id.navDrawer);
        navDrawButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });

        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        TextView timeDate_main = (TextView)findViewById(R.id.timeDate_main);
        timeDate_main.setText(currentDateTimeString);

        Spinner course_spinner = (Spinner) findViewById(R.id.course);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> course_adapter = ArrayAdapter.createFromResource(this,
                R.array.course_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        course_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        course_spinner.setAdapter(course_adapter);

        Spinner section_spinner = (Spinner) findViewById(R.id.section);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> section_adapter = ArrayAdapter.createFromResource(this,
                R.array.section_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        section_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        section_spinner.setAdapter(section_adapter);

       Thread myThread = null;

        Runnable runnable = new CountDownRunner();
        myThread= new Thread(runnable);
        myThread.start();
    }


    public void doWork() {
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    TextView txtCurrentTime = (TextView) findViewById(R.id.timeDate_main);
                    String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
                    Date dt = new Date();
                    long time = dt.getTime();
                    String timeSt = String.valueOf(time);
                    txtCurrentTime.setText(currentDateTimeString);
                } catch (Exception e) {
                }
            }
        });
    }



    class CountDownRunner implements Runnable{
        // @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()){
                try {
                    doWork();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }catch(Exception e){
                }
            }
        }
    }

    public final static String HEADEROUTPUT = "com.ilagan.instructor.courselogin.HEADEROUTPUT";

    public void attendanceView(View view){
        Intent intent = new Intent(this, Attendance.class);
        TextView timeDate = (TextView) findViewById(R.id.timeDate_main);
        Spinner courseSpinner = (Spinner) findViewById(R.id.course);
        Spinner sectionSpinner = (Spinner) findViewById(R.id.section);
        String courseSt = courseSpinner.getSelectedItem().toString();
        String sectionSt = sectionSpinner.getSelectedItem().toString();
        String timeDateSt  = timeDate.getText().toString();
        String headerString = "Student/s in "+courseSt+" of "+sectionSt+" at "+timeDateSt;
        intent.putExtra(HEADEROUTPUT, headerString);
        startActivity(intent);
    }

    public class SpinnerActivity extends Activity implements AdapterView.OnItemSelectedListener {

        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
            // An item was selected. You can retrieve the selected item using
            // parent.getItemAtPosition(pos)
            String st = parent.getItemAtPosition(pos).toString();
            Toast t = Toast.makeText(this,st,Toast.LENGTH_SHORT);
        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        int id = item.getItemId();
        if (id == R.id.action_add) {
            return true;
        }
        else if (id == R.id.action_settings){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
       // mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
