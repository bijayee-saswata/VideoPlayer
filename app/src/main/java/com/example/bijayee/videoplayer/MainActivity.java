package com.example.bijayee.videoplayer;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    ProgressDialog mdialog;
    VideoView videoView;
    ImageButton btn;

    String url= "https://www.youtube.com/watch?v=_KYqDCJii0c";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.videoView);
        btn = findViewById(R.id.btn);

    }

    public void action(View view) {
        mdialog = new ProgressDialog(MainActivity.this);
        mdialog.setMessage("Please Wait...");
        mdialog.setCanceledOnTouchOutside(false);
        mdialog.show();

        try {
            if (!videoView.isPlaying()) {
                Uri uri = Uri.parse(url);
                videoView.setVideoURI(uri);
                videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        btn.setImageResource(R.drawable.ic_pause);
                    }
                });
            }
            else
            {
                videoView.pause();
                btn.setImageResource(R.drawable.ic_play);
            }
        }
        catch (Exception ex)
        {

        }
        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mdialog.dismiss();
                mp.setLooping(true);
                videoView.start();
                btn.setImageResource(R.drawable.ic_pause);
            }
        });
    }
}
