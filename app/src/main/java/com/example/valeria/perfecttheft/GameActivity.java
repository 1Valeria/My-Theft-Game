package com.example.valeria.perfecttheft;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Activity {

    MediaPlayer police_radio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        final String player_name = intent.getStringExtra("player_name");

        Thread t = new Thread(new Runnable() {
            public void run() {

                final int[] points = {1};

                points[0] = 3000;

                police_radio = MediaPlayer.create(GameActivity.this, R.raw.police_radio_1);

                if (!police_radio.isPlaying()) {
                    police_radio.setLooping(true);
                    police_radio.start();
                }

                final TextView textViewHelloTxt = (TextView) findViewById(R.id.textViewHelloTxt);
                String helloStr = "Hello, ";
                textViewHelloTxt.setText(helloStr + player_name + "! " + textViewHelloTxt.getText());

                final Button buttonNext = (Button) findViewById(R.id.buttonNext);

                final TextView textViewPoints = (TextView) findViewById(R.id.textViewPoints);
                textViewPoints.setVisibility(View.INVISIBLE);

                final LinearLayout stuff_layout = (LinearLayout) findViewById(R.id.layout_stuff);
                stuff_layout.setVisibility(View.INVISIBLE);

                buttonNext.setOnClickListener(new View.OnClickListener() {

                    public void manageItem(boolean[] plusXBtnFlag, Button plusXBtn, TextView xName, int idName) {
                        if (!plusXBtnFlag[0]) {
                            if (points[0] >= Integer.valueOf(((TextView) findViewById(idName)).getText().toString())) {
                                plusXBtn.setTextSize(60);
                                plusXBtn.setText("-");
                                Toast.makeText(GameActivity.this, "Add " + xName.getText(), Toast.LENGTH_SHORT).show();
                                plusXBtnFlag[0] = true;
                                points[0] -= Integer.valueOf(((TextView) findViewById(idName)).getText().toString());
                                textViewPoints.setText(Integer.toString(points[0]));
                            }
                            else
                                Toast.makeText(GameActivity.this, "Sadly, you too poor to buy this item", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            plusXBtn.setTextSize(40);
                            plusXBtn.setText("+");
                            Toast.makeText(GameActivity.this, "Remove " + xName.getText(), Toast.LENGTH_SHORT).show();
                            plusXBtnFlag[0] = false;
                            points[0] += Integer.valueOf(((TextView) findViewById(idName)).getText().toString());
                            textViewPoints.setText(Integer.toString(points[0]));
                        }
                    }

                    final boolean[] plusGlovesBtnFlag = {false};
                    final boolean[] plusWeaponBtnFlag = {false};
                    final boolean[] plusKeyBtnFlag = {false};
                    final boolean[] plusKnifeBtnFlag = {false};
                    final boolean[] plusDNABtnFlag = {false};
                    final boolean[] plusBurgerBtnFlag = {false};

                    @Override
                    public void onClick(View view) {

                        if (textViewHelloTxt.getVisibility() == View.VISIBLE) {
                            textViewHelloTxt.setVisibility(View.INVISIBLE);
                            textViewPoints.setText(Integer.toString(points[0]));
                            textViewPoints.setVisibility(View.VISIBLE);

                            stuff_layout.setVisibility(View.VISIBLE);

                            final Button plusGlovesBtn = (Button) findViewById(R.id.buttonPlusGloves);
                            final Button plusWeaponBtn = (Button) findViewById(R.id.buttonPlusWeapon);
                            final Button plusKeyBtn = (Button) findViewById(R.id.buttonPlusKey);
                            final Button plusKnifeBtn = (Button) findViewById(R.id.buttonPlusKnife);
                            final Button plusDNABtn = (Button) findViewById(R.id.buttonPlusDNA);
                            final Button plusBurgerBtn = (Button) findViewById(R.id.buttonPlusBurger);

                            plusGlovesBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    manageItem(plusGlovesBtnFlag, plusGlovesBtn, ((TextView) findViewById(R.id.textViewGloves)), R.id.textViewPriceGloves);
                                }
                            });

                            plusWeaponBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    manageItem(plusWeaponBtnFlag, plusWeaponBtn, ((TextView) findViewById(R.id.textViewWeapon)), R.id.textViewPriceWeapon);
                                }
                            });

                            plusKeyBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    manageItem(plusKeyBtnFlag, plusKeyBtn, ((TextView) findViewById(R.id.textViewKey)), R.id.textViewPriceKey);
                                }
                            });

                            plusKnifeBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    manageItem(plusKnifeBtnFlag, plusKnifeBtn, ((TextView) findViewById(R.id.textViewKnife)), R.id.textViewPriceKnife);
                                }
                            });

                            plusDNABtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    manageItem(plusDNABtnFlag, plusDNABtn, ((TextView) findViewById(R.id.textViewDNA)), R.id.textViewPriceDNA);
                                }
                            });

                            plusBurgerBtn.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    manageItem(plusBurgerBtnFlag, plusBurgerBtn, ((TextView) findViewById(R.id.textViewBurger)), R.id.textViewPriceBurger);
                                }
                            });

                        }

                        else {
                            police_radio.stop();

                            Intent intent = new Intent(GameActivity.this, TablesActivity.class);

                            if(plusKnifeBtnFlag[0])
                                intent.putExtra("knife", true);
                            else intent.putExtra("knife", false);

                            if(plusWeaponBtnFlag[0])
                                intent.putExtra("weapon", true);
                            else
                                intent.putExtra("weapon", false);

                            if(plusBurgerBtnFlag[0])
                                intent.putExtra("burger", true);
                            else
                                intent.putExtra("burger", false);

                            if(plusGlovesBtnFlag[0])
                                intent.putExtra("gloves", true);
                            else
                                intent.putExtra("gloves", false);

                            if(plusKeyBtnFlag[0])
                                intent.putExtra("key", true);
                            else
                                intent.putExtra("key", false);

                            if(plusDNABtnFlag[0])
                                intent.putExtra("DNA", true);
                            else
                                intent.putExtra("DNA", false);

                                startActivity(intent);
                        }
                    }

                });
            }
        });
        t.start();
    }

    // System Back button
    @Override
    public void onBackPressed() {

        System.out.println("Back pressed");

        Intent intent1 = new Intent(this, MainActivity.class);
        startActivity(intent1);

        police_radio.stop();

        // TODO Auto-generated method stub
        super.onBackPressed();
    }

    // System Home button
    @Override
    protected void onUserLeaveHint() {

        System.out.println("Home pressed");
        //onBackPressed();
        super.onUserLeaveHint();
    }

}
