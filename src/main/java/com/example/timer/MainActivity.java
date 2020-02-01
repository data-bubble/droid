package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private SeekBar seekBar;
    private Button button;
    private boolean isPressed=false;
    private TextView textView;
    private CountDownTimer timer;
    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar=findViewById(R.id.seekBar);
        button=findViewById(R.id.button);
        textView=findViewById(R.id.textView);
        player=MediaPlayer.create(getApplicationContext(),R.raw.ding);

        seekBar.setProgress(60);
        textView.setText("00:60");
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                int minutes=progress/60;
                int seconds=progress-(minutes*60);
                textView.setText(minutes+":"+seconds);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void dodo(View view) {

          if(!isPressed) {
        //    textView.setText("00:60");
            seekBar.setEnabled(false);
            button.setText("Stop");
            isPressed = true;

                timer = new CountDownTimer((seekBar.getProgress()+1) * 1000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        setMillis(millisUntilFinished);


                    }

                    @Override
                    public void onFinish() {
                        player.start();

                            timer.cancel();
                        setMillis(seekBar.getProgress()*1000);

                    }

                }.start();


        }

        else{
            seekBar.setEnabled(true
            );
            setMillis(seekBar.getProgress()*1000);
            button.setText("Start");
            timer.cancel();

            isPressed=false;


        }



    }
    private void setMillis(long millis){
        int minutes = (int) millis/ 1000 / 60;
        int seconds = (int) millis / 1000 - (minutes * 60);


        textView.setText(minutes + ":" + seconds);
    }
}
