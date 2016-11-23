package com.example.valeria.perfecttheft;

import android.media.MediaPlayer;

/**
 * Created by Valeria on 07.10.2016.
 */
public class Situation {

    public static class Btns{

        private String[] names = new String[4];
        private int[] louds = new int[4];
        private int[] evids = new int[4];
        private boolean[] shows = new boolean[4];
        private MediaPlayer[] mPs = new MediaPlayer[4];

        protected String[] getNames(){
            return names;
        }

        protected int[] getLouds(){
            return louds;
        }

        protected int[] getEvids(){
            return evids;
        }

        protected boolean[] getShows(){
            return shows;
        }

        protected MediaPlayer[] getMps(){
            return mPs;
        }

        protected void setBtn(String namess[], int[] loudss, int[] evidss, boolean[] showss, MediaPlayer[] mPss){
            this.names = namess;
            this.louds = loudss;
            this.evids = evidss;
            this.shows = showss;
            this.mPs = mPss;
        }
    }

    private int wallpapers;
    private String description;
    private Btns solution;

    protected void setWall(int wallpapersImg){
        this.wallpapers = wallpapersImg;
    }

    protected void setDescription(String desc){
        this.description = desc;
    }

    protected void setSolution(Btns solt){
        this.solution = solt;
    }

    protected int getWall(){
        return wallpapers;
    }

    protected String getDesc(){
        return description;
    }

    protected Btns getSolution(){
        return solution;
    }

}
