package com.ilagan.instructor.courselogin;

/**
 * Created by Miggy on 9/8/2014.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import static java.lang.Thread.sleep;

public class Splash extends Activity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Thread for splash
        Thread splash_screen = new Thread(){
        public void run(){
            try{
                sleep(2000);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
                }
            }
        };
            splash_screen.start();
    }
}
