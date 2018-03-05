package edu.utep.cs.cs4330.timer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    String tag = "Lifecycle Step";
    private TextView timerDisplay;
    private Button startButton;
    private Button stopButton;
    private TimerModel timerModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(tag, "In the onCreate() event");

        // "hey main activity, here is the ui
        setContentView(R.layout.activity_main);

        timerDisplay = (TextView) findViewById(R.id.timerDisplay);
        startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener( this::startClicked);

        stopButton = (Button) findViewById((R.id.stopButton) );
        stopButton.setOnClickListener(this::stopClicked);

        timerModel = new TimerModel();
    }

    public void startClicked (View view)  {

        timerModel.start ();
        timerDisplay.setText ("00:00:00");

        new Thread ( () ->  {
            while (timerModel.isRunning() )  {
                // Explained near 49:00:00
                this.runOnUiThread (this::displayTime);
                try {
                    Thread.sleep (200);
                }
                catch (InterruptedException e)  {}
            }
        }).start();

        startButton.setEnabled (false);
        stopButton.setEnabled (true);
    }

    public void stopClicked (View view)  {

        //displayTime ();
        timerModel.stop ();
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
    }

    private void displayTime ()  {
        // Convert milliseconds to seconds
        long sec = timerModel.elapsedTime() / 1000;
        long min = sec / 60; sec %= 60;
        long hour = min / 60; min %= 60;
        // think of printf from C
        timerDisplay.setText(String.format("%02d:%02d:%02d", hour, min, sec));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        if (timerModel.isRunning()) {
            outState.putLong("Start Time", timerModel.startTime());
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        long startTime = savedInstanceState.getLong("Start Time", -1);
        if (startTime != -1){
            timerModel.setStartTime(startTime);
            new Thread ( () ->  {
                while (timerModel.isRunning() )  {
                    // Explained near 49:00:00
                    this.runOnUiThread (this::displayTime);
                    try {
                        Thread.sleep (200);
                    }
                    catch (InterruptedException e)  {}
                }
            }).start();

            startButton.setEnabled (false);
            stopButton.setEnabled (true);
        }
    }

    protected void onStart()  {

        super.onStart();
        Log.d(tag, "In the onStart() event");
    }

    protected void onRestart()  {

        super.onRestart();
        Log.d(tag, "In the onRestart() event");
    }

    protected void onResume()  {

        super.onResume();
        Log.d(tag, "In the onResume() event");
    }

    protected void onPause()  {

        super.onPause();
        Log.d(tag, "In the onPause() event");
    }

    protected void onStop()  {

        super.onStop();
        Log.d(tag, "In the onStop() event");
    }

    protected void onDestroy()  {

        super.onDestroy();
        Log.d(tag, "In the onDestroy() event");
    }
}