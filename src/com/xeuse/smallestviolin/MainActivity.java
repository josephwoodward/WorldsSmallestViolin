package com.xeuse.smallestviolin;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.preference.PreferenceManager;

import com.google.ads.*;

public class MainActivity extends SherlockActivity {

	private int oldX = 0;
	final int threshold = 2;
	
	private Instrument instrument;
	private ViewStub viewStub;
	private AdView adView = null;
	private String adUnitId = "a150b38c21d6c3e"; 
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		setContentView(R.layout.activity_main);
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		
		
		viewStub = (ViewStub) findViewById(R.id.stub);
		
		instrument = new Violin(MainActivity.this);
		instrument.setStub(viewStub);
		instrument.create();

		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
		
		boolean autoPlay = prefs.getBoolean("autoplay", false);
		if (autoPlay) {
			instrument.autoPlay();
			instrument.playSound();
		}


		adView = new AdView(this, AdSize.BANNER, adUnitId);
		boolean disableAds = prefs.getBoolean("disable_ads", false);
		if (!disableAds) {
			LinearLayout mainLayout = (LinearLayout) findViewById(R.id.advert);
			mainLayout.addView(adView);
			adView.loadAd(new AdRequest());
		}

	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);
	}
	
	@Override
	public void onDestroy() {
		if (adView != null) {
			adView.destroy();
		}
		super.onDestroy();
	}
	
	/*
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);
	    if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
	    	Log.d("MYTAG", "Landscape");
	    } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
	    	Log.d("MYTAG", "Portrait");
	    }
	}*/
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getSupportMenuInflater();
	    inflater.inflate(R.menu.options_menu, menu);
	    return super.onCreateOptionsMenu(menu);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	    case R.id.opt_settings:
	        Intent preferencesIntent = new Intent(this, Preferences.class);
	        startActivity(preferencesIntent);
	        return true;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	}
	
	
	/* Programatically add menu items
	public boolean onCreateOptionsMenu2(Menu menu) {	
		SubMenu subMenu1 = menu.addSubMenu("Options");
		subMenu1.add(getResources().getString(R.string.settings));
		subMenu1.add(getResources().getString(R.string.play_on_load_enable));
		MenuItem subMenu1Item = subMenu1.getItem();
		subMenu1Item.setIcon(R.drawable.abs__ic_menu_moreoverflow_normal_holo_light);
		subMenu1Item.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT);
		return super.onCreateOptionsMenu(menu);
	}*/

	public boolean onTouchEvent(MotionEvent event) {

		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				instrument.eventOn();
				instrument.playSound();
				return true;
				
			case MotionEvent.ACTION_MOVE:
				float posX = event.getX();
				instrument.eventMove(posX);
				/*if ( detectValidMovement(posX) ) {
					instrument.playSound();	
					Log.d("MYAG", "Action move");
				} else {
					instrument.pauseSound();	
				}*/
				
				instrument.playSound();
				return true;
			case MotionEvent.ACTION_UP:
				instrument.pauseSound();
				instrument.eventOff();
				return true;
		}
		return false;
		
/*		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			instrument.eventOn();
		} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
			float posX = event.getX();
			if ( detectValidMovement(posX) ) {
				instrument.playSound();
				instrument.eventMove(posX);			
			}
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			instrument.pauseSound();
			instrument.eventOff();
			// Set off position
		}
		return true;*/
	}
	
	
	private boolean detectValidMovement(float posX) {
		int newX = Math.round(posX);
	    double distance = Math.abs(newX - oldX);
	    oldX = newX;
	    if(distance < threshold) {    	
	        return false;
	    }
	    return true;
	}
	
}