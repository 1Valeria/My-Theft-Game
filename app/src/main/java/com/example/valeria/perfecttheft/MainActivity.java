package com.example.valeria.perfecttheft;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    MediaPlayer anthemSong;
    private static final long DOUBLE_PRESS_INTERVAL = 250; // in millis
    private long lastPressTime;

    private boolean mHasDoubleClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread t = new Thread(new Runnable() {
            public void run() {
/////////////// MUSIC /////////////////////
                anthemSong = MediaPlayer.create(MainActivity.this, R.raw.national_anthem_of_the_usa);
                if (!anthemSong.isPlaying()) {
                    anthemSong.start();
                }

                final ImageView imageViewFemida = (ImageView) findViewById(R.id.imageViewFemida);
                final TextView textViewPlay = (TextView) findViewById(R.id.textViewPlay);
                final ImageView imageViewDlr = (ImageView) findViewById(R.id.imageViewDlr);


                final ImageView imageViewNewLogo = (ImageView) findViewById(R.id.imageViewLogo);
                final EditText editTextPlayerName = (EditText) findViewById(R.id.editTextPlayerName);
                final TextView textViewEnterName = (TextView) findViewById(R.id.textViewEnterName);

                final Button btnOk = (Button) findViewById(R.id.buttonOK);
                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if (anthemSong.isPlaying())
                            anthemSong.stop();

                        Intent intent = new Intent(MainActivity.this, GameActivity.class);
                        intent.putExtra("player_name", editTextPlayerName.getText().toString());
                        startActivity(intent);

                    }
                });


                imageViewDlr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        // Get current time in nano seconds.
                        long pressTime = System.currentTimeMillis();

                        // If double click...
                        if (pressTime - lastPressTime <= DOUBLE_PRESS_INTERVAL) {
                            Toast.makeText(getApplicationContext(), "Double Click", Toast.LENGTH_SHORT).show();
                            if (anthemSong.isPlaying()) {
                                anthemSong.pause();
                            } else {
                                anthemSong.start();
                            }
                            mHasDoubleClicked = true;
                        }
                        else {     // If not double click....
                            mHasDoubleClicked = false;
                            Handler myHandler = new Handler() {
                                public void handleMessage(Message m) {
                                    if (!mHasDoubleClicked) {
                                        Toast.makeText(getApplicationContext(), "Single Click Event", Toast.LENGTH_SHORT).show();

                                        imageViewFemida.setVisibility(View.INVISIBLE);
                                        imageViewDlr.setVisibility(View.INVISIBLE);
                                        textViewPlay.setVisibility(View.INVISIBLE);

                                        imageViewNewLogo.setVisibility(View.VISIBLE);
                                        editTextPlayerName.setVisibility(View.VISIBLE);
                                        textViewEnterName.setVisibility(View.VISIBLE);
                                        btnOk.setVisibility(View.VISIBLE);
                                    }
                                }
                            };
                            Message m = new Message();
                            myHandler.sendMessageDelayed(m,DOUBLE_PRESS_INTERVAL);
                        }
                        lastPressTime = pressTime;

                    }
                });
            }
        });
        t.start();
    }

    // System Back button
    @Override
    public void onBackPressed() {

        if (anthemSong.isPlaying())
            anthemSong.stop();

        // TODO Auto-generated method stub
        super.onBackPressed();
    }

    // System Home button
    @Override
    protected void onUserLeaveHint() {
        onBackPressed();
        super.onUserLeaveHint();
    }

}
