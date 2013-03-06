package com.xeuse.smallestviolin;

import java.util.ArrayList;

import com.actionbarsherlock.app.SherlockActivity;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;



public class Background extends SherlockActivity {

	/*private ViewPager pager;
	private ArrayList<String> data;*/
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.background);

		/*data = new ArrayList<String>();
		data.add("A");
		data.add("B");

		pager = (ViewPager) findViewById(R.id.bg_viewpager);
		
		TypedArray bgArray = getResources().obtainTypedArray(R.array.background_images);
		
		pager.setAdapter(new BgViewPager(this, bgArray));
		pager.setCurrentItem(2);
		
		bgArray.recycle();*/
		
		
		
		BgViewPager adapter = new BgViewPager();
	    ViewPager myPager = (ViewPager) findViewById(R.id.bg_viewpager);
	    myPager.setAdapter(adapter);
	    myPager.setCurrentItem(2);
		
		
	}

	private class BgViewPager extends PagerAdapter {

		/*private TypedArray bgImages;
		private Context ctx;*/

/*		public BgViewPager(Context ctx) {
			this.ctx = ctx;
			TypedArray bgImages
			this.bgImages = bgImages;
		}*/

		@Override
	    public Object instantiateItem(View collection, int position) {
			
			 LayoutInflater inflater = (LayoutInflater) collection.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			
			//View layout = getLayoutInflater().inflate(R.layout.background_item, null);
			int resId = 0;
		     switch (position) {
	            case 0:
	                resId = R.layout.background_item;
	                break;
	            case 1:
	                resId = R.layout.background_item;
	                break;
	            case 2:
	                resId = R.layout.background_item;
	                break;
	            case 3:
	                resId = R.layout.background_item;
	                break;
	            case 4:
	                resId = R.layout.background_item;
	                break;
	            }
			 
			 
			View view = inflater.inflate(resId, null);
	        ((ViewPager)collection).addView(view, 0);

	        return view;
        }

		@Override
		public int getCount() {
			/*return data.size();*/
			return 5;
		}

		@Override
		public void destroyItem(View collection, int position, Object view) {
		     ((ViewPager) collection).removeView((View) view);
		}
		
		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			// TODO Auto-generated method stub
			return false;
		}
		
	}
	
}
