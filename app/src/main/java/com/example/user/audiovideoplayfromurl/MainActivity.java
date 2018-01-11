package com.example.user.audiovideoplayfromurl;

import android.app.ProgressDialog;
import android.media.AsyncPlayer;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    String AudioURL = "https://www.android-examples.com/wp-content/uploads/2016/04/Thunder-rumble.mp3";
    MediaPlayer mediaplayer;
    //2nd way
    AsyncPlayer   mAsync = null;
    private static final String TAG = "AsyncPlayerDemo";

    Button btnStartMusic;
    Button btnStopMusic;

    String VideoURL = "http://www.androidbegin.com/tutorial/AndroidCommercial.3gp";
    Button btnStartVideo;
    Button btnStopVideo;
    VideoView videoView;
    ProgressDialog pDialog;

    MediaController mc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initAudio();

        initVideo();

    }

    private void initVideo() {
        videoView = findViewById(R.id.videoView);

        pDialog = new ProgressDialog(this);
        // Set progressbar title
        pDialog.setTitle("Android Video Streaming Tutorial");
        // Set progressbar message
        pDialog.setMessage("Buffering...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(false);
        // Show progressbar
        pDialog.show();

        try {
            // Start the MediaController
            MediaController mediacontroller = new MediaController(this);
            mediacontroller.setAnchorView(videoView);
            // Get the URL from String VideoURL
            Uri video = Uri.parse(VideoURL);
            videoView.setMediaController(mediacontroller);
            videoView.setVideoURI(video);

        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }

        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            // Close the progress bar and play the video
            public void onPrepared(MediaPlayer mp) {
                pDialog.dismiss();
                videoView.start();
            }
        });
    }

    private void initAudio() {
        btnStartMusic = findViewById(R.id.btnStartMusic);
        btnStopMusic = findViewById(R.id.btnStopMusic);

        btnStopMusic.setEnabled(false);

        mediaplayer = new MediaPlayer();
        mediaplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        //mAsync = new AsyncPlayer(TAG);

    }

    public void playMusic(View view) {
        try {
            mediaplayer.setDataSource(AudioURL);
            mediaplayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mediaplayer.start();
        btnStartMusic.setEnabled(false);
        btnStopMusic.setEnabled(true);


        //mAsync.play(this, Uri.parse(AudioURL),false,AudioManager.STREAM_MUSIC);
    }

    public void stopMusic(View view) {
        //mAsync.stop();
        mediaplayer.stop();
        btnStartMusic.setEnabled(true);
        btnStopMusic.setEnabled(false );

    }


}
