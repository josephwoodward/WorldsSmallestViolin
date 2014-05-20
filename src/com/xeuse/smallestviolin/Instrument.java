package com.xeuse.smallestviolin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import android.content.SharedPreferences;
import com.actionbarsherlock.app.SherlockActivity;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.view.ViewStub;

public abstract class Instrument {

	private MediaPlayer player;
	private SherlockActivity activity;
	private Context context;
	private ViewStub stub;
	private String defaultMp3 = "tune_1.mp3";
	private String mp3_tune;

	public Instrument(SherlockActivity mainActivity) {
		activity = mainActivity;
		context = activity.getApplicationContext();

        //Set audio file
        SharedPreferences prefs = activity.getSharedPreferences("com.xeuse.smallestviolin", Context.MODE_PRIVATE);
        mp3_tune = prefs.getString("mp3Path", null);

        if (mp3_tune != null) {
            try {
                mp3_tune = URLDecoder.decode(mp3_tune, "UTF-8");
            } catch(UnsupportedEncodingException exception) {
                exception.printStackTrace();
            }
        }

		initAudio(context);
	}

	protected SherlockActivity activity() {
		return this.activity;
	}
	
	protected ViewStub viewStub() {
		return this.stub;
	}

	public void initAudio(Context ctx) {
		
		AssetFileDescriptor asset;
		try {
			asset = ctx.getAssets().openFd((mp3_tune != null) ? mp3_tune : defaultMp3);
			player = new MediaPlayer();
			player.setDataSource(asset.getFileDescriptor());
			player.prepare();

		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void playSound() {
		player.start();
	}
	
	public void pauseSound() {
		player.pause();
	}
	
	public void stopSound() {
		player.stop();
	}

	public void setStub(ViewStub s) {
		this.stub = s;
	}
	
	abstract void create();
	
	abstract void eventOn();
	
	abstract void eventMove(float x);
	
	abstract void eventOff();

	abstract void autoPlay();

}
