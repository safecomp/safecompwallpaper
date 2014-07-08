package com.safecomp.wallpaper;

import com.safecomp.safecompwallpaper.R;
import com.safecomp.safecompwallpaper.R.layout;
import com.safecomp.safecompwallpaper.R.menu;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.app.Activity;
import android.view.Menu;

public class SafeCompPreferencesActivity extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.prefs);
	}

}
