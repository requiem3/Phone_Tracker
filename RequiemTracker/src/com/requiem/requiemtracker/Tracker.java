package com.requiem.requiemtracker;

import android.location.LocationManager;

import android.os.Bundle;
import android.provider.Settings;
import android.widget.TextView;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;

import tools.Messaging;

//TODO: email coordinates
//TODO: discover where coordinates are
public class Tracker {
	private Messaging messanger;
	private LocationManager locationManager;
	private String provider;
	private MyLocationListener mylistener;
	private Criteria criteria;
	private Activity activity;
	private TextView tViewLatitude;
	private TextView tViewLongitude;

	public Tracker(Activity activity) {
		this.activity = activity;
		messanger = new Messaging(activity);
		tViewLatitude = (TextView) activity.findViewById(R.id.textLatitude);
		tViewLongitude = (TextView) activity.findViewById(R.id.textLongitude);

		locationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
		criteria = new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_COARSE);

		criteria.setCostAllowed(false);
		provider = locationManager.getBestProvider(criteria, false);

		Location location = locationManager.getLastKnownLocation(provider);

		mylistener = new MyLocationListener();

		if (location != null) {
			mylistener.onLocationChanged(location);
		} else { //If location services are not enabled
			Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			activity.startActivity(intent);
		}

		locationManager.requestLocationUpdates(provider, 200, 1, mylistener);
	}

	private class MyLocationListener implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			tViewLatitude.setText("Latitude: " + location.getLatitude());
			tViewLongitude.setText("Longitude: " + location.getLongitude());
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {

		}

		@Override
		public void onProviderEnabled(String provider) {

		}

		@Override
		public void onProviderDisabled(String provider) {

		}
	}
}

/* Might be useful someday code 
//Enable GPS
Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
intent.putExtra("enabled", true);
sendBroadcast(intent);
//Disable GPS
Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
intent.putExtra("enabled", false);
sendBroadcast(intent);
*/
