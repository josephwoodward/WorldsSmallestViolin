package com.xeuse.smallestviolin;

import java.io.IOException;

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
	private String mp3_tune = "tune_1.mp3";
	
	public Instrument(SherlockActivity mainActivity) {
		activity = mainActivity;
		context = activity.getApplicationContext();
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

			asset = ctx.getAssets().openFd(mp3_tune);
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
