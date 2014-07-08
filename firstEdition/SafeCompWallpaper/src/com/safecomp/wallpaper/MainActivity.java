package com.safecomp.wallpaper;

import com.safecomp.safecompwallpaper.R;
import com.safecomp.safecompwallpaper.R.layout;
import com.safecomp.safecompwallpaper.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.app.WallpaperManager;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button settings=(Button)findViewById(R.id.settings);
		settings.setOnClickListener(onSettings);
	
	}
	private View.OnClickListener onSettings =new View.OnClickListener(){

		@Override
		public void onClick(View arg0) {
			startActivity(new Intent(MainActivity.this,SafeCompPreferencesActivity.class));
		}
		
	};
	
}
