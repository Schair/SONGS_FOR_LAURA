package com.example.asmr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class PlayerActivity extends AppCompatActivity {

    Button playBtn;
    SeekBar songBar, volumeBar;
    TextView timePlayed, timeRemaining;
    MediaPlayer mediaPlayer;
    int totalTime, audioSelected;

    int audioArray[] = {R.raw.no_canto_yo, R.raw.pope_is_a_rockstar, R.raw.rain, R.raw.achilles_come_down,
            R.raw.this_side_of_paradise, R.raw.oh_klahoma, R.raw.karma, R.raw.death_of_a_bachelor,
            R.raw.el_gallo_de_moron, R.raw.mechteria, R.raw.kawaki_wo_ameku, R.raw.id_love, R.raw.licht_und_schatten,
            R.raw.the_girl_who_fell_from_the_sky };


    ImageView imageView;
    TextView title, subtitle;

    String data1, data2;
    int img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        imageView = findViewById(R.id.image);
        title = findViewById(R.id.audioTitle);
        subtitle = findViewById(R.id.audioSubtitle);
        title.setTextColor(Color.BLACK);
        subtitle.setTextColor(Color.BLACK);

        getData();
        setData();

        playBtn = (Button) findViewById(R.id.playBtn);
        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressPlayBtn();
            }
        });

        timePlayed = (TextView) findViewById(R.id.timePlayed);
        timeRemaining = (TextView) findViewById(R.id.timeRemaining);

        // Media player things
        mediaPlayer = MediaPlayer.create(this, audioSelected);
        mediaPlayer.setLooping(false);
        mediaPlayer.seekTo(0);
        mediaPlayer.setVolume(0.5F, 100.0F);
        totalTime = mediaPlayer.getDuration();

        // Song bar things
        songBar = (SeekBar) findViewById(R.id.songBar);
        songBar.setMax(totalTime);
        songBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser){
                    mediaPlayer.seekTo(progress);
                    songBar.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mediaPlayer != null){
                    try {
                        Message msg = new Message();
                        msg.what = mediaPlayer.getCurrentPosition();
                        handler.sendMessage(msg);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {}
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int currentPosition = msg.what;
            songBar.setProgress(currentPosition);

            String timePlayedString = createTimeLabel(currentPosition);
            timePlayed.setText(timePlayedString);

            String timeRemainingString = createTimeLabel(totalTime - currentPosition);
            timeRemaining.setText("-"+timeRemainingString);
        }
    };

    private void getData(){
        if (getIntent().hasExtra("data1") && getIntent().hasExtra("data2")){
            data1 = getIntent().getStringExtra("data1");
            data2 = getIntent().getStringExtra("data2");
            audioSelected = audioArray[getIntent().getIntExtra("pos", 0)];

        }
        else{
            Toast.makeText(this, "No se ha encontrado el audio", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData(){
        title.setText(data1);
        subtitle.setText(data2);
    }

    public String createTimeLabel(int time){
        String timeLabel = "";
        int min = time / 1000 / 60;
        int sec = time / 1000 % 60;
        timeLabel = min + ":";
        if (sec < 10) timeLabel += "0";
        timeLabel += sec;

        return timeLabel;
    }


    public void pressPlayBtn(){
        if (!mediaPlayer.isPlaying()){
            // Pause the song
            mediaPlayer.start();
            playBtn.setBackgroundResource(R.drawable.stop);
        }
        else{
            mediaPlayer.pause();
            playBtn.setBackgroundResource(R.drawable.play);
        }
    }

    // That's all folks ^-^
}