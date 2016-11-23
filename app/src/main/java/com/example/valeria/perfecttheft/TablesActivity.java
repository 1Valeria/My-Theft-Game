package com.example.valeria.perfecttheft;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

/**
 * Created by Valeria on 07.10.2016.
 */
public class TablesActivity extends Activity {

    private void showSituation(final Situation sit, final Situation[] mas, final short[] numOfSituation) {

        Button[] btns = new Button[]{
                (Button) findViewById(R.id.buttonSit1),
                (Button) findViewById(R.id.buttonSit2),
                (Button) findViewById(R.id.buttonSit3),
                (Button) findViewById(R.id.buttonSit4)
        };

        for (short i = 0; i < 4; i++) {
            final short j = i;

            btns[i].setText(sit.getSolution().getNames()[i]);

            btns[i].setOnClickListener(new View.OnClickListener() {

                TextView txtEvid = (TextView) findViewById(R.id.textViewEvidLvl);
                TextView txtLoud = (TextView) findViewById(R.id.textViewLoudLvl);

                private void showNext() {
                    if (numOfSituation[0] < mas.length)
                        if (sit.getSolution().getShows()[j])
                            showSituation(mas[numOfSituation[0]++], mas, numOfSituation);
                }

                @Override
                public void onClick(View view) {

                    sit.getSolution().getMps()[j].start();
                    showNext();

                    txtEvid.setText(Integer.toString(Integer.valueOf(
                            (txtEvid.getText()).toString()) +
                            sit.getSolution().getEvids()[j]));
                    txtLoud.setText(Integer.toString(Integer.valueOf(
                            (txtLoud.getText()).toString()) +
                            sit.getSolution().getLouds()[j]));
                }
            });

            switch(btns[i].getText().toString()){

                case ("Use Master Key"): {
                    if (getIntent().getExtras().getBoolean("key")) {
                        btns[i].setClickable(true);
                    } else {
                        btns[i].setClickable(false);
                    }
                    break;
                }

                case ("Shot the dog using gun"): {
                    if (getIntent().getExtras().getBoolean("weapon")) {
                        btns[i].setClickable(true);
                    } else {
                        btns[i].setClickable(false);
                    }
                    break;
                }

                case ("Cut by the knife"): {
                    if (getIntent().getExtras().getBoolean("knife")) {
                        btns[i].setClickable(true);
                    } else {
                        btns[i].setClickable(false);
                    }
                    break;
                }

                case ("Give the meat from the burger"): {
                    if (getIntent().getExtras().getBoolean("burger")) {
                        btns[i].setClickable(true);
                    } else {
                        btns[i].setClickable(false);
                    }
                    break;
                }

            }
        }

        ImageView imgWall = (ImageView) findViewById(R.id.imageViewWallpaper);
        TextView txtDesc = (TextView) findViewById(R.id.textViewSituationText);
        imgWall.setImageResource(sit.getWall());
        txtDesc.setText(sit.getDesc());

    }

    private void situationInit() {

        final short[] numOfSituation = {1};

        // Situation  num1 - closed door
        final Situation situation_the_door = new Situation();

        situation_the_door.setWall(R.mipmap.situation_the_door);
        situation_the_door.setDescription("The closed door. An ordinary lock. " +
                "Seems like I have a deal with the similar before. I try to use a master key. " +
                "Of course if I have one.");
        String[] names1 = new String[]{"Use Master Key", "Break the Window to get in", "Break the Door to get in", "Knock"};
        int[] louds1 = new int[]{5, 40, 40, 10};
        int[] evids1 = new int[]{5, 50, 50, 1};
        boolean[] shows1 = new boolean[]{true, true, true, false};
        MediaPlayer[] mPs1 = new MediaPlayer[]{
                MediaPlayer.create(TablesActivity.this, R.raw.btns_the_master_key),
                MediaPlayer.create(TablesActivity.this, R.raw.btns_the_broken_window),
                MediaPlayer.create(TablesActivity.this, R.raw.btns_the_broken_door),
                MediaPlayer.create(TablesActivity.this, R.raw.btns_the_knock),
        };
        Situation.Btns bT = new Situation.Btns();
        bT.setBtn(names1, louds1, evids1, shows1, mPs1);
        situation_the_door.setSolution(bT);

        final Situation situation_the_light = new Situation();

        situation_the_light.setWall(R.mipmap.situation_the_light);
        situation_the_light.setDescription("There's no one is here. The light is turned off. " +
                "Of course... May I turn the light on? Or the neighbors can see the suspicion?");
        String[] names2 = new String[]{"Use flashlight", "Keep going in the dark", "Use broken smartphones screen", "Turn the light on"};
        int[] louds2 = new int[]{5, 30, 15, 20};
        int[] evids2 = new int[]{3, 20, 10, 25};
        boolean[] shows2 = new boolean[]{true, false, true, true};
        MediaPlayer[] mPs2 = new MediaPlayer[]{
                MediaPlayer.create(TablesActivity.this, R.raw.btns_the_switcher),
                MediaPlayer.create(TablesActivity.this, R.raw.btns_the_keep_off),
                MediaPlayer.create(TablesActivity.this, R.raw.btns_the_phone),
                MediaPlayer.create(TablesActivity.this, R.raw.btns_the_light_switcher),
        };
        Situation.Btns bT2 = new Situation.Btns();
        bT2.setBtn(names2, louds2, evids2, shows2, mPs2);
        situation_the_light.setSolution(bT2);

        Situation situation_the_animal = new Situation();
        Random rnd = new Random();
        boolean isDangerousAnimal = rnd.nextBoolean();
        if(isDangerousAnimal){
            situation_the_animal.setWall(R.mipmap.situation_the_bad_dog);
            situation_the_animal.setDescription("Oh no!.. I hate dogs. This dog-meat can ruin everything. " +
                    "But no. I'm not gonna deal with it. I have great plans. And not going to step back. " +
                    "Have something special for you, puppy!");
            String[] names3 = new String[]{"Ignore", "Shot the dog using gun", "Cut by the knife", "Give the meat from the burger"};
            int[] louds3 = new int[]{20, 50, 10, 3};
            int[] evids3 = new int[]{4, 40, 40, 2};
            boolean[] shows3 = new boolean[]{false, true, true, true};
            MediaPlayer[] mPs3 = new MediaPlayer[]{
                    MediaPlayer.create(TablesActivity.this, R.raw.btns_the_bad_dog),
                    MediaPlayer.create(TablesActivity.this, R.raw.btns_the_shot),
                    MediaPlayer.create(TablesActivity.this, R.raw.btns_the_poor_dog),
                    MediaPlayer.create(TablesActivity.this, R.raw.btns_the_dof_snif)
            };
            Situation.Btns bT3 = new Situation.Btns();
            bT3.setBtn(names3, louds3, evids3, shows3, mPs3);
            situation_the_animal.setSolution(bT3);
        }
        else{
            situation_the_animal.setWall(R.mipmap.situation_the_good_cat);
            situation_the_animal.setDescription("What a wonderful cat. I love the cats. Gorgeos, beautiful, amazing creatures. " +
                    "He looks like he is not going to hurt my business, but who knows that, right?");
            String[] names3 = new String[]{"Ignore", "Shot the cat using gun", "Cut by the knife", "Give the meat from the burger"};
            int[] louds3 = new int[]{10, 50, 10, 3};
            int[] evids3 = new int[]{4, 40, 40, 2};
            boolean[] shows3 = new boolean[]{true, true, true, true};
            MediaPlayer[] mPs3 = new MediaPlayer[]{
                    MediaPlayer.create(TablesActivity.this, R.raw.btns_the_cat),
                    MediaPlayer.create(TablesActivity.this, R.raw.btns_the_shot),
                    MediaPlayer.create(TablesActivity.this, R.raw.btns_the_cat),
                    MediaPlayer.create(TablesActivity.this, R.raw.btns_the_dof_snif)
            };
            Situation.Btns bT3 = new Situation.Btns();
            bT3.setBtn(names3, louds3, evids3, shows3, mPs3);
            situation_the_animal.setSolution(bT3);
        }


///////////////
        final Situation[] masOfSituations = new Situation[]{
                situation_the_door,
                situation_the_light,
                situation_the_animal,
        };
///////////////

        showSituation(masOfSituations[0], masOfSituations, numOfSituation);

    }

    private short min = 2;
    private short sec = 59;
    private boolean flagPause = true;

    int pauseImgs[] = new int[]{
            R.drawable.the_american_dream,
            R.drawable.the_bad_man,
            R.drawable.the_judge,
            R.drawable.the_moral,
            R.drawable.the_right_wrong
    };


    private Handler handler = new Handler();
    private Runnable task = new Runnable() {

        @Override
        public void run() {

            if (flagPause) {

                TextView timerTxt = (TextView) findViewById(R.id.textViewTimer);

                final Button btnPause = (Button) findViewById(R.id.buttonPause);
                btnPause.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Random rnd = new Random();

                        final ImageView imgPauseWall = (ImageView) findViewById(R.id.imageViewPause);
                        imgPauseWall.setVisibility(View.VISIBLE);
                        final Button btnResume = (Button) findViewById(R.id.buttonResume);
                        btnResume.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                btnResume.setVisibility(View.INVISIBLE);
                                imgPauseWall.setVisibility(View.INVISIBLE);
                                flagPause = true;
                            }
                        });

                        if (flagPause) {
                            btnPause.setText("| >");
                            flagPause = false;

                            imgPauseWall.setImageResource(pauseImgs[rnd.nextInt(pauseImgs.length)]);
                            btnResume.setVisibility(View.VISIBLE);
                            imgPauseWall.setVisibility(View.VISIBLE);
                        } else {
                            btnPause.setText("||");
                            flagPause = true;
                            imgPauseWall.setVisibility(View.INVISIBLE);
                            btnResume.setVisibility(View.INVISIBLE);
                        }
                    }
                });

                if (sec < 10)
                    timerTxt.setText("0" + min + ":0" + String.valueOf(sec--));
                else
                    timerTxt.setText("0" + min + ":" + String.valueOf(sec--));

                if ((sec <= 0) && (min > 0)) {
                    min--;
                    sec = 59;
                } else if ((sec < 0) && (min <= 0)) {
                    System.out.println("=========== return ==========");
                    min = 0;
                    sec = 0;
                    timerTxt.setText("0" + min + ":" + "0" + String.valueOf(sec--));
                    flagPause = false;
                }
            } else {
            }

            handler.postDelayed(this, 1000);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);


        situationInit();
    }

    protected void onResume() {
        super.onResume();
        handler.postDelayed(task, 0);
    }

    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(task);
    }

    // System Back button
    @Override
    public void onBackPressed() {

        // TODO Auto-generated method stub
        if (flagPause)
            flagPause = false;
        else
            super.onBackPressed();
    }

    // System Home button
    @Override
    protected void onUserLeaveHint() {
        onBackPressed();
        super.onUserLeaveHint();
    }
}
