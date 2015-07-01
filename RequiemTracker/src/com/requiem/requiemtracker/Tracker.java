package com.requiem.requiemtracker;

import android.location.LocationManager;
import android.widget.TextView;
import android.app.Activity;
import android.location.GpsStatus;
import android.location.GpsStatus.Listener;
import android.location.Location;

public class Tracker {
	private Activity activity;
	private LocationManager mLManager;
	private Location mLocation;
	private String bestProvider;
	private GpsStatus mGpsStatus;
	private Listener gpsListener;
	private TextView tView;
	
	public Tracker(Activity activity) {
		this.activity = activity;
		tView = (TextView) activity.findViewById(R.id.test1);
		
		mLManager.addGpsStatusListener(gpsListener);
		
		bestProvider = mLManager.getBestProvider(null, false);
		tView.setText(bestProvider);
		
	}
}
