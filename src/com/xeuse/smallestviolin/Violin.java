package com.xeuse.smallestviolin;

import android.os.Build;
import android.widget.ImageView;

import com.actionbarsherlock.app.SherlockActivity;

public class Violin extends Instrument {
	
	private ImageView bowImage, violinImage;
	private int imageCenter = 0;
	private int yAxis = 0;	
	
	public Violin(SherlockActivity mainActivity) {
		super(mainActivity);
	}
	
	@Override
	void create() {	
		viewStub().setLayoutResource(R.layout.violin);
		viewStub().inflate();

		violinImage = (ImageView) activity().findViewById(R.id.violin_image);
		bowImage = (ImageView) activity().findViewById(R.id.bow_image);

	}

	@Override
	void eventOn() {
		imageCenter = (bowImage.getWidth() / (int) 2);
		yAxis = (violinImage.getHeight() / (int) 2);
	}

	@Override
	void eventMove(float x) {
		int xPos = (int) x; 
		/* Required as setX / setY were added in API 11+*/
		if(Build.VERSION.SDK_INT >= 11) {
			bowImage.setX(xPos - imageCenter);
			bowImage.setY(yAxis);
		} else {
			int leftPos = xPos - imageCenter;
			/*if (leftPos < 0) leftPos = 0;*/
			bowImage.setPadding(leftPos, yAxis, 0, 0);
		}
	}

	@Override
	void eventOff() {}
	
	@Override
	void autoPlay() {
		/* Autoplay instrument */
		
		
	}
	
}
